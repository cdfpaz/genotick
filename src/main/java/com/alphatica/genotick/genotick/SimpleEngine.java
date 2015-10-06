package com.alphatica.genotick.genotick;

import com.alphatica.genotick.breeder.ProgramBreeder;
import com.alphatica.genotick.data.DataSetName;
import com.alphatica.genotick.data.MainAppData;
import com.alphatica.genotick.killer.ProgramKiller;
import com.alphatica.genotick.population.Population;
import com.alphatica.genotick.population.Program;
import com.alphatica.genotick.population.ProgramName;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SimpleEngine implements Engine {
    private EngineSettings engineSettings;
    private TimePointExecutor timePointExecutor;
    private ProgramKiller killer;
    private ProgramBreeder breeder;
    private Population population;
    private MainAppData data;

    private SimpleEngine() {
    }

    @Override
    public List<TimePointStats> start() {
        double result = 1;
        initPopulation();
        TimePoint timePoint = new TimePoint(engineSettings.startTimePoint);
        List<TimePointStats> timePointStats = new ArrayList<>();
        while(engineSettings.endTimePoint.compareTo(timePoint) >= 0) {
            TimePointStats stat = executeTimePoint(timePoint);
            timePointStats.add(stat);
            result *= (stat.getPercentEarned() / 100 + 1);
            Debug.d("Time:",timePoint,"Percent earned so far:",(result - 1) * 100);
            timePoint.increment();
        }
        timePointExecutor.savePopulation("savedPopulation");
        return timePointStats;
    }

    private void initPopulation() {
        if(population.getSize() == 0 && !engineSettings.executionOnly)
            breeder.breedPopulation(population);
    }

    @Override
    public void setSettings(EngineSettings engineSettings,
                            TimePointExecutor timePointExecutor,
                            MainAppData data,
                            ProgramKiller killer,
                            ProgramBreeder breeder,
                            Population population) {
        this.engineSettings = engineSettings;
        this.timePointExecutor = timePointExecutor;
        this.data = data;
        this.killer = killer;
        this.breeder = breeder;
        this.population = population;
    }

    private TimePointStats executeTimePoint(TimePoint timePoint) {
        Debug.d("Starting TimePoint:", timePoint);
        List<ProgramData> programDataList = data.prepareProgramDataList(timePoint);
        TimePointResult timePointResult = timePointExecutor.execute(timePoint,programDataList);
        TimePointStats timePointStats = TimePointStats.getNewStats(timePoint);
        for(DataSetResult dataSetResult: timePointResult.listDataSetResults()) {
            Prediction prediction = dataSetResult.getCumulativePrediction();
            Debug.d("Prediction for:",dataSetResult.getName(),prediction);
            Double actualChange = data.getActualChange(dataSetResult.getName(),timePoint);
            if(!actualChange.isNaN()) {
                Debug.d("Actual change:", actualChange);
                printPercentEarned(dataSetResult.getName(), prediction, actualChange);
                timePointStats.update(dataSetResult.getName(), actualChange, prediction);
            }
        }
        if(!engineSettings.executionOnly && !programDataList.isEmpty() && !timePointStats.isEmpty())
            updatePopulation(timePointResult);
        Debug.d("Finished TimePoint:",timePoint);
        return timePointStats;
    }

    private void printPercentEarned(DataSetName name, Prediction prediction, Double actualChange) {
        double percent;
        if(prediction == Prediction.OUT) {
            Debug.d("No position");
            return;
        }
        if(prediction.isCorrect(actualChange))
            percent = Math.abs(actualChange);
        else
            percent = -Math.abs(actualChange);
        Debug.d("Profit for",name,percent);
    }

    private void updatePopulation(TimePointResult timePointResult) {
        HashMap<ProgramName, List<Outcome>> programPredictions = new HashMap<>();
        for(DataSetResult dataSetResult: timePointResult.listDataSetResults()) {
            Double actualChange = data.getActualChange(dataSetResult.getName(),timePointResult.getTimePoint());
            updateProgramPredictions(programPredictions,dataSetResult,actualChange);
        }
        updateProgramResults(programPredictions);
        killer.killPrograms(population);
        breeder.breedPopulation(population);
    }

    private void updateProgramResults(Map<ProgramName, List<Outcome>> programResults) {
        if(programResults.isEmpty())
            return;
        Debug.d("updateProgramResults");
        for(Map.Entry<ProgramName, List<Outcome>> entry: programResults.entrySet()) {
            Program program = population.getProgram(entry.getKey());
            program.recordOutcomes(entry.getValue());
            population.saveProgram(program);
        }
        Debug.d("Finished updateProgramResults");
    }

    private void updateProgramPredictions(HashMap<ProgramName, List<Outcome>> programPredictions, DataSetResult dataSetResult,double actualChange) {
        for(ProgramResult programResult: dataSetResult.listProgramResults()) {
            List<Outcome> list = getListForProgram(programResult.getName(), programPredictions);
            Outcome outcome = Outcome.getOutcome(programResult.getPrediction(), actualChange);
            list.add(outcome);
        }
    }

    private List<Outcome> getListForProgram(ProgramName name, HashMap<ProgramName, List<Outcome>> programPredictions) {
        List<Outcome> list = programPredictions.get(name);
        if(list == null) {
            list = new ArrayList<>();
            programPredictions.put(name,list);
        }
        return list;
    }


    public static Engine getEngine() {
        return new SimpleEngine();
    }
}

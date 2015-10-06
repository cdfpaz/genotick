package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Population;
import com.alphatica.genotick.population.Program;

import java.util.List;

class SimpleTimePointExecutor implements TimePointExecutor {

    private Population population;
    private DataSetExecutor dataSetExecutor;


    @Override
    public TimePointResult execute(TimePoint timePoint, List<ProgramData> programDataList) {
        TimePointResult timePointResult = new TimePointResult(timePoint);
        if(programDataList.isEmpty())
            return timePointResult;
        for(Program program: population.listPrograms()) {
            for(ProgramData programData: programDataList) {
                ProgramResult programResult = dataSetExecutor.execute(programData,program);
                timePointResult.addProgramResult(programResult, programData.getName());
                program.recordPrediction(programResult.getPrediction());
                population.saveProgram(program);
            }
        }
        return timePointResult;
    }

    @Override
    public void savePopulation(String path) {
        population.savePopulation(path);
    }

    @Override
    public void setSettings(Population population, DataSetExecutor dataSetExecutor) {
        this.population = population;
        this.dataSetExecutor = dataSetExecutor;
    }



}

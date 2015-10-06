package com.alphatica.genotick.genotick;

import com.alphatica.genotick.breeder.BreederSettings;
import com.alphatica.genotick.breeder.ProgramBreeder;
import com.alphatica.genotick.breeder.ProgramBreederFactory;
import com.alphatica.genotick.data.DataFactory;
import com.alphatica.genotick.data.DataLoader;
import com.alphatica.genotick.data.MainAppData;
import com.alphatica.genotick.killer.ProgramKiller;
import com.alphatica.genotick.killer.ProgramKillerFactory;
import com.alphatica.genotick.killer.ProgramKillerSettings;
import com.alphatica.genotick.mutator.Mutator;
import com.alphatica.genotick.mutator.MutatorFactory;
import com.alphatica.genotick.mutator.MutatorSettings;
import com.alphatica.genotick.population.*;
import com.alphatica.genotick.ui.UserOutput;

import java.util.List;

public class Application {
    public static final Debug Logger = new Debug();
    private final UserOutput output;

    public Application(UserOutput output) {
        this.output = output;
    }

    public void start(MainSettings settings) {
        setupLogger();
        if(!validateSettings(settings))
            return;
        logSettings(settings);
        ProgramKiller killer = getProgramKiller(settings);
        Mutator mutator = getMutator(settings);
        ProgramBreeder breeder = wireProgramBreeder(settings, mutator);
        Population population = wirePopulation(settings);
        Engine engine = wireEngine(settings, killer, breeder, population);
        List<TimePointStats> results = engine.start();
        showSummary(results);
    }

    private boolean validateSettings(MainSettings settings) {
        try {
            settings.validate();
            return true;
        } catch(IllegalArgumentException ex) {
            output.errorMessage(ex.getMessage());
            return false;
        }
    }


    private Engine wireEngine(MainSettings settings, ProgramKiller killer,
                              ProgramBreeder breeder, Population population) {
        EngineSettings engineSettings = getEngineSettings(settings);
        MainAppData data = createData(settings);
        TimePointExecutor timePointExecutor = wireTimePointExecutor(settings, population);
        return EngineFactory.getDefaultEngine(engineSettings, timePointExecutor, data, killer, breeder, population);
    }

    private TimePointExecutor wireTimePointExecutor(MainSettings settings, Population population) {
        DataSetExecutor dataSetExecutor = wireDataSetExecutor(settings);
        return TimePointExecutorFactory.getDefaultExecutor(population, dataSetExecutor);
    }

    private Population wirePopulation(MainSettings settings) {
        PopulationDAO dao = PopulationDAOFactory.getDefaultDAO(settings.populationDAO);
        Population population = PopulationFactory.getDefaultPopulation(dao);
        population.setDesiredSize(settings.populationDesiredSize);
        return population;
    }

    private DataSetExecutor wireDataSetExecutor(MainSettings settings) {
        ProgramExecutorSettings programExecutorSettings = new ProgramExecutorSettings();
        programExecutorSettings.instructionLimit = settings.processorInstructionLimit;
        ProgramExecutor programExecutor = ProgramExecutorFactory.getDefaultProgramExecutor(programExecutorSettings);
        return DataSetExecutorFactory.getDefaultSetExecutor(programExecutor);
    }

    private ProgramBreeder wireProgramBreeder(MainSettings settings, Mutator mutator) {
        BreederSettings breederSettings = new BreederSettings(
                settings.minimumOutcomesBetweenBreeding,
                settings.inheritedChildWeight,
                settings.minimumOutcomesToAllowBreeding,
                settings.randomProgramsAtEachUpdate,
                settings.dataMaximumOffset);
        return ProgramBreederFactory.getDefaultBreeder(breederSettings, mutator);
    }

    private ProgramKiller getProgramKiller(MainSettings settings) {
        ProgramKillerSettings killerSettings = new ProgramKillerSettings();
        killerSettings.maximumDeathByAge = settings.maximumDeathByAge;
        killerSettings.maximumDeathByWeight = settings.maximumDeathByWeight;
        killerSettings.probabilityOfDeathByAge = settings.probabilityOfDeathByAge;
        killerSettings.probabilityOfDeathByWeight = settings.probabilityOfDeathByWeight;
        killerSettings.protectProgramUntilOutcomes = settings.protectProgramUntilOutcomes;
        killerSettings.protectBestPrograms = settings.protectBestPrograms;
        killerSettings.killNonPredictingPrograms = settings.killNonPredictingPrograms;
        killerSettings.requireSymmetricalPrograms = settings.requireSymmetricalPrograms;
        return ProgramKillerFactory.getDefaultProgramKiller(killerSettings);
    }

    private Mutator getMutator(MainSettings settings) {
        MutatorSettings mutatorSettings = new MutatorSettings(
                settings.instructionMutationProbability,
                settings.newInstructionProbability,
                settings.skipInstructionProbability);
        return MutatorFactory.getDefaultMutator(mutatorSettings);
    }

    private MainAppData createData(MainSettings settings) {
        DataLoader dataLoader = DataFactory.getDefaultLoader(settings.dataLoader);
        return dataLoader.createProgramData();
    }

    private EngineSettings getEngineSettings(MainSettings settings) {
        EngineSettings engineSettings = new EngineSettings();
        engineSettings.startTimePoint = settings.startTimePoint;
        engineSettings.endTimePoint = settings.endTimePoint;
        engineSettings.executionOnly = settings.executionOnly;
        return engineSettings;
    }

    @SuppressWarnings("AccessStaticViaInstance")
    private void setupLogger() {
        Logger.setShowTime(true);
        Logger.toFile("genotick.out");
    }

    private void logSettings(MainSettings settings) {
        String settingsString = settings.getString();
        Logger.d(settingsString);
    }

    private void showSummary(List<TimePointStats> list) {
        double result = 1;
        for (TimePointStats stats : list) {
            double percent = stats.getPercentEarned();
            result *= percent / 100.0 + 1;
        }
        Debug.d("Final result for genotic:", result);
    }

}

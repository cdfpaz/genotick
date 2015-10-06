package com.alphatica.genotick.ui;

import com.alphatica.genotick.genotick.Application;
import com.alphatica.genotick.genotick.MainSettings;
import com.alphatica.genotick.genotick.TimePoint;

@SuppressWarnings("unused")
class ConsoleInput implements UserInput {

    @Override
    public void show(Application application) {
        MainSettings settings = new MainSettings();
        settings.startTimePoint = new TimePoint(getLong("Start time point",settings.startTimePoint.toString()));
        TimePoint nextTimePoint = new TimePoint(settings.startTimePoint.getValue() + 1);
        settings.endTimePoint = new TimePoint(getLong("End time point", nextTimePoint.toString()));
        settings.dataLoader = getString("Data directory",settings.dataLoader);
        settings.populationDAO = getString("Population storage", settings.populationDAO);
        settings.executionOnly = getBoolean("Prediction only", settings.executionOnly);
        settings.processorInstructionLimit = getInteger("Processor instruction limit", settings.processorInstructionLimit);

        if (!settings.executionOnly) {
            settings.dataMaximumOffset = getInteger("Data maximum offset", settings.dataMaximumOffset);
            settings.populationDesiredSize = getInteger("Population desired size", settings.populationDesiredSize);
            settings.maximumDeathByAge = getDouble("Maximum death rate by age", settings.maximumDeathByAge);
            settings.maximumDeathByWeight = getDouble("Maximum death rate by weight", settings.maximumDeathByWeight);
            settings.probabilityOfDeathByAge = getDouble("Probability of death by age", settings.probabilityOfDeathByAge);
            settings.probabilityOfDeathByWeight = getDouble("Probability of death by weight", settings.probabilityOfDeathByWeight);
            settings.inheritedChildWeight = getDouble("Inherited child's weight", settings.inheritedChildWeight);
            settings.protectProgramUntilOutcomes = getInteger("Protect programs until outcomes", settings.protectProgramUntilOutcomes);
            settings.newInstructionProbability = getDouble("Probability of new instruction", settings.newInstructionProbability);
            // This looks like an error but it's not. Default value for 'skipInstruction...' is same as 'newInstruction'
            // to keep programs more or less constant size.
            settings.skipInstructionProbability = getDouble("Probability of skipping instruction", settings.newInstructionProbability);
            settings.instructionMutationProbability = getDouble("Instruction mutation probability", settings.instructionMutationProbability);
            settings.minimumOutcomesToAllowBreeding = getInteger("Minimum outcomes to allow breeding", settings.minimumOutcomesToAllowBreeding);
            settings.minimumOutcomesBetweenBreeding = getInteger("Minimum outcomes between breeding", settings.minimumOutcomesBetweenBreeding);
            settings.killNonPredictingPrograms = getBoolean("Kill non-predicting programs", settings.killNonPredictingPrograms);
            settings.randomProgramsAtEachUpdate = getDouble("Random programs at each update", settings.randomProgramsAtEachUpdate);
            settings.protectBestPrograms = getDouble("Protect best programs", settings.protectBestPrograms);
            settings.requireSymmetricalPrograms = getBoolean("Require symmetrical programs", settings.requireSymmetricalPrograms);
        }
        application.start(settings);
    }

    private double getDouble(String s, double value) {
        displayMessage(s,String.valueOf(value));
        String response = getString(null,String.valueOf(value));
        return Double.parseDouble(response);
    }

    private int getInteger(String message, int def) {
        displayMessage(message,String.valueOf(def));
        String response = getString(null,String.valueOf(def));
        return Integer.parseInt(response);
    }

    private boolean getBoolean(String message, boolean def) {
        displayMessage(message, String.valueOf(def));
        String response = getString(null,String.valueOf(def));
        return Boolean.valueOf(response);
    }

    private String getString(String message, String def) {
        displayMessage(message, def);
        String response = System.console().readLine();
        if(response.equals(""))
            return def;
        else
            return response;
    }

    private void displayMessage(String message, String def) {
        if(message != null)
            System.out.print(String.format("%s [%s]: ",message,def));
    }

    private long getLong(String message, String value) {
        displayMessage(message,value);
        String response = getString(null,value);
        return Long.parseLong(response);
    }
}

package com.alphatica.genotick.genotick;

import java.lang.reflect.Field;

public class MainSettings {

    public TimePoint startTimePoint = new TimePoint(20000101);
    public TimePoint endTimePoint = new TimePoint(20150101);
    public String dataLoader = "data";
    public String populationDAO = "RAM";
    public boolean executionOnly = false;

    public int populationDesiredSize = 5_000;
    public int processorInstructionLimit = 256;
    public double maximumDeathByAge = 0.01;
    public double maximumDeathByWeight = 0.1;
    public double probabilityOfDeathByAge = 0.5;
    public double probabilityOfDeathByWeight = 0.5;
    public double inheritedChildWeight = 0;
    public int dataMaximumOffset = 256;
    public int protectProgramUntilOutcomes = 100;
    public double newInstructionProbability = 0.01;
    public double instructionMutationProbability = 0.01;
    public double skipInstructionProbability = 0.01;
    public int minimumOutcomesToAllowBreeding = 50;
    public int minimumOutcomesBetweenBreeding = 50;
    public boolean killNonPredictingPrograms = true;
    public double randomProgramsAtEachUpdate = 0.02;
    public double protectBestPrograms = 0.02;
    public boolean requireSymmetricalPrograms = true;



    public String getString() {
        StringBuilder sb = new StringBuilder();
        Field [] fields = this.getClass().getDeclaredFields();
        for(Field field: fields) {
            try {
                sb.append(field.getName()).append(" ").append(field.get(this)).append("\n");
            } catch (IllegalAccessException e) {
                Debug.d("Unable to print field",field.getName());
            }
        }
        return sb.toString();
    }

    public void validate() {
        ensure(startTimePoint.getValue() < endTimePoint.getValue(),
                "Starting TimePoint must be lower than ending TimePoint");
        ensure(populationDesiredSize > 0, gt0("Population desired size"));
        ensure(dataMaximumOffset > 0, gt0("Data Maximum Offset"));
        ensure(processorInstructionLimit > 0,gt0("Processor Instruction Limit"));
        ensure(check0to1(maximumDeathByAge), zto1("Maximum Death by Age"));
        ensure(check0to1(maximumDeathByWeight),zto1("Maximum Death by Weight"));
        ensure(check0to1(probabilityOfDeathByAge),zto1("Probability Death by Age"));
        ensure(check0to1(inheritedChildWeight),zto1("Inherited Child's Weight"));
        ensure(protectProgramUntilOutcomes >= 0, al0("Protect Programs until Outcomes"));
        ensure(check0to1(newInstructionProbability),zto1("New Instruction Probability"));
        ensure(check0to1(instructionMutationProbability),zto1("Instruction Mutation Probability"));
        ensure(check0to1(skipInstructionProbability),zto1("Skip Instruction Probability"));
        ensure(minimumOutcomesToAllowBreeding >= 0,al0("Minimum outcomes to allow breeding"));
        ensure(minimumOutcomesBetweenBreeding >= 0, al0("Minimum outcomes between breeding"));
        ensure(randomProgramsAtEachUpdate >=0, zto1("Random Programs at Each Update"));
        ensure(protectBestPrograms >= 0, zto1("Protect Best Programs"));

    }
    private String al0(String s) {
        return s + " must be at least 0";
    }
    private String zto1(String s) {
        return s + " must be between 0.0 and 1.0";
    }

    private boolean check0to1(double value) {
        return value >= 0 && value <= 1;
    }

    private String gt0(String s) {
        return s + " must be greater than 0";
    }

    private void ensure(boolean condition, String message) {
        if(!condition) {
            throw new IllegalArgumentException(message);
        }
    }

}

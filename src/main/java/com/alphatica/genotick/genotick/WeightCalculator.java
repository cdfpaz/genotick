package com.alphatica.genotick.genotick;

import com.alphatica.genotick.population.Program;

public class WeightCalculator {


    public static double calculateWeight(Program program) {
        return calculateSquareOfDifference(program);
        //return calculateCorrectVsIncorrectPredictions(program);
    }

    @SuppressWarnings("unused")
    private static double calculateCorrectVsIncorrectPredictions(Program program) {
        int totalPrediction = program.getTotalPredictions();
        if(totalPrediction == 0)
            return 0;
        int correct = program.getCorrectPredictions();
        int incorrect = program.getTotalPredictions() - correct;
        return correct - incorrect;
    }

    private static double calculateSquareOfDifference(Program program) {
        int correct = program.getCorrectPredictions();
        int incorrect = program.getTotalPredictions() - correct;
        boolean positive = correct > incorrect;
        double weightAbs = Math.pow(correct - incorrect,2);
        return positive ? weightAbs : -weightAbs;
    }


}

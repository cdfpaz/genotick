package com.alphatica.genotick.genotick;

public enum Outcome {
    CORRECT(1),
    INCORRECT(-1),
    OUT(0);
    private final int value;

    Outcome(int value) {
        this.value = value;
    }

    public static Outcome getOutcome(Prediction prediction, double actualChange) {
        if(prediction == Prediction.OUT)
            return OUT;
        return prediction.isCorrect(actualChange) ? CORRECT : INCORRECT;
    }
}

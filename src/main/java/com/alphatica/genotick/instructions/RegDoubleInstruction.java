package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class RegDoubleInstruction extends RegInstruction implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -8574875071910464339L;

    private double doubleArgument;

    void setDoubleArgument(double doubleArgument) {
        this.doubleArgument = doubleArgument;
    }

    public double getDoubleArgument() {
        return doubleArgument;
    }

    @Override
    public void mutate(Mutator mutator) {
        super.mutate(mutator);
        doubleArgument *= Double.MAX_VALUE * mutator.getNextDouble();
    }
}

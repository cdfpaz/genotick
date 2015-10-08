package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class VarDoubleInstruction extends VarInstruction implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 5798418310767182684L;

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

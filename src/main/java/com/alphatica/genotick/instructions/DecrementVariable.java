package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.NotEnoughDataException;
import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class DecrementVariable extends VarInstruction implements Serializable {
    public static final long serialVersionUID = -3260981819622564798L;

    public DecrementVariable(DecrementVariable i) {
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public DecrementVariable() {
    }

    @Override
    public void executeOn(Processor processor) throws NotEnoughDataException {
        processor.execute(this);
    }

    @Override
    public DecrementVariable copy() {
        return new DecrementVariable(this);
    }

}

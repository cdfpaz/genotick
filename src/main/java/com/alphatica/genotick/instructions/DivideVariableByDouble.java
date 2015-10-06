package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class DivideVariableByDouble extends VarDoubleInstruction implements Serializable {
    public static final long serialVersionUID = 2277032167143213475L;

    public DivideVariableByDouble(DivideVariableByDouble i) {
        this.setDoubleArgument(i.getDoubleArgument());
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public DivideVariableByDouble() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public DivideVariableByDouble copy() {
        return new DivideVariableByDouble(this);
    }

}

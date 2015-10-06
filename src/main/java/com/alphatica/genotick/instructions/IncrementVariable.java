package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class IncrementVariable extends VarInstruction implements Serializable {
    public static final long serialVersionUID = -7224362880966273739L;

    public IncrementVariable(IncrementVariable i) {
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public IncrementVariable() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public IncrementVariable copy() {
        return new IncrementVariable(this);
    }

}

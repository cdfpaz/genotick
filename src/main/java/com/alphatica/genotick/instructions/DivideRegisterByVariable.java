package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class DivideRegisterByVariable extends RegVarInstruction implements Serializable {
    public static final long serialVersionUID = -7846370685515767796L;

    public DivideRegisterByVariable(DivideRegisterByVariable i) {
        this.setRegister(i.getRegister());
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public DivideRegisterByVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public DivideRegisterByVariable copy() {
        return new DivideRegisterByVariable(this);
    }

}

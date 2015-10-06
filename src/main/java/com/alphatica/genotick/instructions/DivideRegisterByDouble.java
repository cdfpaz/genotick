package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class DivideRegisterByDouble extends RegDoubleInstruction implements Serializable {
    public static final long serialVersionUID = 6495799568812947637L;

    public DivideRegisterByDouble(DivideRegisterByDouble i) {
        this.setDoubleArgument(i.getDoubleArgument());
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public DivideRegisterByDouble() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public DivideRegisterByDouble copy() {
        return new DivideRegisterByDouble(this);
    }

}

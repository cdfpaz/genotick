package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class SubtractDoubleFromRegister extends RegDoubleInstruction implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 8867925324160720308L;

    private SubtractDoubleFromRegister(SubtractDoubleFromRegister i) {
        this.setRegister(i.getRegister());
        this.setDoubleArgument(i.getDoubleArgument());
    }

    @SuppressWarnings("unused")
    public SubtractDoubleFromRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public SubtractDoubleFromRegister copy() {
        return new SubtractDoubleFromRegister(this);
    }
}

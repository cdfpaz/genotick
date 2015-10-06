package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class AddRegisterToRegister extends RegRegInstruction implements Serializable {
    public static final long serialVersionUID = 3465536183323672440L;

    public AddRegisterToRegister(AddRegisterToRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
    }

    @SuppressWarnings("unused")
    public AddRegisterToRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public AddRegisterToRegister copy() {
        return new AddRegisterToRegister(this);
    }

}

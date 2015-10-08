package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveRegisterToRegister extends RegRegInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1416597000762527293L;

    private MoveRegisterToRegister(MoveRegisterToRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
    }

    @SuppressWarnings("unused")
    public MoveRegisterToRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public MoveRegisterToRegister copy() {
        return new MoveRegisterToRegister(this);
    }

}

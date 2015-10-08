package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class SubtractRegisterFromRegister extends RegRegInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -5487441136619310076L;

    private SubtractRegisterFromRegister(SubtractRegisterFromRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
    }

    @SuppressWarnings("unused")
    public SubtractRegisterFromRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public SubtractRegisterFromRegister copy() {
        return new SubtractRegisterFromRegister(this);
    }

}

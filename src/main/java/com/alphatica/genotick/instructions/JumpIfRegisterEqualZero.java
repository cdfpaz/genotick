package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterEqualZero extends RegJumpInstruction implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 5735491554481943162L;

    private JumpIfRegisterEqualZero(JumpIfRegisterEqualZero i) {
        this.setRegister(i.getRegister());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterEqualZero() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterEqualZero copy() {
        return new JumpIfRegisterEqualZero(this);
    }
}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterLessThanZero extends RegJumpInstruction implements Serializable{
    public static final long serialVersionUID = 5576287341828522397L;

    public JumpIfRegisterLessThanZero(JumpIfRegisterLessThanZero i) {
        this.setRegister(i.getRegister());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterLessThanZero() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterLessThanZero copy() {
        return new JumpIfRegisterLessThanZero(this);
    }
}

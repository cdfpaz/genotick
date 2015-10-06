package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterNotEqualZero extends RegJumpInstruction implements Serializable{
    public static final long serialVersionUID = -6429106660478254250L;

    @SuppressWarnings("unused")
    public JumpIfRegisterNotEqualZero() {
    }

    public JumpIfRegisterNotEqualZero(JumpIfRegisterNotEqualZero i) {
        this.setRegister(i.getRegister());
        this.setAddress(i.getAddress());
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterNotEqualZero copy() {
        return new JumpIfRegisterNotEqualZero(this);
    }
}
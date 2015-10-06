package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterNotEqualDouble extends RegDoubleJumpInstruction implements Serializable{
    public static final long serialVersionUID = -8395514990644799759L;

    public JumpIfRegisterNotEqualDouble(JumpIfRegisterNotEqualDouble i) {
        this.setRegister(i.getRegister());
        this.setDoubleArgument(i.getDoubleArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterNotEqualDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterNotEqualDouble copy() {
        return new JumpIfRegisterNotEqualDouble(this);
    }
}

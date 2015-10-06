package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterGreaterThanDouble extends RegDoubleJumpInstruction implements Serializable {
    public static final long serialVersionUID = 3245923977507621290L;

    public JumpIfRegisterGreaterThanDouble(JumpIfRegisterGreaterThanDouble i) {
        this.setAddress(i.getAddress());
        this.setDoubleArgument(i.getDoubleArgument());
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterGreaterThanDouble() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterGreaterThanDouble copy() {
        return new JumpIfRegisterGreaterThanDouble(this);
    }
}
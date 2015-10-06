package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterGreaterThanRegister extends RegRegJumpInstruction implements Serializable {
    public static final long serialVersionUID = -7544201771600408606L;

    public JumpIfRegisterGreaterThanRegister(JumpIfRegisterGreaterThanRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterGreaterThanRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterGreaterThanRegister copy() {
        return new JumpIfRegisterGreaterThanRegister(this);
    }
}

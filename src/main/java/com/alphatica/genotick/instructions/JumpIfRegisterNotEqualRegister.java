package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterNotEqualRegister extends RegRegJumpInstruction implements Serializable{
    public static final long serialVersionUID = -5597151246876888643L;

    public JumpIfRegisterNotEqualRegister(JumpIfRegisterNotEqualRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterNotEqualRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterNotEqualRegister copy() {
        return new JumpIfRegisterNotEqualRegister(this);
    }
}

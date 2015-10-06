package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfRegisterEqualDouble extends RegDoubleJumpInstruction implements Serializable{
    public static final long serialVersionUID = -3677241217620353564L;

    public JumpIfRegisterEqualDouble(JumpIfRegisterEqualDouble i) {
        this.setDoubleArgument(i.getDoubleArgument());
        this.setRegister(i.getRegister());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfRegisterEqualDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfRegisterEqualDouble copy() {
        return new JumpIfRegisterEqualDouble(this);
    }
}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableEqualZero extends VarJumpInstruction implements Serializable{
    public static final long serialVersionUID = -1544494880490476979L;

    public JumpIfVariableEqualZero(JumpIfVariableEqualZero i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableEqualZero() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableEqualZero copy() {
        return new JumpIfVariableEqualZero(this);
    }
}

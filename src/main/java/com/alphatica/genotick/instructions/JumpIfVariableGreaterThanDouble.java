package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableGreaterThanDouble extends VarDoubleJumpInstruction implements Serializable{
    public static final long serialVersionUID = 2980935422934405842L;

    public JumpIfVariableGreaterThanDouble(JumpIfVariableGreaterThanDouble i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setDoubleArgument(i.getDoubleArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableGreaterThanDouble() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableGreaterThanDouble copy() {
        return new JumpIfVariableGreaterThanDouble(this);
    }
}

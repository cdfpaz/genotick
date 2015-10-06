package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableEqualDouble extends VarDoubleJumpInstruction implements Serializable{
    public static final long serialVersionUID = 3937406272874353714L;

    public JumpIfVariableEqualDouble(JumpIfVariableEqualDouble i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setDoubleArgument(i.getDoubleArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableEqualDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableEqualDouble copy() {
        return new JumpIfVariableEqualDouble(this);
    }
}

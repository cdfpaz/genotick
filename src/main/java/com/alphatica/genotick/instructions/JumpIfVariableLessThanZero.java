package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableLessThanZero extends VarJumpInstruction implements Serializable{
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -6368270237071805389L;

    private JumpIfVariableLessThanZero(JumpIfVariableLessThanZero i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableLessThanZero() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableLessThanZero copy() {
        return new JumpIfVariableLessThanZero(this);
    }
}

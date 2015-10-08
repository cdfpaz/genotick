package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableLessThanDouble extends VarDoubleJumpInstruction implements Serializable{
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -8449905052813057724L;

    private JumpIfVariableLessThanDouble(JumpIfVariableLessThanDouble i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setDoubleArgument(i.getDoubleArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableLessThanDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableLessThanDouble copy() {
        return new JumpIfVariableLessThanDouble(this);
    }
}

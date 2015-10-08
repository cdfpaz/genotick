package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableNotEqualRegister extends RegVarJumpInstruction implements Serializable{
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 196783147119700331L;

    private JumpIfVariableNotEqualRegister(JumpIfVariableNotEqualRegister i) {
        this.setRegister(i.getRegister());
        this.setVariableArgument(i.getVariableArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableNotEqualRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableNotEqualRegister copy() {
        return new JumpIfVariableNotEqualRegister(this);
    }
}

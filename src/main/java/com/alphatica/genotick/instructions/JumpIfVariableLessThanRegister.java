package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class JumpIfVariableLessThanRegister extends RegVarJumpInstruction implements Serializable{
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -4297622848412859898L;

    private JumpIfVariableLessThanRegister(JumpIfVariableLessThanRegister i) {
        this.setRegister(i.getRegister());
        this.setVariableArgument(i.getVariableArgument());
        this.setAddress(i.getAddress());
    }

    @SuppressWarnings("unused")
    public JumpIfVariableLessThanRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public JumpIfVariableLessThanRegister copy() {
        return new JumpIfVariableLessThanRegister(this);
    }
}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class AddRegisterToVariable extends RegVarInstruction implements Serializable {

    @SuppressWarnings("unused")
    private static final long serialVersionUID = 1079449155331923812L;

    private AddRegisterToVariable(AddRegisterToVariable i) {
        this.setRegister(i.getRegister());
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public AddRegisterToVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public AddRegisterToVariable copy() {
        return new AddRegisterToVariable(this);
    }

}

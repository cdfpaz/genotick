package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveVariableToRegister extends RegVarInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -3943721532302613198L;

    private MoveVariableToRegister(MoveVariableToRegister i) {
        this.setRegister(i.getRegister());
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public MoveVariableToRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public MoveVariableToRegister copy() {
        return new MoveVariableToRegister(this);
    }

}

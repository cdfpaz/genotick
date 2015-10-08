package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveDoubleToRegister extends RegDoubleInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -8885197267150260362L;

    private MoveDoubleToRegister(MoveDoubleToRegister i) {
        this.setRegister(i.getRegister());
        this.setDoubleArgument(i.getDoubleArgument());
    }

    @SuppressWarnings("unused")
    public MoveDoubleToRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public MoveDoubleToRegister copy() {
        return new MoveDoubleToRegister(this);
    }

}

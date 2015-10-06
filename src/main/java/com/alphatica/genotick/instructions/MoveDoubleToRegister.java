package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveDoubleToRegister extends RegDoubleInstruction  implements Serializable {
    public static final long serialVersionUID = -8885197267150260362L;

    public MoveDoubleToRegister(MoveDoubleToRegister i) {
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

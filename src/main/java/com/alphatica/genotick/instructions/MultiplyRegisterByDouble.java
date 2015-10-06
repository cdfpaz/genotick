package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MultiplyRegisterByDouble extends RegDoubleInstruction  implements Serializable {
    public static final long serialVersionUID = 7017210446264669933L;

    public MultiplyRegisterByDouble(MultiplyRegisterByDouble i) {
        this.setDoubleArgument(i.getDoubleArgument());
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public MultiplyRegisterByDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public MultiplyRegisterByDouble copy() {
        return new MultiplyRegisterByDouble(this);
    }

}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class NaturalLogarithmOfRegister extends RegRegInstruction  implements Serializable {
    public static final long serialVersionUID = -5965927479237202603L;

    public NaturalLogarithmOfRegister(NaturalLogarithmOfRegister i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
    }

    @SuppressWarnings("unused")
    public NaturalLogarithmOfRegister() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public NaturalLogarithmOfRegister copy() {
        return new NaturalLogarithmOfRegister(this);
    }
}

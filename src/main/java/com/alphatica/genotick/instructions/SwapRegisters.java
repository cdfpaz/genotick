package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class SwapRegisters extends RegRegInstruction implements Serializable {
    public static final long serialVersionUID = -3433775138789900573L;

    public SwapRegisters(SwapRegisters i) {
        this.setRegister1(i.getRegister1());
        this.setRegister2(i.getRegister2());
    }

    @SuppressWarnings("unused")
    public SwapRegisters() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public SwapRegisters copy() {
        return new SwapRegisters(this);
    }
}

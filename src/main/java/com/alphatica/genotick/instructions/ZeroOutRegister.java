package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class ZeroOutRegister extends RegInstruction  implements Serializable {
    public static final long serialVersionUID = 7925325642053814475L;

    public ZeroOutRegister(ZeroOutRegister i) {
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public ZeroOutRegister() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public ZeroOutRegister copy() {
        return new ZeroOutRegister(this);
    }
}

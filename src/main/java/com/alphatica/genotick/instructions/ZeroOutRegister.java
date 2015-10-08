package com.alphatica.genotick.instructions;


import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class ZeroOutRegister extends RegInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 7925325642053814475L;

    private ZeroOutRegister(ZeroOutRegister i) {
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

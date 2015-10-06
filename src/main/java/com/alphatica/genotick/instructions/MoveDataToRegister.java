package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveDataToRegister extends DataRegInstruction implements Serializable {
    public static final long serialVersionUID = 6441937261061215492L;

    public MoveDataToRegister(MoveDataToRegister i) {
        this.setDataOffsetIndex(i.getDataOffsetIndex());
        this.setDataTableIndex(i.getDataTableIndex());
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public MoveDataToRegister() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public MoveDataToRegister copy() {
        return new MoveDataToRegister(this);
    }

}

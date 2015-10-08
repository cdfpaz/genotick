package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveRelativeDataToRegister extends DataRegInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -2247072351675972683L;

    private MoveRelativeDataToRegister(MoveRelativeDataToRegister i) {
        this.setDataOffsetIndex(i.getDataOffsetIndex());
        this.setDataTableIndex(i.getDataTableIndex());
        this.setRegister(i.getRegister());
    }

    @SuppressWarnings("unused")
    public MoveRelativeDataToRegister() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public MoveRelativeDataToRegister copy() {
        return new MoveRelativeDataToRegister(this);
    }

}

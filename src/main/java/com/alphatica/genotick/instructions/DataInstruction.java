package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract public class DataInstruction extends Instruction implements Serializable {
    public static final long serialVersionUID = -2955270878126863352L;
    private int dataTableIndex;
    private int dataOffsetIndex;

    public void setDataTableIndex(int dataTableIndex) {
        this.dataTableIndex = dataTableIndex;
    }

    public void setDataOffsetIndex(int dataOffsetIndex) {
        this.dataOffsetIndex = dataOffsetIndex;
    }

    public int getDataTableIndex() {
        return dataTableIndex;
    }

    public int getDataOffsetIndex() {
        return dataOffsetIndex;
    }

    @Override
    public void mutate(Mutator mutator) {
        dataTableIndex = mutator.getNextInt();
        dataOffsetIndex = mutator.getNextInt();
    }
}

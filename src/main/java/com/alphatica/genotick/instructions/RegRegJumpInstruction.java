package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class RegRegJumpInstruction extends  RegRegInstruction implements JumpInstruction, Serializable {
    public static final long serialVersionUID = 714019783545690635L;

    private int address;

    protected RegRegJumpInstruction() {
        address = 0;
    }
    @Override
    public int getAddress() {
        return address;
    }

    public void setAddress(int address) {
        this.address = address;
    }

    @Override
    public void mutate(Mutator mutator) {
        super.mutate(mutator);
        address = mutator.getNextInt();
    }
}

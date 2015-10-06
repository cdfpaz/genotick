package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class RegJumpInstruction extends RegInstruction implements JumpInstruction, Serializable {
    public static final long serialVersionUID = -4386895204308098944L;

    private int address;

    protected RegJumpInstruction() {
        address = 0;
    }
    @Override
    public int getAddress() {
        return address;
    }
    @Override
    public void mutate(Mutator mutator) {
        super.mutate(mutator);
        address = mutator.getNextInt();
    }

    public void setAddress(int address) {
        this.address = address;
    }
}

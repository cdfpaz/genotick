package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class RegDoubleJumpInstruction extends RegDoubleInstruction implements JumpInstruction, Serializable {
    public static final long serialVersionUID = 2490196013277564185L;

    private int address;

    protected RegDoubleJumpInstruction() {
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

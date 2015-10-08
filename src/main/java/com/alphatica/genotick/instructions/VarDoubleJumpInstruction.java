package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class VarDoubleJumpInstruction extends  VarDoubleInstruction implements JumpInstruction, Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 3733708799704002530L;

    private int address;

    VarDoubleJumpInstruction() {
        address = 0;
    }
    @Override
    public int getAddress() {
        return address;
    }

    void setAddress(int address) {
        this.address = address;
    }
    @Override
    public void mutate(Mutator mutator) {
        super.mutate(mutator);
        address = mutator.getNextInt();
    }
}

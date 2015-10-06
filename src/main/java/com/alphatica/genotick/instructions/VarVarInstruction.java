package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;

import java.io.Serializable;

abstract class VarVarInstruction extends Instruction implements Serializable {
    public static final long serialVersionUID = -8461921520321026497L;

    private int variable1Argument;
    private int variable2Argument;

    public int getVariable1Argument() {
        return variable1Argument;
    }

    public void setVariable1Argument(int variable1Argument) {
        this.variable1Argument = variable1Argument;
    }

    public int getVariable2Argument() {
        return variable2Argument;
    }

    public void setVariable2Argument(int variable2Argument) {
        this.variable2Argument = variable2Argument;
    }

    @Override
    public void mutate(Mutator mutator) {
        variable1Argument = mutator.getNextInt();
        variable2Argument = mutator.getNextInt();
    }
}

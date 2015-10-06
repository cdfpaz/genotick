package com.alphatica.genotick.instructions;

import java.io.Serializable;
import java.util.Random;

public class ProgramFunction  implements Serializable {
    public static final long serialVersionUID = -9061849459193572181L;

    private long functionId;
    private final InstructionList instructionList;

    @SuppressWarnings("unused")
    public ProgramFunction() {
        instructionList = InstructionList.createInstructionList();
        functionId = new Random().nextLong();
    }

    public ProgramFunction(long newId, InstructionList instructionList) {
        this.instructionList = instructionList;
        functionId = newId;
    }
    public long getFunctionId() {
        return functionId;
    }

    public InstructionList getInstructionList() {
        return instructionList;
    }
}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class SubtractVariableFromVariable extends VarVarInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -5692114758846719358L;

    private SubtractVariableFromVariable(SubtractVariableFromVariable i) {
        this.setVariable1Argument(i.getVariable1Argument());
        this.setVariable2Argument(i.getVariable2Argument());
    }

    @SuppressWarnings("unused")
    public SubtractVariableFromVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public SubtractVariableFromVariable copy() {
        return new SubtractVariableFromVariable(this);
    }

}

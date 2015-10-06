package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class SqRootOfVariable extends VarVarInstruction  implements Serializable {
    public static final long serialVersionUID = -6025395017177611705L;

    public SqRootOfVariable(SqRootOfVariable i) {
        this.setVariable1Argument(i.getVariable1Argument());
        this.setVariable2Argument(i.getVariable2Argument());
    }

    @SuppressWarnings("unused")
    public SqRootOfVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public SqRootOfVariable copy() {
        return new SqRootOfVariable(this);
    }
}

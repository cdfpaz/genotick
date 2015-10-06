package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MultiplyVariableByVariable extends VarVarInstruction  implements Serializable {
    public static final long serialVersionUID = -2530246252784080647L;

    public MultiplyVariableByVariable(MultiplyVariableByVariable i) {
        this.setVariable1Argument(i.getVariable1Argument());
        this.setVariable2Argument(i.getVariable2Argument());
    }

    @SuppressWarnings("unused")
    public MultiplyVariableByVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public MultiplyVariableByVariable copy() {
        return new MultiplyVariableByVariable(this);
    }

}

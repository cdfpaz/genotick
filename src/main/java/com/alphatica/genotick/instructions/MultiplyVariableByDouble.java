package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MultiplyVariableByDouble extends VarDoubleInstruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = -488671617233131162L;

    private MultiplyVariableByDouble(MultiplyVariableByDouble i) {
        this.setDoubleArgument(i.getDoubleArgument());
        this.setVariableArgument(i.getVariableArgument());
    }

    @SuppressWarnings("unused")
    public MultiplyVariableByDouble() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public MultiplyVariableByDouble copy() {
        return new MultiplyVariableByDouble(this);
    }

}

package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveVariableToVariable extends VarVarInstruction  implements Serializable {
    public static final long serialVersionUID = -8962949754876920077L;

    public MoveVariableToVariable(MoveVariableToVariable i) {
        this.setVariable1Argument(i.getVariable1Argument());
        this.setVariable2Argument(i.getVariable2Argument());
    }

    @SuppressWarnings("unused")
    public MoveVariableToVariable() {
    }

    @Override
    public void executeOn(Processor processor)  {
        processor.execute(this);
    }

    @Override
    public MoveVariableToVariable copy() {
        return new MoveVariableToVariable(this);
    }

}

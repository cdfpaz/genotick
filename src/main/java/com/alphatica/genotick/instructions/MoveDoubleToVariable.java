
package com.alphatica.genotick.instructions;

import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class MoveDoubleToVariable extends VarDoubleInstruction  implements Serializable {
    public static final long serialVersionUID = -1120463586513743256L;

    public MoveDoubleToVariable(MoveDoubleToVariable i) {
        this.setVariableArgument(i.getVariableArgument());
        this.setDoubleArgument(i.getDoubleArgument());
    }

    @SuppressWarnings("unused")
    public MoveDoubleToVariable() {
    }

    @Override
    public void executeOn(Processor processor) {
        processor.execute(this);
    }

    @Override
    public MoveDoubleToVariable copy() {
        return new MoveDoubleToVariable(this);
    }

}

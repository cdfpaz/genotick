package com.alphatica.genotick.instructions;

import com.alphatica.genotick.mutator.Mutator;
import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public class CallFunction extends Instruction implements Serializable {
    public static final long serialVersionUID = 7265758332515595487L;

    private long functionId;

    @SuppressWarnings("unused")
    public CallFunction() {
    }

    public CallFunction(CallFunction i) {
        functionId = i.getFunctionId();
    }

    public long getFunctionId() {
        return functionId;
    }

    @Override
    public void executeOn(Processor processor) {
        //processor.execute(this);
    }
    @Override
    public void mutate(Mutator mutator) {
        functionId = Double.doubleToLongBits(mutator.getNextDouble());
    }

    @Override
    public CallFunction copy() {
        return new CallFunction(this);
    }
}

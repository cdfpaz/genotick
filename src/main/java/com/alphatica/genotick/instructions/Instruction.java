package com.alphatica.genotick.instructions;


import com.alphatica.genotick.mutator.Mutator;
import com.alphatica.genotick.processor.Processor;

import java.io.Serializable;

public abstract class Instruction  implements Serializable {
    @SuppressWarnings("unused")
    private static final long serialVersionUID = 6038774498356414583L;

    abstract public void executeOn(Processor processor);

    abstract public void mutate(Mutator mutator);

    public abstract Instruction copy();
}

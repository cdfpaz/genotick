package com.alphatica.genotick.instructions;

import com.alphatica.genotick.population.ProgramExecutor;

public class Registers {
    public static byte validateRegister(byte register) {
        return (byte)((register < 0 ? -register : register) % ProgramExecutor.totalRegisters);
    }
}

package com.alphatica.genotick.killer;

public class ProgramKillerFactory {
    public static ProgramKiller getDefaultProgramKiller(ProgramKillerSettings killerSettings) {
        ProgramKiller killer = SimpleProgramKiller.getInstance();
        killer.setSettings(killerSettings);
        return killer;
    }
}

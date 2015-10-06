package com.alphatica.genotick.population;

import java.io.Serializable;

public class ProgramName implements Serializable {
    private final static long serialVersionUID = 32136468798664L;
    private final long name;

    public ProgramName(long name) {
        this.name = name;
    }

    public long getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.valueOf(name);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ProgramName that = (ProgramName) o;
        return name == that.getName();
    }

    @Override
    public int hashCode() {
        return (int) (name ^ (name >>> 32));
    }
}

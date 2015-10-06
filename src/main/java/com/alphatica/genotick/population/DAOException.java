package com.alphatica.genotick.population;

class DAOException extends RuntimeException {
    public DAOException(Exception e) {
        super(e);
    }

    public DAOException(String s) {
        super(s);
    }
}

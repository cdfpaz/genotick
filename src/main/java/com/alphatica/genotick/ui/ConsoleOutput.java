package com.alphatica.genotick.ui;

import com.alphatica.genotick.genotick.Application;

class ConsoleOutput implements UserOutput {
    @Override
    public void errorMessage(String message) {
        Application.Logger.d(message);
    }

    @Override
    public void warningMessage(String message) {
        Application.Logger.d(message);
    }
}

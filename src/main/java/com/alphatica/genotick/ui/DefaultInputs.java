package com.alphatica.genotick.ui;

import com.alphatica.genotick.genotick.Application;
import com.alphatica.genotick.genotick.MainSettings;

@SuppressWarnings("unused")
class DefaultInputs implements UserInput {

    @Override
    public void show(Application application) {
        MainSettings defaults = new MainSettings();
        application.start(defaults);
    }


}

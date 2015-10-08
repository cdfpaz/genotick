package com.alphatica.genotick.ui;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("SameParameterValue")
public class Parameters {
    private final Map<String,String> map = new HashMap<>();
    public Parameters(String[] args) {
        for(String arg: args) {
            String key = parseKey(arg);
            String value = parseValue(key,arg);
            map.put(key,value);
        }
    }

    private String parseValue(String key,String arg) {
        int eqPos = key.length();
        return arg.substring(eqPos+1,arg.length());
    }

    private String parseKey(String arg) {
        int eqPos = arg.indexOf("=");
        if(eqPos < 0)
            throw new RuntimeException("Unable to parse " + arg + " as parameter. (Missing '=' sign).");
        return arg.substring(0,eqPos);
    }

    public String getValue(String key) {
        return map.get(key);
    }

    public void removeKey(String key) {
        map.remove(key);
    }

    public boolean allConsumed() {
        return map.isEmpty();
    }

    public Collection<String> getUnconsumed() {
        return map.values();
    }
}

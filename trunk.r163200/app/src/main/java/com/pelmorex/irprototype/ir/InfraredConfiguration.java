package com.pelmorex.irprototype.ir;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class InfraredConfiguration {
    private final Map<String, String> infraredCodes = new HashMap<>();

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public InfraredSignal getInfraredSignal(String codeName) {
        try {
            return InfraredFactory.createInfraredSignalFromString(infraredCodes.get(codeName));
        } catch ( Exception e ) {
            return null;
        }
    }

    public Map<String, String> getInfraredCodes() {
        return infraredCodes;
    }

}

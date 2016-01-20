package com.pelmorex.irprototype.ir;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class InfraredFactory {
    private InfraredFactory() { }

    public static InfraredEmitter createInfraredEmitter(@NonNull Context context) { return new InfraredEmitter(context); }

    public static InfraredSignal createInfraredSignalFromString(@NonNull String string) throws Exception {
        try {
            List<String> dataElements = new ArrayList<>(Arrays.asList(string.split(",")));
            int frequency = Integer.parseInt(dataElements.remove(0));
            int[] data = new int[dataElements.size()];
            for ( int i = 0; i < dataElements.size(); i++ ) { data[i] = Integer.parseInt(dataElements.get(i)); }
            return new InfraredSignal(frequency, data);
        } catch ( NumberFormatException e ) {
            throw new Exception("Parse failed. Incorrect infrared String format.");
        }
    }
}

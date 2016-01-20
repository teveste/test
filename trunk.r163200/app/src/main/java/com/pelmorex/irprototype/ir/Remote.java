package com.pelmorex.irprototype.ir;

import android.content.Context;
import android.support.annotation.NonNull;

import com.pelmorex.irprototype.util.MathUtil;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class Remote {
    private final InfraredEmitter infraredEmitter;
    private final RemoteConfiguration remoteConfiguration;

    public Remote(@NonNull Context context, @NonNull RemoteConfiguration remoteConfiguration)
            throws IllegalArgumentException {
        infraredEmitter = InfraredFactory.createInfraredEmitter(context);
        this.remoteConfiguration = remoteConfiguration;
    }

    public void turnOnTv() {
        if ( hasTv() ) {
            emitIrSignalToTv("POWER ON");
        }
    }

    public void turnOffTv() {
        if ( hasTv() ) {
            emitIrSignalToTv("POWER OFF");
        }
    }

    public void powerToggleStb() {
        if ( hasStb() ) {
            emitIrSignalToStb("POWER TOGGLE");
        }
    }

    public void tuneChannelOnTv(int channel) {
        for ( int digit : MathUtil.getBase10Digits(channel) ) {
            emitIrSignalToTv("DIGIT " + digit);
        }
    }

    public void tuneChannelOnStb(int channel) {
        for ( int digit : MathUtil.getBase10Digits(channel) ) {
            emitIrSignalToStb("DIGIT " + digit);
        }
    }

    public void tuneTwnChannel() {
        if ( remoteConfiguration.getTwnChannel() != null ) {
            if ( hasStb() ) {
                tuneChannelOnStb(remoteConfiguration.getTwnChannel());
            } else if ( hasTv() ) {
                tuneChannelOnTv(remoteConfiguration.getTwnChannel());
            }
        }
    }

    public boolean hasTv() {
        return remoteConfiguration.getTvIrConfig() != null;
    }

    public boolean hasStb() {
        return remoteConfiguration.getStbIrConfig() != null;
    }

    public void emitIrSignalToTv(String functionName) {
        infraredEmitter.emitUsingMs(remoteConfiguration.getTvIrConfig().getInfraredSignal(functionName));
    }

    public void emitIrSignalToStb(String functionName) {
        infraredEmitter.emitUsingMs(remoteConfiguration.getStbIrConfig().getInfraredSignal(functionName));
    }
}

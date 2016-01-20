package com.pelmorex.irprototype.ir;

import android.content.Context;
import android.hardware.ConsumerIrManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Arrays;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class InfraredEmitter {
    private static final String TAG = "INFRARED EMITTER";
    private final ConsumerIrManager consumerIrManager;
    private final Executor executor = Executors.newSingleThreadExecutor();

    public InfraredEmitter(@NonNull Context context) {
        this.consumerIrManager = (ConsumerIrManager) context.getSystemService(Context.CONSUMER_IR_SERVICE);
        if ( android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT ){
            Log.w(TAG, "CURRENT VERSION OF ANDROID MIGHT NOT SUPPORT IR EMISSION.");
        }
    }

    public void emitUsingCounts(InfraredSignal infraredSignal) {
        if ( consumerIrManager != null && consumerIrManager.hasIrEmitter() ) {
            try {
                emitSignal(infraredSignal.getFrequency(), infraredSignal.getDataAsCounts());
            } catch ( Exception e ) {
                Log.e(TAG, "AN EXCEPTION OCCURRED WHILE EMITTING. ABORTING EMISSION. " + e.getMessage());
            }
        } else { Log.e(TAG, "NO INFRARED EMITTER FOUND. ABORTING EMISSION REQUEST."); }
    }

    public void emitUsingMs(InfraredSignal infraredSignal) {
        if ( consumerIrManager != null && consumerIrManager.hasIrEmitter() ) {
            try {
                emitSignal(infraredSignal.getFrequency(), infraredSignal.getDataAsMs());
            } catch ( Exception e ) {
                Log.e(TAG, "AN EXCEPTION OCCURRED WHILE EMITTING. ABORTING EMISSION. " + e.getMessage());
            }
        } else { Log.e(TAG, "NO INFRARED EMITTER FOUND. ABORTING EMISSION REQUEST."); }
    }

    public boolean isFrequencyInCarrierRange(int frequency) {
        ConsumerIrManager.CarrierFrequencyRange[] cfr = consumerIrManager.getCarrierFrequencies();
        for ( ConsumerIrManager.CarrierFrequencyRange range : cfr ) {
            if ( frequency >= range.getMinFrequency() && frequency <= range.getMaxFrequency() ) {
                return true;
            }
        }
        return false;
    }

    private void emitSignal(final int frequency, final int[] data) {
        if ( consumerIrManager != null && consumerIrManager.hasIrEmitter() ) {
            if ( isFrequencyInCarrierRange(frequency) ) {
                try {
                    executor.execute(new Runnable() {
                        @Override
                        public void run() {
                            Log.i(TAG, "EMITTING [" + frequency + " Hz] " + Arrays.toString(data));
                            for ( int i = 0; i < 3; ++i ) {
                                consumerIrManager.transmit(frequency, data);
                            }
                        }
                    });
                } catch ( Exception e ) { Log.e(TAG, "AN EXCEPTION OCCURRED WHILE EMITTING. ABORTING EMISSION. " + e.getMessage()); }
            } else { Log.e(TAG, "REQUESTED FREQUENCY IS OUTSIDE OF CARRIER RANGE. ABORTING EMISSION REQUEST."); }
        } else { Log.e(TAG, "NO INFRARED EMITTER FOUND. ABORTING EMISSION REQUEST."); }
    }
}

package com.pelmorex.irprototype.application;

import android.app.Application;

import com.pelmorex.irprototype.ir.CustomInfraredFactory;
import com.pelmorex.irprototype.ir.RemoteConfiguration;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class IRApplication extends Application {
    private RemoteConfiguration remoteConfig = CustomInfraredFactory.createSampleRemoteConfiguration();

    public void setRemoteConfiguration(RemoteConfiguration remoteConfig) {
        this.remoteConfig = remoteConfig;
    }

    public RemoteConfiguration getRemoteConfiguration() {
        return remoteConfig;
    }
}

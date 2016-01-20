package com.pelmorex.irprototype.ir;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class RemoteConfiguration {
    private Integer twnChannel;
    private InfraredConfiguration tvIrConfig;
    private InfraredConfiguration stbIrConfig;

    public Integer getTwnChannel() {
        return twnChannel;
    }

    public void setTwnChannel(Integer twnChannel) {
        this.twnChannel = twnChannel;
    }

    public InfraredConfiguration getTvIrConfig() {
        return tvIrConfig;
    }

    public void setTvIrConfig(InfraredConfiguration config) {
        tvIrConfig = config;
    }

    public InfraredConfiguration getStbIrConfig() {
        return stbIrConfig;
    }

    public void setStbIrConfig(InfraredConfiguration config) {
        stbIrConfig = config;
    }
}

package com.pelmorex.irprototype.ir;

/**
 * Created by hbenzamane on 1/12/2016.
 */
public class InfraredSignal {
    private int[] dataAsCounts;
    private int frequency;
    private int[] dataAsMs;

    public InfraredSignal(int frequency, int[] dataAsCounts) {
        setFrequency(frequency);
        setDataAsCounts(dataAsCounts);
        setDataAsMs(count2Ms(frequency, dataAsCounts));
    }

    public int getFrequency() { return frequency; }
    public int[] getDataAsCounts() { return dataAsCounts; }
    public int[] getDataAsMs() { return dataAsMs; }

    public void setFrequency(int frequency) { this.frequency = frequency; }
    public void setDataAsCounts(int[] dataAsCounts) { this.dataAsCounts = dataAsCounts; }
    public void setDataAsMs(int[] dataAsMs) { this.dataAsMs = dataAsMs; }

    private int[] ms2Count(final int frequencyHz, int[] dataMs) {
        int[] dataCount = new int[dataMs.length];
        double pulsesPerSecond = 1000000 / frequencyHz;
        for ( int i = 0; i < dataMs.length; ++i ) { dataMs[i] = (int) ( dataMs[i] * pulsesPerSecond ); }
        return dataCount;
    }

    private int[] count2Ms(final int frequencyHz, final int[] dataCount) {
        int[] dataMs = new int[dataCount.length];
        double n = 1000000 / frequencyHz;
        for ( int i = 0; i < dataCount.length; ++i ) { dataMs[i] = (int) ( dataCount[i] * n ); }
        return dataMs;
    }
}

package com.simscale.webapp;

public class Request {
    private long id;
    private String timestamp;
    private int start;
    private int end;
    private long timeElapsed;
    private String algorithmType;
    private int numberOfPrimes;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public long getTimeElapsed() {
        return timeElapsed;
    }

    public void setTimeElapsed(long timeElapsed) {
        this.timeElapsed = timeElapsed;
    }

    public String getAlgorithmType() {
        return algorithmType;
    }

    public void setAlgorithmType(String algorithmType) {
        this.algorithmType = algorithmType;
    }

    public int getNumberOfPrimes() {
        return numberOfPrimes;
    }

    public void setNumberOfPrimes(int numberOfPrimes) {
        this.numberOfPrimes = numberOfPrimes;
    }
}

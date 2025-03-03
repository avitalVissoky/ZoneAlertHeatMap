package com.example.zonealertheatmap.Entities;


public class LocationData {

    private int totalVisits;
    private long totalDuration;
    private double averageDuration;

    public LocationData(){}

    public int getTotalVisits() {
        return totalVisits;
    }

    public long getTotalDuration() {
        return totalDuration;
    }

    public double getAverageDuration() {
        return averageDuration;
    }

}

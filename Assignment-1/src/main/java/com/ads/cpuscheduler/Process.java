package com.ads.cpuscheduler;

public class Process {
    private String processId;
    private int arrivalTime;
    private int burstTime;

    public Process(String processId, int arrivalTime, int burstTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
    }

    public String getProcessId() {
        return processId;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getBurstTime() {
        return burstTime;
    }

    @Override
    public String toString() {
        return "Process ID: " + processId + ", Arrival Time: " + arrivalTime + ", Burst Time: " + burstTime;
    }
}

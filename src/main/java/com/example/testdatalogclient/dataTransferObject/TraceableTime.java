package com.example.testdatalogclient.dataTransferObject;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.management.MemoryUsage;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TraceableTime {
    private long startTime;
    private long endTime;
    private String methodName;
    private double cpuUsage;
    private String heapMemoryUsage;
    private String nonHeapMemoryUsage;

    public TraceableTime(long startTime, long endTime, String methodName){
        this.startTime=startTime;
        this.endTime=endTime;
        this.methodName=methodName;
    }
}
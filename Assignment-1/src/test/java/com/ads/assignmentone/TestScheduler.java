package com.ads.assignmentone;

import java.util.Comparator;
import java.util.List;

import com.ads.cpuscheduler.Process;
import com.ads.cpuscheduler.ProcessScheduler;

public class TestScheduler {
	public static void main(String[] args) {
		
		String []datasetSize = {"10","100","1000","10000","50000"};
		
		for(String size: datasetSize) {
			System.out.println(size);
			String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset_"+ size +"_Entries.xlsx";
	    	
	    	ProcessScheduler queueScheduler = new ProcessScheduler(false,100000);
	        List<Process> processes = queueScheduler.loadProcessesFromExcel(excelFilePath);
	        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
	        queueScheduler.addToQueue(processes);
	        long queueTime = queueScheduler.scheduleUsingQueue();
	        
	        ProcessScheduler stackScheduler = new ProcessScheduler(true,100000);
	        stackScheduler.addToStack(processes);
	        long stackTime = stackScheduler.scheduleUsingStack();
	        
	        System.out.println("Queue Scheduling Time for: " +  size +" : "+ queueTime + " ms");
	        System.out.println("Stack Scheduling Time for "+ size +" : " + stackTime + " ms");

	        System.out.println((queueTime < stackTime ? "Queue" : "Stack") + " performed better for the size "+size+".");
		}
		
	}
}

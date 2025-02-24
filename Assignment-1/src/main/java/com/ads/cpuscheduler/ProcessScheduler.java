package com.ads.cpuscheduler;

import com.ads.custom.CustomQueue;  // Import CustomQueue
import com.ads.custom.CustomStack; // Import CustomStack
 
import com.ads.custom.Stack;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProcessScheduler {
    private CustomQueue<Process> processQueue = new CustomQueue<>();
    private Stack<Process> processStack = new CustomStack<>();
    private boolean useStack;

    public ProcessScheduler(boolean useStack) {
        this.useStack = useStack;
    }

    // Load processes from an Excel file
    public List<Process> loadProcessesFromExcel(String filePath) {
    	List<Process> processes = new ArrayList<>();
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // processes are in the first sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String processId = row.getCell(0).getStringCellValue();
                int arrivalTime = (int) row.getCell(1).getNumericCellValue();
                int burstTime = (int) row.getCell(2).getNumericCellValue();

                Process process = new Process(processId, arrivalTime, burstTime);
                processes.add(process);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return processes;
    }
    
    public void addToQueue(List<Process> processes) {
    	
   	 for(Process p : processes) {
        	this.processQueue.enqueue(p);
        }
       
   }
    
    public void addToStack(List<Process> processes) {
   	 for(Process p : processes) {
         	this.processStack.push(p);;
         }
   }
    
    // Simulate FIFO CPU scheduling using queue
    public long scheduleUsingQueue() {
    	long startTime = System.currentTimeMillis();
    	while(! this.processQueue.isEmpty()) {
    		Process process = this.processQueue.dequeue();
    		// Simulate process execution
          try {
              Thread.sleep((long)process.getBurstTime());
             // System.out.println("Process executed : "+process.getProcessId()+" Process Arrival Time: "+ process.getArrivalTime());
          } catch (Exception e) {
              e.printStackTrace();
          }
    	}
    	long endTime = System.currentTimeMillis();
    	long executionTime = endTime - startTime;
       System.out.println("All processes scheduled in " + executionTime + " ms using Queue.");
        return executionTime;
       
    }
    
    // Simulate FIFO CPU scheduling using stack
    public long scheduleUsingStack() {
    	long startTime = System.currentTimeMillis();
    	
    	while(! this.processStack.isEmpty()) {
    		CustomStack<Process> reverseStack = new CustomStack<>();
    		while(! this.processStack.isEmpty()) {
        		Process process = this.processStack.pop();
        		reverseStack.push(process);
        	}
    		
    		try 
    		{
          		Process process = reverseStack.pop();
          		Thread.sleep((long)process.getBurstTime());
	    		while(! reverseStack.isEmpty()) {
	    			this.processStack.push(reverseStack.pop());
		   	    }
    		}
    		catch(Exception e) {
                e.printStackTrace();
            }
    	}
    	long endTime = System.currentTimeMillis();
    	long executionTime = endTime - startTime;
        System.out.println("All processes scheduled in " + executionTime + " ms using " + (useStack ? "Stack" : "Queue") + ".");
        return executionTime;
    }


    public static void main(String[] args) {
    	String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset_10_Entries.xlsx";
    	
    	ProcessScheduler queueScheduler = new ProcessScheduler(false);
        List<Process> processes = queueScheduler.loadProcessesFromExcel(excelFilePath);
        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
        queueScheduler.addToQueue(processes);
        long queueTime = queueScheduler.scheduleUsingQueue();
        
        ProcessScheduler stackScheduler = new ProcessScheduler(true);
        stackScheduler.addToStack(processes);
        long stackTime = stackScheduler.scheduleUsingStack();
        
        System.out.println("Queue Scheduling Time: " + queueTime + " ms");
        System.out.println("Stack Scheduling Time: " + stackTime + " ms");

        System.out.println((queueTime < stackTime ? "Queue" : "Stack") + " performed better.");
    }
}

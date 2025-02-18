package com.ads.cpuscheduler;

import com.ads.custom.CustomQueue;  // Import CustomQueue
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
    
    // Simulate FIFO CPU scheduling
    public void scheduleUsingQueue() {
    	long startTime = System.currentTimeMillis();
    	while(! this.processQueue.isEmpty()) {
    		Process process = this.processQueue.dequeue();
    		// Simulate process execution
          try {
              Thread.sleep((long)process.getBurstTime());
              System.out.println("Process executed : "+process.getProcessId()+" Process Arrival Time: "+ process.getArrivalTime());
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
    	}
    	long endTime = System.currentTimeMillis();
    	long executionTime = endTime - startTime;
        System.out.println("All processes scheduled in " + executionTime + " ms using Queue.");
       
    }


    public static void main(String[] args) {
    	String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset__1000_Entries_.xlsx";
    	
    	//Using Queue
        ProcessScheduler queueScheduler = new ProcessScheduler(false);
        List<Process> processes = queueScheduler.loadProcessesFromExcel(excelFilePath);
        processes.sort(Comparator.comparingInt(p -> p.getArrivalTime()));
        queueScheduler.addToQueue(processes);
        queueScheduler.scheduleUsingQueue();
    }
}

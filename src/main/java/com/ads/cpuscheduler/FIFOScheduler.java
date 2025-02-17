//package com.ads.cpuscheduler;
//
//import com.ads.custom.CustomQueue;  // Import CustomQueue
//import com.ads.custom.Stack;
//
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class FIFOScheduler {
//    private CustomQueue<Process> processQueue = new CustomQueue<>();
//
//    // Load processes from an Excel file
//    public void loadProcessesFromExcel(String filePath) {
//        try (FileInputStream fis = new FileInputStream(new File(filePath));
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0); // Assume processes are in the first sheet
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue; // Skip header row
//
//                String processId = row.getCell(0).getStringCellValue();
//                int arrivalTime = (int) row.getCell(1).getNumericCellValue();
//                int burstTime = (int) row.getCell(2).getNumericCellValue();
//
//                Process process = new Process(processId, arrivalTime, burstTime);
//                processQueue.enqueue(process);
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Simulate FIFO CPU scheduling
//    public void schedule() {
//        System.out.println("Starting FIFO CPU Scheduling with queue...");
//        while (!processQueue.isEmpty()) {
//            Process process = processQueue.dequeue();
//            System.out.println("Processing: " + process);
//            // Simulate process execution
//            try {
//                Thread.sleep(process.getBurstTime() * 10L); // 1 second per burst time unit
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        System.out.println("All processes have been scheduled.");
//    }
//
//    public static void main(String[] args) {
//        FIFOScheduler scheduler = new FIFOScheduler();
//        String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset__1000_Entries_.xlsx"; // Replace with actual path
//        scheduler.loadProcessesFromExcel(excelFilePath);
//        scheduler.schedule();
//    }
//}


//package com.ads.cpuscheduler;
//
//import com.ads.custom.CustomQueue;
//import com.ads.custom.CustomStack;
//import org.apache.poi.ss.usermodel.*;
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//
//public class FIFOScheduler {
//    private CustomQueue<Process> processQueue = new CustomQueue<>();
//    private CustomStack<Process> processStack = new CustomStack<>();
//    private boolean useStack;
//
//    public FIFOScheduler(boolean useStack) {
//        this.useStack = useStack;
//    }
//
//    public void loadProcessesFromExcel(String filePath) {
//        try (FileInputStream fis = new FileInputStream(new File(filePath));
//             Workbook workbook = new XSSFWorkbook(fis)) {
//
//            Sheet sheet = workbook.getSheetAt(0);
//            for (Row row : sheet) {
//                if (row.getRowNum() == 0) continue;
//
//                String processId = row.getCell(0).getStringCellValue();
//                int arrivalTime = (int) row.getCell(1).getNumericCellValue();
//                int burstTime = (int) row.getCell(2).getNumericCellValue();
//
//                Process process = new Process(processId, arrivalTime, burstTime);
//                if (useStack) {
//                    processStack.push(process);
//                } else {
//                    processQueue.enqueue(process);
//                }
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
//
//    public long schedule() {
//        System.out.println("Starting " + (useStack ? "Stack" : "Queue") + " CPU Scheduling...");
//        long startTime = System.currentTimeMillis();
//        
//        while (useStack ? !processStack.isEmpty() : !processQueue.isEmpty()) {
//            Process process = useStack ? processStack.pop() : processQueue.dequeue();
//            System.out.println("Processing: " + process);
//            try {
//                Thread.sleep(process.getBurstTime() * 10L);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        
//        long endTime = System.currentTimeMillis();
//        long executionTime = endTime - startTime;
//        System.out.println("All processes scheduled in " + executionTime + " ms using " + (useStack ? "Stack" : "Queue") + ".");
//        return executionTime;
//    }
//
//    public static void main(String[] args) {
//        String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset__1000_Entries_.xlsx";
//
//        FIFOScheduler queueScheduler = new FIFOScheduler(false);
//        queueScheduler.loadProcessesFromExcel(excelFilePath);
//        long queueTime = queueScheduler.schedule();
//
//        FIFOScheduler stackScheduler = new FIFOScheduler(true);
//        stackScheduler.loadProcessesFromExcel(excelFilePath);
//        long stackTime = stackScheduler.schedule();
//
//        System.out.println("Queue Scheduling Time: " + queueTime + " ms");
//        System.out.println("Stack Scheduling Time: " + stackTime + " ms");
//
//        System.out.println((queueTime < stackTime ? "Queue" : "Stack") + " performed better.");
//    }
//}


package com.ads.cpuscheduler;

import com.ads.custom.CustomQueue;
import com.ads.custom.CustomStack;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FIFOScheduler {
    public CustomQueue<Process> processQueue = new CustomQueue<>();
    public CustomStack<Process> processStack = new CustomStack<>();
    private boolean useStack;

    public FIFOScheduler(boolean useStack) {
        this.useStack = useStack;
    }

    public void loadProcessesFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue;

                String processId = row.getCell(0).getStringCellValue();
                int arrivalTime = (int) row.getCell(1).getNumericCellValue();
                int burstTime = (int) row.getCell(2).getNumericCellValue();

                Process process = new Process(processId, arrivalTime, burstTime);
                if (useStack) {
                    processStack.push(process);  // Push to stack (FIFO behavior)
                } else {
                    processQueue.enqueue(process);  // Enqueue to queue
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public long schedule() {
        System.out.println("Starting " + (useStack ? "Stack" : "Queue") + " CPU Scheduling...");
        long startTime = System.currentTimeMillis();
        
        while (useStack ? !processStack.isEmpty() : !processQueue.isEmpty()) {
            Process process = useStack ? processStack.pop() : processQueue.dequeue();
            System.out.println("Processing: " + process);
            try {
                Thread.sleep(process.getBurstTime() * 10L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        long endTime = System.currentTimeMillis();
        long executionTime = endTime - startTime;
        System.out.println("All processes scheduled in " + executionTime + " ms using " + (useStack ? "Stack" : "Queue") + ".");
        return executionTime;
    }

    public static void main(String[] args) {
        String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset__1000_Entries_.xlsx";

        FIFOScheduler queueScheduler = new FIFOScheduler(false);
        queueScheduler.loadProcessesFromExcel(excelFilePath);
        long queueTime = queueScheduler.schedule();

        FIFOScheduler stackScheduler = new FIFOScheduler(true);
        stackScheduler.loadProcessesFromExcel(excelFilePath);
        long stackTime = stackScheduler.schedule();

        System.out.println("Queue Scheduling Time: " + queueTime + " ms");
        System.out.println("Stack Scheduling Time: " + stackTime + " ms");

        System.out.println((queueTime < stackTime ? "Queue" : "Stack") + " performed better.");
    }
}

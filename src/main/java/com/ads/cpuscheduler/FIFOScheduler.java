package com.ads.cpuscheduler;

import com.ads.custom.CustomQueue;  // Import CustomQueue
import com.ads.custom.Stack;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FIFOScheduler {
    private CustomQueue<Process> processQueue = new CustomQueue<>();

    // Load processes from an Excel file
    public void loadProcessesFromExcel(String filePath) {
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheetAt(0); // Assume processes are in the first sheet
            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header row

                String processId = row.getCell(0).getStringCellValue();
                int arrivalTime = (int) row.getCell(1).getNumericCellValue();
                int burstTime = (int) row.getCell(2).getNumericCellValue();

                Process process = new Process(processId, arrivalTime, burstTime);
                processQueue.enqueue(process);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Simulate FIFO CPU scheduling
    public void schedule() {
        System.out.println("Starting FIFO CPU Scheduling...");
        while (!processQueue.isEmpty()) {
            Process process = processQueue.dequeue();
            System.out.println("Processing: " + process);
            // Simulate process execution
            try {
                Thread.sleep(process.getBurstTime() * 10L); // 1 second per burst time unit
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("All processes have been scheduled.");
    }

    public static void main(String[] args) {
        FIFOScheduler scheduler = new FIFOScheduler();
        String excelFilePath = "src/main/resources/CPU_Scheduling_Dataset__1000_Entries_.xlsx"; // Replace with actual path
        scheduler.loadProcessesFromExcel(excelFilePath);
        scheduler.schedule();
    }
}

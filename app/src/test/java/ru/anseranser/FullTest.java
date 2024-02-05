package ru.anseranser;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

public class FullTest {

    public static void main(String[] args) {
        long startTime;
        long endTime;
        long result1;
        String inputFile = "D:\\inc\\Download\\ip_addresses\\ip_addresses";


        startTime = System.currentTimeMillis();
        result1 = IPCounter.countIPs(inputFile, false, true, false);
        endTime = System.currentTimeMillis();
        System.out.println("106 Gb, 8 Billions ip addresses with ip address check");
        System.out.printf("Total count of unique IP Addresses in %s = %,d%n", inputFile, result1);
        System.out.printf("Execution time: %s %n", formatDuration(endTime - startTime, "HH:mm:ss.SSS"));


        startTime = System.currentTimeMillis();
        result1 = IPCounter.countIPs(inputFile, false, false, false);
        endTime = System.currentTimeMillis();
        System.out.println("106 Gb, 8 Billions ip addresses WITHOUT ip address check");
        System.out.printf("Total count of unique IP Addresses in %s = %,d%n", inputFile, result1);
        System.out.printf("Execution time: %s %n", formatDuration(endTime - startTime, "HH:mm:ss.SSS"));
    }


}

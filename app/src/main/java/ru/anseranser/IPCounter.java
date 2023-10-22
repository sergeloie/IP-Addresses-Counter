package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class IPCounter {

    public static long countIPs(String inputFile, boolean displayCount) {

        long lineCounter = 0;
        BitSet ipset0127 = new BitSet(Integer.MAX_VALUE);
        BitSet ipset128255 = new BitSet(Integer.MAX_VALUE);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                if (IPUtils.isIPv4Address(line)) {
                    long ipAddress = IPUtils.ipToLong(line);
                    if (ipAddress < Integer.MAX_VALUE) {
                        ipset0127.set((int) ipAddress);
                    } else {
                        ipset128255.set((int) (ipAddress - Integer.MAX_VALUE));
                    }
                }
                if (displayCount) {
                    lineCounter++;
                    System.out.printf("line number = %,d | IP Address = %s%n", lineCounter, line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipset0127.cardinality() + ipset128255.cardinality();
    }
}

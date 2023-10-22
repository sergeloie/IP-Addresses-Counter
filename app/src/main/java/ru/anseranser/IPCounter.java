package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

import static ru.anseranser.IPUtils.ipToLong;
import static ru.anseranser.IPUtils.isIPv4Address;
import static ru.anseranser.IPUtils.longToIp;

public class IPCounter {

    public static long countIPs(String inputFile, boolean displayCount) {

        long lineCounter = 0;
        BitSet ipset0127 = new BitSet(Integer.MAX_VALUE);
        BitSet ipset128255 = new BitSet(Integer.MAX_VALUE);

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                if (isIPv4Address(line)) {
                    putIPinBitSet(line, ipset0127, ipset128255);
                }
                if (displayCount) {
                    System.out.printf("line number = %,d | IP Address = %s%n", ++lineCounter, line);
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipset0127.cardinality() + ipset128255.cardinality();
    }

    public static void putIPinBitSet(String ipAddress, BitSet ipset0127, BitSet ipset128255) {
        long ipNumericValue = ipToLong(ipAddress);
        if (ipNumericValue < Integer.MAX_VALUE) {
            ipset0127.set((int) ipNumericValue);
        } else {
            ipset128255.set((int) (ipNumericValue - Integer.MAX_VALUE));
        }
    }

    public static String getIPfromBitSet(long ipNumericValue, BitSet ipset0127, BitSet ipset128255) {
        String result = longToIp(ipNumericValue);
        if (ipNumericValue < Integer.MAX_VALUE && ipset0127.get((int) ipNumericValue)) {
            return result;
        } else if (ipNumericValue > Integer.MAX_VALUE && ipset128255.get((int) (ipNumericValue - Integer.MAX_VALUE))) {
            return result;
        } else {
            return String.format("%s is not in the set", result);
        }
    }

}

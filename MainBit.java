package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.BitSet;

public class MainBit {
    public static void main(String[] args) {

        BitSet ipset_0_127 = new BitSet(Integer.MAX_VALUE);
        BitSet ipset_128_255 = new BitSet(Integer.MAX_VALUE);

        try {
            BufferedReader reader = new BufferedReader(new FileReader("D:\\inc\\Download\\ip_addresses\\ip_addresses"));
            String line = reader.readLine();
            while (line != null) {
                long ipAddress = ipToLong(line);
                if (ipAddress < Integer.MAX_VALUE) {
                    ipset_0_127.set((int) ipAddress);
                } else {
                    ipset_128_255.set((int) (ipAddress - Integer.MAX_VALUE));
                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        long result = ipset_0_127.cardinality() + ipset_128_255.cardinality();
        System.out.println(result);

    }

    public static long ipToLong(String ipAddress) {
        String[] octetStrings = ipAddress.split("\\.");
        long result = 0;

        for (int i = 0; i < octetStrings.length; i++) {
            long octetValue = Long.parseLong(octetStrings[i]);
            result += octetValue << (8 * (3 - i));
        }

        return result;
    }
}

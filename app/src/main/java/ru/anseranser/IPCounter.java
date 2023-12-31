package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.anseranser.IPUtils.isIPv4Address;


class IPCounter {

    static long countIPs(String inputFile, boolean displayCount, boolean verifyIP, boolean displayMega) {


        QuadSet quadSet = new QuadSet();
        long lineCounter = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                if (verifyIP) {
                    if (isIPv4Address(line)) {
                        quadSet.set(line);
                    }
                } else {
                    quadSet.set(line);
                }

                lineCounter++;

                if (displayCount) {
                    System.out.printf("line number = %,d | IP Address = %s%n", lineCounter, line);
                }

                if (displayMega) {
                    if (lineCounter % 1_000_000 == 0) {
                        System.out.printf("lineCounter = %,d | ipSet.cardinality = %,d%n",
                                lineCounter,
                                quadSet.cardinality());
                    }
                }

                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
        return quadSet.cardinality();
    }


}

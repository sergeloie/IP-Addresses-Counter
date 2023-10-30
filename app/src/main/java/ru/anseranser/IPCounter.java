package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.anseranser.IPUtils.isIPv4Address;


class IPCounter {

    static long countIPs(String inputFile, boolean displayCount) {


        //IPSet ipSet = new IPSet();
        QuadSet quadSet = new QuadSet();
        long lineCounter = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                if (isIPv4Address(line)) {
                    //ipSet.set(line);
                    quadSet.set(line);
                }
                if (displayCount) {
                    System.out.printf("line number = %,d | IP Address = %s%n", ++lineCounter, line);
                }
//                lineCounter++;
//                if (lineCounter % 1_000_000 == 0) {
//                    System.out.printf("lineCounter = %,d | ipSet.cardinality = %,d%n", lineCounter, quadSet.size());
//                }
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {

            e.printStackTrace();
        }
//        return ipSet.cardinality();
        return quadSet.size();
    }
}

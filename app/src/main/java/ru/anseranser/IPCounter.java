package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.anseranser.IPUtils.isIPv4Address;


class IPCounter {

    static long countIPs(String inputFile, boolean displayCount) {

        IPStorage ipStorage = new IPStorage();
        long lineCounter = 0;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                if (isIPv4Address(line)) {
                    ipStorage.put(line);
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
        return ipStorage.count();
    }
}

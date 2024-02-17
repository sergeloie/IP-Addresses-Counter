package ru.anseranser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static ru.anseranser.IPConverter.textToNumericFormatV4;


class IPCounter {

    static long countIPs(String inputFile, boolean displayCount, boolean displayMega) {

        QuadSet quadSet = new QuadSet();

        long lineCounter = 0;
        long currentLineValue;

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line = reader.readLine();
            while (line != null) {
                currentLineValue = textToNumericFormatV4(line);
                if (currentLineValue != -1) {
                    quadSet.set(currentLineValue);
                }
                lineCounter++;

                if (displayCount) {
                    System.out.printf("line number = %,d | IP Address = %s%n", lineCounter, line);
                }

                if (displayMega && (lineCounter % 1_000_000 == 0)) {
                    System.out.printf("lineCounter = %,d | ipSet.cardinality = %,d%n",
                            lineCounter,
                            quadSet.cardinality());

                }

                line = reader.readLine();

            }
            return quadSet.cardinality();
        } catch (IOException e) {
            throw new IllegalStateException("Невозможно прочитать файл", e);
        }


    }
}

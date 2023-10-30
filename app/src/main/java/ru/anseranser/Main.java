package ru.anseranser;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine;


public class Main {

    @Command
        (name = "ipCounter", version = "ipCounter 1.0", mixinStandardHelpOptions = true)
    public static final class App implements Runnable {

        @Parameters
            (paramLabel = "FILE", description = "Input file containing IP addresses")
        private String inputFile;

        @Option
            (names = "-d", description = "Display line number and IP address")
        private boolean displayCount;


        @Override
        public void run() {
            long startTime = System.currentTimeMillis();

            long result = IPCounter.countIPs(inputFile, displayCount);
            System.out.printf("Total count of unique IP Addresses in %s = %,d%n", inputFile, result);

            long endTime = System.currentTimeMillis();

            System.out.printf("Execution time: %,d ms%n", endTime - startTime);
        }
    }

    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);


    }
}

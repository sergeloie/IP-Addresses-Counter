package ru.anseranser;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine;

import static org.apache.commons.lang3.time.DurationFormatUtils.formatDuration;

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

        @Option
            (names = "-m", description = "for large files, displays information on every millionth line")
        private boolean displayMega;


        @Override
        public void run() {
            long startTime = System.currentTimeMillis();
            long result1 = IPCounter.countIPs(inputFile, displayCount, displayMega);
            System.out.printf("Total count of unique IP Addresses in %s = %,d%n", inputFile, result1);
            long endTime = System.currentTimeMillis();
            System.out.printf("Execution time: %s %n", formatDuration(endTime - startTime, "HH:mm:ss.SSS"));

        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);
    }
}

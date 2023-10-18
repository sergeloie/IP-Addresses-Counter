package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class Main {

    private static long ipGlobalCount = 0;
    private static long currentLine = 0;

    @Command(name = "ipCounter", version = "ipCounter 1.0", mixinStandardHelpOptions = true)
    public static class App implements Runnable {

        @Parameters(paramLabel = "FILE", description = "Input file containing IP addresses", defaultValue = "./ip_addresses")
        String inputFile;

        @Option(names = "-c", description = "Display current count of unique IP addresses")
        boolean displayCount;

        @Option(names = "-n", description = "Display line number of the input file")
        boolean displayLineNumber;

        @Option(names = "-i", description = "Display current IP address")
        boolean displayIPAddress;


        @Override
        public void run() {
            ipNode rootNode = new ipNode();

            try {
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                String line = reader.readLine();
                while (line != null) {
                    makeBranch(rootNode, line);
                    displayInfo(line, displayLineNumber, displayIPAddress, displayCount);
                    line = reader.readLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + inputFile);
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.printf("Final count of unique IP addresses in %s = %d%n", inputFile, ipGlobalCount);
        }
    }

    public static void main(String[] args) {

        int exitCode = new CommandLine(new App()).execute(args);
        System.exit(exitCode);


    }

    static void makeBranch(ipNode root, String line) {
        currentLine++;
        ipNode node = root;
        int[] ipArr = Arrays.stream(line.split("\\.")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i <= 2; i++) {
            if (node.children == null) {
                node.children = new HashMap<>();
            }
            if (!node.children.containsKey(ipArr[i])) {
                node.children.put(ipArr[i], new ipNode());
            }
            node = node.children.get(ipArr[i]);
        }
//      проверка четвёртого октета
        if (node.children == null) {
            node.children = new HashMap<>();
        }
        if (!node.children.containsKey(ipArr[3])) {
            node.children.put(ipArr[3], null);
            ipGlobalCount++;

        }
    }

    private static void displayInfo(String ipAddress,
                                    boolean displayLineNumber,
                                    boolean displayIPAddress,
                                    boolean displayCount) {
        StringBuilder infoBuilder = new StringBuilder();
        if (displayLineNumber) {
            infoBuilder.append(String.format("Line number = %,d | ", currentLine));
        }
        if (displayIPAddress) {
            infoBuilder.append(String.format("IP address = %s | ", ipAddress));
        }
        if (displayCount) {
            infoBuilder.append(String.format("Current ipGlobalCount = %,d", ipGlobalCount));
        }
        System.out.println(infoBuilder);
    }
    static class ipNode {
        public Map<Integer, ipNode> children;
    }
}


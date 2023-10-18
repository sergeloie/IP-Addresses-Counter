package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import picocli.CommandLine;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

public class Main {

    private static long ipGlobalCount = 0;
    private static long currentLine = 0;

    @Option(names = "-c", description = "Display current count of unique IP addresses")
    private static boolean displayCount;

    @Option(names = "-n", description = "Display line number of the input file")
    private static boolean displayLineNumber;

    @Option(names = "-i", description = "Display current IP address")
    private static boolean displayIPAddress;

    @Parameters(paramLabel = "FILE", description = "Input file containing IP addresses", defaultValue = ".\\ip_addresses")
    private static String inputFile;


    public static void main(String[] args) {

        ipNode rootNode = new ipNode();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            String line = reader.readLine();
            while (line != null) {
                makeBranch(rootNode, line);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ipGlobalCount);
    }

    public static void makeBranch(ipNode root, String line) {
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
            displayInfo(line);
        }
    }

    private static void displayInfo(String ipAddress) {
        StringBuilder infoBuilder = new StringBuilder();
        if (displayLineNumber) {
            infoBuilder.append(String.format("Line number = %d | ", currentLine));
        }
        if (displayIPAddress) {
            infoBuilder.append(String.format("IP address = %s | ", ipAddress));
        }
        if (displayCount) {
            infoBuilder.append(String.format("Current line number = %d", ipGlobalCount));
        }
        if (infoBuilder != null) {
            System.out.println(infoBuilder.toString());
        }
        }
    }

    class ipNode {
        public Map<Integer, ipNode> children;
    }

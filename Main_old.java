package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Main {

    static int ipGlobalCount = 0;

    public static void main(String[] args) {

        ipNode rootNode = new ipNode();

        try {
            BufferedReader reader = new BufferedReader(new FileReader("Z:\\CSV\\ipaddresses.txt"));
            String line = reader.readLine();
            while (line != null) {
                int[] ipArr = Arrays.stream(line.split("\\.")).mapToInt(Integer::parseInt).toArray();
                makeBranch(rootNode, ipArr);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(ipGlobalCount);
    }

    public static void makeBranch(ipNode root, int[] ipArr) {
        ipNode node = root;
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
            ipGlobalCount++;
            node.children.put(ipArr[3], null);
        }
    }

    public static class ipNode {
        public Map<Integer, ipNode> children;

        @Override
        public String toString() {
            return String.format("children = %s%n", this.children.keySet());
        }
    }
}

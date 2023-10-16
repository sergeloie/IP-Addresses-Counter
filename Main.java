package org.example;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        String ipAddress1 = "192.068.000.001";
        String ipAddress11 = "192.068.000.002";
        String ipAddress2 = "125.57.17.29";
        int[] ipArr1 = Arrays.stream(ipAddress1.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int[] ipArr11 = Arrays.stream(ipAddress11.split("\\.")).mapToInt(Integer::parseInt).toArray();
        int[] ipArr2 = Arrays.stream(ipAddress2.split("\\.")).mapToInt(Integer::parseInt).toArray();
        System.out.println(Arrays.toString(ipArr1));

        ipNode rootNode = new ipNode();
        int number = 0;
        //rootNode.children = new HashMap<>();
        makeBranch(rootNode, ipArr1);
        makeBranch(rootNode, ipArr2);
        makeBranch(rootNode, ipArr11);
        makeBranch(rootNode, ipArr2);
        makeBranch(rootNode, ipArr2);
        makeBranch(rootNode, ipArr2);
        System.out.println(rootNode);
        System.out.println(rootNode.children.values());
        printTree(rootNode, new StringBuilder());
        countTree(rootNode, number);
        System.out.println(number);

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
            node.children.put(ipArr[3], null);
        }
    }

    public static void printTree(ipNode node, StringBuilder sb) {
//        System.out.println(node);
//        System.out.println(sb.toString());
        if (node == null) {
            System.out.print("node == null - ");
            System.out.println("sb = " + sb.toString());
        }
        if (node != null && node.children != null) {
            for(Map.Entry entry : node.children.entrySet()) {
                System.out.println("node.children.values = " + node.children.values());
                printTree((ipNode) entry.getValue(), new StringBuilder(sb).append(".").append(entry.getKey()));
            }
        }

    }
    public static void countTree(ipNode node, int count) {
        System.out.println(count);
        if (node != null && node.children != null) {
            for(Map.Entry entry : node.children.entrySet()) {
                System.out.println("node.children.values = " + node.children.values());
                if (node.children.values() == null) {
                    count++;
                } else {
                    countTree((ipNode) entry.getValue(), count);
                }

            }
        }
    }
}

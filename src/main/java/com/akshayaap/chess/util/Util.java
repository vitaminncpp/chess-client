package com.akshayaap.chess.util;

public class Util {
    public void printMap(boolean[][] map) {
        System.out.println("################BEGIN MAP################");
        for (boolean[] i : map) {
            for (boolean j : i) {
                if (j) {
                    System.out.print("  ");
                } else {
                    System.out.println(" X");
                }
            }
            System.out.println();
        }
        System.out.println("#################END MAP#################");
    }
}

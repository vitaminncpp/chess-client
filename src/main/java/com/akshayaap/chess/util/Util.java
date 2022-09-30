package com.akshayaap.chess.util;

import com.akshayaap.chess.game.Move;
import com.akshayaap.chess.model.MoveMessage;

public class Util {
    public static MoveMessage convertToMoveMessage(Move move) {
        MoveMessage mvMsg = new MoveMessage();
        mvMsg.setX1(move.getX1());
        mvMsg.setY1(move.getY1());
        mvMsg.setX2(move.getX2());
        mvMsg.setY2(move.getY2());

        mvMsg.setTurn(move.getTurn());
        mvMsg.setState(move.getState());
        mvMsg.setCheckState(move.getCheckState());

        return mvMsg;
    }

    public static void printMap(boolean[][] map) {
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

    public static Move convertToMove(MoveMessage mvMsg) {
        Move move = new Move();
        move.setSource(mvMsg.getX1(), mvMsg.getY1());
        move.setDestination(mvMsg.getX2(), mvMsg.getY2());
        move.setState(mvMsg.getState());
        move.setCheckState(mvMsg.getCheckState());
        return move;
    }
}

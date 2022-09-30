package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.Piece;
import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.game.util.PromotionCallback;
import com.akshayaap.chess.util.ResourceManager;

import javax.swing.*;

public class Promotion implements PromotionCallback {

    private static Object[] objsW;
    private static Object[] objsB;

    static {
        objsW = new Object[]{ResourceManager.getResourceManager().getWhiteQ(), ResourceManager.getResourceManager().getWhiteR(), ResourceManager.getResourceManager().getWhiteB(), ResourceManager.getResourceManager().getWhiteN(), ResourceManager.getResourceManager().getWhiteP(), ResourceManager.getResourceManager().getWhiteK()};
        objsB = new Object[]{ResourceManager.getResourceManager().getBlackQ(), ResourceManager.getResourceManager().getBlackR(), ResourceManager.getResourceManager().getBlackB(), ResourceManager.getResourceManager().getBlackN(), ResourceManager.getResourceManager().getBlackP(), ResourceManager.getResourceManager().getBlackK()};
    }


    @Override
    public void prompt(Player player) {
        Object p = JOptionPane.showInputDialog(null, "Promote Pawn to", "Promotion Time !", JOptionPane.QUESTION_MESSAGE, player.getColor() ? ResourceManager.getResourceManager().getWhiteQ() : ResourceManager.getResourceManager().getWhiteQ(), player.getColor() ? objsW : objsB, player.getColor() ? objsW[0] : objsB[0]);
        if (p == objsW[0] || p == objsB[0]) {
            player.promot(Piece.QUEEN_TYPE);
        } else if (p == objsW[1] || p == objsB[1]) {
            player.promot(Piece.ROOK_TYPE);
        } else if (p == objsW[2] || p == objsB[2]) {
            player.promot(Piece.BISHOP_TYPE);
        } else if (p == objsW[3] || p == objsB[3]) {
            player.promot(Piece.KNIGHT_TYPE);
        } else if (p == objsW[4] || p == objsB[4]) {
            player.promot(Piece.PAWN_TYPE);
        } else if (p == objsW[5] || p == objsB[5]) {
            player.promot(Piece.KING_TYPE);
        } else {
            System.out.println("Invalid Input");
            prompt(player);
        }
    }
}

package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.CaptureCallBack;
import com.akshayaap.chess.game.Piece;
import com.akshayaap.chess.game.Player;
import com.akshayaap.chess.util.ResourceManager;

import javax.swing.*;
import java.awt.*;

public class CaptureWindow extends JPanel implements CaptureCallBack {
    private Player player;

    public CaptureWindow(Player player) {
        this.player = player;
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setBorder(BorderFactory.createTitledBorder("Captured Pieces"));
        this.setPreferredSize(new Dimension(150, 200));
    }

    @Override
    public void capture(Piece piece) {

        add(new JLabel(ResourceManager.getResourceManager().getPieceImage((piece.getColor() ? 1 : 0), piece.getType())));
        validate();
    }
}

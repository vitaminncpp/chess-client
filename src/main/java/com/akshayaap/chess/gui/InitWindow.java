package com.akshayaap.chess.gui;

import javax.swing.*;
import java.awt.*;

public class InitWindow {

    private static final Dimension WIN_SIZE = new Dimension(400, 400);

    private final JFrame frame;
    private final JPanel colorPanel = new JPanel();
    private final JPanel nameConfig = new JPanel();
    private final JPanel gameMode = new JPanel();


    public InitWindow() {
        frame = new JFrame("Start  a New Game");
        frame.setPreferredSize(WIN_SIZE);
        frame.setLayout(new GridLayout(2, 3));
        frame.add(colorPanel);
        frame.add(nameConfig);
        frame.add(gameMode);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(true);

    }
}

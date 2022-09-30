package com.akshayaap.chess.gui;

import javax.swing.*;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;

public class ChessControlPanel extends JPanel {
    private final ChessGui game;
    private final JButton newGame = new JButton("New Game");
    private final JButton open = new JButton("Open");
    private final JButton save = new JButton("Save");
    private final JButton reset = new JButton("Reset");
    private final JButton undo = new JButton("Undo");
    private final JButton redo = new JButton("Redo");
    private final JButton exit = new JButton("Exit");
    private final JButton about = new JButton("About");
    private final JButton connect = new JButton("Connect");

    public ChessControlPanel(ChessGui game) {
        this.game = game;
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        add(newGame);
        add(connect);
        add(open);
        add(save);
        add(reset);
        add(undo);
        add(redo);
        add(exit);
        add(about);
        validate();

        reset.addActionListener(e -> game.reset());
        connect.addActionListener(e -> {
            String uri = JOptionPane.showInputDialog("Enter URI to the server");
            try {
                game.getClient().connectToServer(uri);
                JOptionPane.showMessageDialog(game.getGameFrame(), "Hurrey !");
            } catch (ExecutionException | InterruptedException | URISyntaxException ex) {
                game.getLogger().log(Arrays.toString(ex.getStackTrace()));
            }
        });

    }
}

package com.akshayaap.chess.gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    private final JButton register = new JButton("Register");

    private JTextField uri = new JTextField();
    private JTextField name = new JTextField();
    private JTextField opponent = new JTextField();


    public ChessControlPanel(ChessGui game) {
        this.game = game;
        JPanel config = new JPanel();
        config.setLayout(new BoxLayout(config, BoxLayout.X_AXIS));
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        config.add(new JLabel("Server URL"));
        config.add(uri);
        config.add(connect);
        config.add(new JLabel("Your Name"));
        config.add(name);
        config.add(new JLabel("Your Opponent"));
        config.add(opponent);
        config.add(register);

        buttons.add(newGame);
        buttons.add(open);
        buttons.add(save);
        buttons.add(reset);
        buttons.add(undo);
        buttons.add(redo);
        buttons.add(exit);
        buttons.add(about);
        add(config);
        add(buttons);
        validate();

        uri.setText("ws://localhost:8080/chess-server");
        reset.addActionListener(e -> game.reset());
        connect.addActionListener(e -> {
            try {
                game.getClient().connectToServer(uri.getText());
                game.getLogger().log("Successfully connected to server");
            } catch (ExecutionException | InterruptedException | URISyntaxException ex) {
                game.getLogger().log(Arrays.toString(ex.getStackTrace()));
            }
        });
        register.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.getClient().subscribe(name.getText());
                game.setPayers(name.getText(),opponent.getText());
                game.getLogger().log("Successfully SubScribed to user EndPoint");
                String playas=JOptionPane.showInputDialog("Play As...");
                if(playas.charAt(0)=='B'||playas.charAt(0)=='b'){
                    game.getState().setPlayer(false);
                }
                else{
                    game.getState().setPlayer(true);
                }
            }
        });
    }
}

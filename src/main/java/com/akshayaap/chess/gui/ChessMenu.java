package com.akshayaap.chess.gui;

import javax.swing.*;

public class ChessMenu extends JMenuBar {
    private final ChessGui game;
    private final JMenu file = new JMenu("File");
    private final JMenuItem newGame = new JMenuItem("New Game");
    private final JMenuItem open = new JMenuItem("Open");
    private final JMenuItem save = new JMenuItem("Save");
    private final JMenuItem reset = new JMenuItem("Reset");
    private final JMenuItem undo = new JMenuItem("Undo");
    private final JMenuItem redo = new JMenuItem("Redo");
    private final JMenuItem exit = new JMenuItem("Exit");
    private final JMenu help = new JMenu("Help");
    private final JMenuItem about = new JMenuItem("About");
    private final JMenuItem helpContents = new JMenuItem("Help Contents");
    private final JMenuItem checkForUpdates = new JMenuItem("Check for Updates");
    private final JMenuItem reportBug = new JMenuItem("Report Bug");
    private final JMenuItem contactUs = new JMenuItem("Contact Us");
    private final JMenuItem website = new JMenuItem("Website");
    private final JMenuItem facebook = new JMenuItem("Facebook");
    private final JMenuItem twitter = new JMenuItem("Twitter");
    private final JMenuItem youtube = new JMenuItem("YouTube");
    private final JMenuItem reddit = new JMenuItem("Reddit");
    private final JMenuItem github = new JMenuItem("Github");
    private final JMenuItem discord = new JMenuItem("Discord");
    private final JMenuItem redditChess = new JMenuItem("Reddit Chess");
    private final JMenuItem redditChessRules = new JMenuItem("Reddit Chess Rules");
    private final JMenuItem redditChessTournaments = new JMenuItem("Reddit Chess Tournaments");
    private final JMenuItem redditChessMoves = new JMenuItem("Reddit Chess Moves");
    private final JMenuItem redditChessPuzzles = new JMenuItem("Reddit Chess Puzzles");
    private final JMenuItem redditChessStrategies = new JMenuItem("Reddit Chess Strategies");

    public ChessMenu(ChessGui game) {
        this.game = game;
        file.add(newGame);
        file.add(open);
        file.add(save);
        file.add(reset);
        file.add(undo);
        file.add(redo);
        file.add(exit);
        help.add(about);
        help.add(helpContents);
        help.add(checkForUpdates);
        help.add(reportBug);
        help.add(contactUs);
        help.add(website);
        help.add(facebook);
        help.add(twitter);
        help.add(youtube);
        help.add(reddit);
        help.add(github);
        help.add(discord);
        help.add(redditChess);
        help.add(redditChessRules);
        help.add(redditChessTournaments);
        help.add(redditChessMoves);
        help.add(redditChessPuzzles);
        help.add(redditChessStrategies);
        add(file);
        add(help);
        validate();
    }
}

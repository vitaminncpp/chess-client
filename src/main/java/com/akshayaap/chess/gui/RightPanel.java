package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.ChessGame;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

public class RightPanel extends JPanel {
    private ChessGame game;
    private ChatPane chatPane = new ChatPane();
    private StatusBar statusBar = new StatusBar();

    public RightPanel(ChessGame game) {
        super();
        setLayout(new BorderLayout());
        //setPreferredSize(new Dimension(400, 800));
        this.game = game;
        add(statusBar, BorderLayout.NORTH);
        add(chatPane, BorderLayout.SOUTH);
        validate();
    }


    public void update() {
        statusBar.update();
    }

    private class StatusBar extends JPanel {
        private final JLabel status = new JLabel("Start The Game");
        private final Font font = new Font("Arial", Font.BOLD, 20);
        private final JLabel turn = new JLabel("Turn: ");
        private final JLabel turnValue = new JLabel("White");
        private final JLabel turnColor = new JLabel("Color: ");

        public StatusBar() {
            super();
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            //setSize(new Dimension(400, 400));
            setBorder(BasicBorders.getInternalFrameBorder());

            status.setFont(font);
            status.setForeground(Color.RED);
            add(status);
            add(turn);
            add(turnValue);
            add(turnColor);

            validate();
        }

        public void update() {
            switch (game.getState().getState()) {
                case NORMAL_STATE -> status.setText("Normal");
                case SELECTED_STATE -> status.setText("Setected Piece: " + game.getState().getChXPrev() + " " + game.getState().getChYPrev());
                case INVALID_STATE -> status.setText("INVALID");

            }
            switch (game.getState().getCheckState()) {
                case WHITE_CHECKMATE:
                    status.setText("White Checkmate");
                    break;
                case BLACK_CHECKMATE:
                    status.setText("Black Checkmate");
                    break;
                case WHITE_CHECK:
                    status.setText("White Check");
                    break;
                case BLACK_CHECK:
                    status.setText("Black Check");
                    break;
                case WHITE_STALEMATE:
                    status.setText("White Stalemate");
                    break;
                case BLACK_STALEMATE:
                    status.setText("Black Stalemate");
                    break;
            }
            if (game.getState().getTurn()) {
                turnColor.setText("Color: White");
            } else {
                turnColor.setText("Color: Black");
            }
            validate();
        }
    }

    private class ChatPane extends JPanel {
        private final JButton send = new JButton("Send");
        private final JScrollPane scrollPane;
        private final JPanel chat;
        private JTextField message = new JTextField();
        private int messageCount = 0;

        public ChatPane() {
            super();
            chat = new JPanel();
            //chat.setSize(new Dimension(400, 520));
            //padding for BoxLayout
            chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
            scrollPane = new JScrollPane(chat);
            scrollPane.setWheelScrollingEnabled(true);
            scrollPane.setPreferredSize(new Dimension(400, 520));
            scrollPane.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
                public void adjustmentValueChanged(AdjustmentEvent e) {
                    e.getAdjustable().setValue(e.getAdjustable().getMaximum());
                }
            });
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            setBorder(BasicBorders.getInternalFrameBorder());
            send.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JLabel next = new JLabel("Message :#" + ++messageCount);
                    next.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
                    //next.setSize(new Dimension(400, 80));
                    next.setFont(new Font("Arial", Font.PLAIN, 15));
                    chat.add(next);
                    chat.validate();
                    validate();
                }
            });
            //message.setPreferredSize(new Dimension(400, 40));
            add(scrollPane);
            add(message);
            add(send);
            validate();
        }
    }
}

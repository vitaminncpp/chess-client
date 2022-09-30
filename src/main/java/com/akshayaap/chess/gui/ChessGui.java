package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.ChessGame;
import com.akshayaap.chess.game.Move;
import com.akshayaap.chess.game.util.ChessState;
import com.akshayaap.chess.model.ChatMessage;
import com.akshayaap.chess.model.MoveMessage;
import com.akshayaap.chess.network.Client;
import com.akshayaap.chess.util.ChessActionListener;
import com.akshayaap.chess.util.ResourceManager;
import com.akshayaap.chess.util.Util;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class ChessGui {
    private final Promotion promotionCallback = new Promotion();
    private final JFrame gameFrame;
    private final JPanel gamePanel = new JPanel();
    private final JMenuBar menuBar;
    private final BoardPanel boardPanel;
    private final ChessGame game;
    Client client;
    private ChessControlPanel controlPanel;
    private RightPanel rightPanel;
    private CaptureWindow captureCallBackBlack = null;
    private CaptureWindow captureCallBackWhite = null;
    private Move move = new Move();
    private Logger logger = new Logger();
    private State state = new State();

    public ChessGui() throws IOException, ExecutionException, InterruptedException {
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.game = new ChessGame();
        captureCallBackWhite = new CaptureWindow(game.getPlayerWhite());
        captureCallBackBlack = new CaptureWindow(game.getPlayerBlack());
        game.setPromotionCallback(promotionCallback);
        game.getPlayerWhite().setCaptrueCallback(captureCallBackWhite);
        game.getPlayerBlack().setCaptrueCallback(captureCallBackBlack);
        game.setLogable(logger);
        this.gameFrame = new JFrame("A Chess Game !");
        this.gameFrame.setLayout(new FlowLayout());
        this.menuBar = new ChessMenu(this);
        this.gameFrame.setJMenuBar(this.menuBar);
        this.gameFrame.setVisible(true);
        this.gameFrame.setResizable(false);
        this.gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        this.boardPanel = new BoardPanel();
        this.gamePanel.setLayout(new BorderLayout());
        controlPanel = new ChessControlPanel(this);
        this.gamePanel.add(controlPanel, BorderLayout.NORTH);
        this.gamePanel.add(this.boardPanel, BorderLayout.CENTER);
        this.gamePanel.add(captureCallBackWhite, BorderLayout.WEST);
        this.gamePanel.add(captureCallBackBlack, BorderLayout.EAST);
        this.gamePanel.add(logger, BorderLayout.SOUTH);

        this.gameFrame.add(this.gamePanel, BorderLayout.CENTER);
        rightPanel = new RightPanel(game);
        controlPanel = new ChessControlPanel(this);
        this.gameFrame.add(rightPanel);

        this.gameFrame.validate();
        this.gameFrame.pack();


        client = new Client(logger);
        client.addActionListener(new ChessActionListener() {
            @Override
            public void onMoveReceived(MoveMessage msgMove) {
                //TODO Action Performed
                logger.log("Action is about to performed");
                Move move = Util.convertToMove(msgMove);
                move=game.move(move);
                logger.log(move.toString());
                switch (move.getState()) {
                    case NORMAL_MOVE, CAPTURE_MOVE, CHECK_MOVE, PROMOTION_MOVE -> {
                        ChessGui.this.state.toggleTurn();
                        logger.log("Moved from Remote");
                    }
                    case ILLEGAL_MOVE -> {
                        logger.log("Remote Made illegal move");
                    }
                }
                ChessGui.this.state.reset();
                ChessGui.this.update();
            }

            @Override
            public void onChatMessageReceived(ChatMessage message) {
                //TODO Action Performed
            }
        });

        update();
        this.render();
    }

    public JFrame getGameFrame() {
        return gameFrame;
    }

    public Logger getLogger() {
        return logger;
    }

    public ChessGame getGame() {
        return game;
    }

    public void render() {
        this.boardPanel.render();
        gameFrame.validate();
    }

    private void update() {
        rightPanel.update();
        this.render();
    }

    public void reset() {
        game.reset();
        state.reset();
        state.setTurn(true);
        move.reset();
        captureCallBackWhite.removeAll();
        captureCallBackBlack.removeAll();
        captureCallBackWhite.revalidate();
        captureCallBackBlack.revalidate();
        captureCallBackBlack.repaint();
        captureCallBackWhite.repaint();
        update();
        render();
    }

    public Client getClient() {
        return this.client;
    }

    public void setPayers(String name, String opponent) {
        this.state.setName(name);
        this.state.setOpponent(opponent);
    }


    public String getName() {
        return this.state.getName();
    }

    public String getOpponent() {
        return this.state.getOpponent();
    }

    public State getState() {
        return this.state;
    }

    private class BoardPanel extends JPanel {
        public static final Dimension BOARD_DIMENSION;

        static {
            BOARD_DIMENSION = new Dimension(550, 550);
        }

        private final TilePanel[][] grid;

        public BoardPanel() {
            super(new GridLayout(8, 8));
            this.grid = new TilePanel[8][8];
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.grid[i][j] = new TilePanel(i, j);
                    this.add(this.grid[i][j]);
                }
            }
            this.setPreferredSize(BOARD_DIMENSION);
            setVisible(true);
            validate();
        }

        public void render() {
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    this.grid[i][j].render();
                }
            }
            this.setAnnotatted();
            validate();
        }

        public void setAnnotatted() {
            int x = ChessGui.this.state.getChXPrev();
            int y = ChessGui.this.state.getChYPrev();
            if (ChessGui.this.move == null) {
                return;
            }
            switch (ChessGui.this.move.getState()) {
                case INVALID_SELECTION:
                    break;
                case NORMAL_MOVE:
                    this.grid[ChessGui.this.move.getX1()][ChessGui.this.move.getY1()].setSourceTile();
                    this.grid[ChessGui.this.move.getX2()][ChessGui.this.move.getY2()].setDestinationTile();
                    break;
                case CAPTURE_MOVE:
                    this.grid[ChessGui.this.move.getX1()][ChessGui.this.move.getY1()].setSourceTile();
                    this.grid[ChessGui.this.move.getX2()][ChessGui.this.move.getY2()].setThreatenTile();
                default:
                    break;
            }
            switch (state.getCheckState()) {
                case WHITE_CHECKMATE:
                    this.grid[ChessGui.this.game.getPlayerWhite().getPieces()[5][0].getX()][ChessGui.this.game.getPlayerWhite().getPieces()[5][0].getY()].setCheckMateTile();
                    break;
                case BLACK_CHECKMATE:
                    this.grid[ChessGui.this.game.getPlayerBlack().getPieces()[5][0].getX()][ChessGui.this.game.getPlayerBlack().getPieces()[5][0].getY()].setCheckMateTile();
                    break;
                case WHITE_CHECK:
                    this.grid[ChessGui.this.game.getPlayerWhite().getPieces()[5][0].getX()][ChessGui.this.game.getPlayerWhite().getPieces()[5][0].getY()].setCheckTile();
                    break;
                case BLACK_CHECK:
                    this.grid[ChessGui.this.game.getPlayerBlack().getPieces()[5][0].getX()][ChessGui.this.game.getPlayerBlack().getPieces()[5][0].getY()].setCheckTile();
                    break;
                case WHITE_STALEMATE:
                    break;
                case BLACK_STALEMATE:
                    break;

                default:
                    break;
            }
            switch (ChessGui.this.state.getState()) {
                case SELECTED_STATE:
                    this.grid[ChessGui.this.state.getChXPrev()][ChessGui.this.state.getChYPrev()].setSelectTile();
                    for (int i = 0; i < 8; i++) {
                        for (int j = 0; j < 8; j++) {
                            if (ChessGui.this.game.getPiece(ChessGui.this.state.getChXPrev(), ChessGui.this.state.getChYPrev()).getMoveMap()[i][j]) {
                                this.grid[i][j].setPossibleTile();
                            }
                        }
                    }
                    break;
                case EMPTY_SELECTION:
                    this.grid[ChessGui.this.state.getChXPrev()][ChessGui.this.state.getChYPrev()].setEmptyTile();
                    break;
                case INVALID_SELECTION:
                    this.grid[ChessGui.this.state.getChXPrev()][ChessGui.this.state.getChYPrev()].setInvalidTile();
                    break;
            }
        }
    }

    private class TilePanel extends JPanel {
        public static final Color DESTINATION_TILE = new Color(1, 255, 255);
        public static final Color THREATEN_TILE = new Color(255, 221, 75, 255);
        public static final Color CHECK_TILE = new Color(255, 118, 45);
        public static final Color CHECKMATE_TILE = new Color(255, 0, 0);
        private static final Dimension TILE_DIMENSION = new Dimension(10, 10);
        private static final Color DARK_SQUARE = new Color(50, 0, 20);
        private static final Color LIGHT_SQUARE = new Color(255, 255, 200);
        private static final Color SELECT_TILE = new Color(131, 255, 77);
        private static final Color SOURCE_TILE = new Color(110, 132, 241);
        private static final Color EMPTY_TILE = new Color(135, 135, 135);
        private static final Color INVALID_TILE = new Color(68, 107, 113);

        private static final Color light = new Color(156, 156, 156);
        private static final Color dark = new Color(97, 97, 97);
        private static final Color lightHigh = new Color(255, 255, 255);
        private static final Color darkHigh = new Color(0, 0, 0);


        private final int x;
        private final int y;


        public TilePanel(int x, int y) {
            super();
            setLayout(new OverlayLayout(this));
            this.x = x;
            this.y = y;
            setPreferredSize(TILE_DIMENSION);
            setBackground((this.x + this.y) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
            setVisible(true);
            addMouseListener(new Input());
            Border border = new BasicBorders.FieldBorder(dark, light, lightHigh, darkHigh);
            setBorder(border);
            validate();
        }

        public void render() {
            removeAll();
            setBackground((this.x + this.y) % 2 == 0 ? LIGHT_SQUARE : DARK_SQUARE);
            if (ChessGui.this.game.getPiece(this.x, this.y) != null) {
                JLabel label = new JLabel(ResourceManager.getResourceManager().getPieceImage(ChessGui.this.game.getPiece(this.x, this.y).getColor() ? 1 : 0, ChessGui.this.game.getPiece(this.x, this.y).getType()));
                label.setAlignmentX(CENTER_ALIGNMENT);
                label.setAlignmentY(CENTER_ALIGNMENT);

                add(label);
            }
            revalidate();
            repaint();
        }

        public void setSourceTile() {
            this.setBackground(SOURCE_TILE);
        }

        public void setDestinationTile() {
            this.setBackground(DESTINATION_TILE);
        }

        public void setSelectTile() {
            this.setBackground(SELECT_TILE);
        }

        public void setPossibleTile() {
            JLabel label = new JLabel(ResourceManager.getResourceManager().getCircle());
            label.setAlignmentX(CENTER_ALIGNMENT);
            label.setAlignmentY(CENTER_ALIGNMENT);
            this.add(label);
        }

        public void setThreatenTile() {
            this.setBackground(THREATEN_TILE);
        }

        public void setCheckTile() {
            this.setBackground(CHECK_TILE);
        }

        public void setCheckMateTile() {
            this.setBackground(CHECKMATE_TILE);
        }

        public void setEmptyTile() {
            this.setBackground(EMPTY_TILE);
        }

        public void setInvalidTile() {
            this.setBackground(INVALID_TILE);
        }

        private class Input implements MouseListener {

            @Override
            public void mouseClicked(MouseEvent e) {
                //throw new UnsupportedOperationException("This bullshit is not supported yet you fool");
            }


            @Override
            public void mousePressed(MouseEvent e) {
                if (ChessGui.this.state.getTurn() != ChessGui.this.state.getPlayer()) {
                    return;
                }
                Move move = null;
                switch (ChessGui.this.state.getState()) {
                    case INVALID_STATE:
                        break;
                    case EMPTY_SELECTION:
                    case INVALID_SELECTION:
                    case NORMAL_STATE:
                        ChessGui.this.state.reset();
                        ChessGui.this.state.setChXYPrev(TilePanel.this.x, TilePanel.this.y);
                        if (game.getPiece(TilePanel.this.x, TilePanel.this.y) == null) {
                            ChessGui.this.state.setState(ChessState.EMPTY_SELECTION);
                        } else if (game.getPiece(TilePanel.this.x, TilePanel.this.y).getColor() == ChessGui.this.state.getTurn()) {
                            ChessGui.this.state.setState(ChessState.SELECTED_STATE);
                        } else {
                            ChessGui.this.state.setState(ChessState.INVALID_SELECTION);
                        }
                        break;
                    case SELECTED_STATE:
                        if (game.getPiece(TilePanel.this.x, TilePanel.this.y) != null && game.getPiece(TilePanel.this.x, TilePanel.this.y).getColor() == ChessGui.this.state.getTurn()) {
                            ChessGui.this.state.reset();
                            ChessGui.this.state.setState(ChessState.SELECTED_STATE);
                            ChessGui.this.state.setChXYPrev(TilePanel.this.x, TilePanel.this.y);
                        } else {
                            ChessGui.this.state.setChXY(TilePanel.this.x, TilePanel.this.y);
                            move = new Move();
                            move.setTurn(ChessGui.this.state.getTurn());
                            move.setSource(ChessGui.this.state.getChXPrev(), ChessGui.this.state.getChYPrev());
                            move.setDestination(ChessGui.this.state.getChX(), ChessGui.this.state.getChY());
                            move.setState(ChessState.NORMAL_MOVE);
                            ChessGui.this.move = ChessGui.this.game.move(move);
                            switch (ChessGui.this.move.getState()) {
                                case NORMAL_MOVE, CAPTURE_MOVE, CHECK_MOVE, PROMOTION_MOVE -> {
                                    ChessGui.this.state.toggleTurn();
                                    MoveMessage msgMove = Util.convertToMoveMessage(ChessGui.this.move);
                                    msgMove.setSender(ChessGui.this.state.getName());
                                    msgMove.setReceiver(ChessGui.this.state.getOpponent());
                                    client.sendMoveMessage(msgMove);
                                }
                                case ILLEGAL_MOVE -> {
                                    game.getLogger().log("Illegal move");
                                }
                                default -> {
                                }
                            }
                            ChessGui.this.state.reset();
                        }
                        break;
                    default:
                        break;
                }
                ChessGui.this.update();
            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        }
    }
}

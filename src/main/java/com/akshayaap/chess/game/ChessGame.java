package com.akshayaap.chess.game;

import com.akshayaap.chess.game.util.ChessState;
import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.gui.Promotion;

import java.util.ArrayList;
import java.util.List;

public class ChessGame {
    private final State state;
    private final ChessBoard board;
    private final Player playerWhite;
    private final Player playerBlack;
    private Move move = new Move();
    private Logable logger;
    private List<Move> moveHistory = new ArrayList<>();

    public ChessGame() {
        this.state = new State();
        this.board = new ChessBoard();

        this.playerBlack = new Player(false, this.board.getBoard());
        this.playerWhite = new Player(true, this.board.getBoard());
        this.playerBlack.setOpponent(this.playerWhite);
        this.playerWhite.setOpponent(this.playerBlack);

        this.playerWhite.updateAttackMap();
        this.playerBlack.updateAttackMap();
        this.playerWhite.updateMoveMap();
        this.playerBlack.updateMoveMap();
    }

    public Player getPlayerWhite() {
        return playerWhite;
    }


    @Deprecated
    public Move onClick(int x, int y) {
        move.reset();
        switch (this.state.getState()) {
            case INVALID_STATE:
                logger.log("Invalid state");
                move.setState(ChessState.INVALID_SELECTION);
                move.setSource(x, y);
                move.setDestination(-1, -1);
                break;
            case NORMAL_STATE:
                if (this.board.getPiece(x, y) == null) {
                    logger.log("No piece selected");
                    move.setState(ChessState.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                } else if (this.state.getTurn() == this.board.getPiece(x, y).getColor()) {
                    logger.log("Piece Selected:" + this.board.getPiece(x, y).getType() + " " + this.board.getPiece(x, y).getColor() + " " + x + " " + y);
                    this.state.setChXYPrev(x, y);
                    this.state.setState(ChessState.SELECTED_STATE);
                    this.state.setChXY(-1, -1);
                    this.state.setChXYNext(-1, -1);
                    move.setState(ChessState.SELECTED_STATE);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                    move.setTurn(this.state.getTurn());
                    //move.setMap(board.getPiece(x, y).getMoveMap());
                } else {
                    logger.log("Invalid selection");
                    move.setState(ChessState.INVALID_SELECTION);
                    move.setSource(x, y);
                    move.setDestination(-1, -1);
                }
                break;
            case SELECTED_STATE:
                if (this.board.getPiece(x, y) == null) {
                    this.state.setChXY(x, y);
                    move = this.board.moveTo(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                    this.state.fallBack();
                    move.setTurn(this.state.getTurn());
                    if (move.getState() != ChessState.ILLEGAL_MOVE && move.getState() != ChessState.EMPTY_SELECTION && move.getState() != ChessState.INVALID_SELECTION && move.getState() != ChessState.NOT_APPLICABLE) {
                        logger.log("Normal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                        this.state.toggleTurn();
                        logger.log("Turn:" + this.state.getTurn());
                        update();
                    } else {
                        logger.log("Illegal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                    }
                } else {
                    if (this.board.getPiece(x, y).getColor() != this.state.getTurn()) {
                        this.state.setChXY(x, y);
                        move = this.board.moveTo(this.state.getChXPrev(), this.state.getChYPrev(), x, y);
                        this.state.fallBack();
                        move.setTurn(this.state.getTurn());
                        if (move.getState() != ChessState.ILLEGAL_MOVE && move.getState() != ChessState.EMPTY_SELECTION && move.getState() != ChessState.INVALID_SELECTION && move.getState() != ChessState.NOT_APPLICABLE) {
                            move.setState(ChessState.CAPTURE_MOVE);
                            logger.log("Capture Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                            this.state.toggleTurn();
                            update();
                        } else {
                            logger.log("Illegal Move:" + move.getState() + " " + move.getX1() + " " + move.getY1() + " " + move.getX2() + " " + move.getY2());
                        }
                    } else {
                        logger.log("Piece Selected:" + this.board.getPiece(x, y).getType() + " " + this.board.getPiece(x, y).getColor() + " " + x + " " + y);

                        this.state.fallBack();
                        this.state.setChXYPrev(x, y);
                        this.state.setState(ChessState.SELECTED_STATE);

                        move.setState(ChessState.SELECTED_STATE);
                        move.setSource(this.state.getChXPrev(), this.state.getChYPrev());
                        move.setDestination(-1, -1);
                        //move.setMap(board.getPiece(x, y).getMoveMap());
                    }
                }
                break;
            default:
                break;
        }
        this.board.printBord();
        return move;
    }

    public void update() {
        if (state.getTurn()) {
            logger.log("Updating Black's aattackMap...");
            playerBlack.updateAttackMap();
            logger.log("Updating White's moveMap...");
            playerWhite.updateMoveMap();

            if (playerWhite.isCheckMate()) {
                logger.log("White is CheckMate");
                state.setCheckState(ChessState.WHITE_CHECKMATE);
            } else if (playerWhite.isCheck()) {
                logger.log("White is Check");
                state.setCheckState(ChessState.WHITE_CHECK);
            } else if (playerWhite.isStallMate()) {
                logger.log("White is Stalemate");
                state.setCheckState(ChessState.WHITE_STALEMATE);
            } else {
                state.setCheckState(ChessState.CHECK_NONE);
            }
            logger.log("White's Legal Moves:" + playerWhite.getLegalMoves());

        } else {
            logger.log("Updating White's aattackMap...");
            playerWhite.updateAttackMap();
            logger.log("Updating Black's moveMap...");
            playerBlack.updateMoveMap();
            if (playerBlack.isCheckMate()) {
                logger.log("Black is CheckMate");
                state.setCheckState(ChessState.BLACK_CHECKMATE);
            } else if (playerBlack.isCheck()) {
                logger.log("Black is Check");
                state.setCheckState(ChessState.BLACK_CHECK);
            } else if (playerBlack.isStallMate()) {
                logger.log("Black is Stalemate");
                state.setCheckState(ChessState.BLACK_STALEMATE);
            } else {
                state.setCheckState(ChessState.CHECK_NONE);
            }
            logger.log("Black's Legal Moves:" + playerBlack.getLegalMoves());
        }
    }

    public Piece getPiece(int x, int y) {
        return this.board.getPiece(x, y);
    }

    public void setPromotionCallback(Promotion promotionCallback) {
        this.playerBlack.setPromotionCallback(promotionCallback);
        this.playerWhite.setPromotionCallback(promotionCallback);
    }

    public Player getPlayerBlack() {
        return this.playerBlack;
    }

    public void reset() {

        //TODO
        // Reseting Game After promoting pawn leads Promoted Pawn Instead of Original Pawn

        logger.log("Resetting game");
        this.move.reset();
        this.state.reset();
        this.state.setTurn(true);
        this.board.resetBoard();
        this.playerWhite.reset();
        this.playerBlack.reset();
        this.playerWhite.updateAttackMap();
        this.playerBlack.updateAttackMap();
        this.playerWhite.updateMoveMap();
        this.playerBlack.updateMoveMap();
    }

    public void setLogable(Logable logable) {
        this.logger = logable;
        playerWhite.setLogable(logable);
        playerBlack.setLogable(logable);
    }

    public State getState() {
        return this.state;
    }

    public Move move(Move move) {
        logger.log("move");
        this.move.reset();
        this.move.setSource(move.getX1(), move.getY1());
        this.move.setDestination(move.getX2(), move.getY2());
        switch (move.getState()) {
            case NORMAL_MOVE:
                logger.log("move|NORMAL_MOVE");
                if (this.state.getTurn() == move.getTurn()) {
                    this.move = this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
                    if (this.move.getState() != ChessState.ILLEGAL_MOVE) {
                        logger.log("Illegal move");
                        this.state.toggleTurn();
                    }
                    this.update();
                } else {
                    this.move.setState(ChessState.ILLEGAL_MOVE);
                    logger.log("Illegal move");
                }
                break;
            default:
                break;
        }
        return this.move;
    }

    @Deprecated
    public void playBlack(Move move) {
        Move tmp;
        if (!state.getTurn()) {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Playing Black's move");
            tmp = this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
            this.state.toggleTurn();
            update();
        } else {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Black's move is not applicable");
        }
    }

    @Deprecated
    public void playWhite(Move move) {
        if (state.getTurn()) {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::Playing White's move");
            this.board.moveTo(move.getX1(), move.getY1(), move.getX2(), move.getY2());
            this.state.toggleTurn();
            update();
        } else {
            logger.log(Thread.currentThread().getStackTrace()[1] + "::White's move is not applicable");
        }
    }

    public Logable getLogger() {
        return this.logger;
    }
}

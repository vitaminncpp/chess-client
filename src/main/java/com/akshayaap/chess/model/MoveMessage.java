package com.akshayaap.chess.model;

import com.akshayaap.chess.game.Move;
import com.akshayaap.chess.game.util.ChessState;

public class MoveMessage {
    String sender = null;
    String receiver = null;
    private int x1, y1;
    private int x2, y2;
    private boolean turn;
    private ChessState state;
    private ChessState checkState;

    public MoveMessage() {
        reset();
    }

    public MoveMessage(Move move) {
        this.x1 = move.getX1();
        this.y1 = move.getY1();
        this.x2 = move.getX2();
        this.y2 = move.getY2();

        this.turn = move.getTurn();
        this.state = move.getState();
        this.checkState = move.getCheckState();
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public void reset() {
        this.x1 = -1;
        this.x2 = -1;
        this.y1 = -1;
        this.y2 = -1;

        this.state = ChessState.INVALID_STATE;
        this.checkState = ChessState.CHECK_NONE;

        this.turn = true;
    }

    public Move getMove() {
        Move move = new Move();
        move.setSource(this.x1, this.y1);
        move.setDestination(this.x2, this.y2);
        move.setState(this.state);
        move.setCheckState(this.checkState);

        return move;
    }


    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public ChessState getState() {
        return state;
    }

    public void setState(ChessState state) {
        this.state = state;
    }

    public ChessState getCheckState() {
        return checkState;
    }

    public void setCheckState(ChessState checkState) {
        this.checkState = checkState;
    }

    @Override
    public String toString() {
        return "{" +
                "\"x1\":\"" + x1 + '\"' +
                ", \"y1\":\"" + y1 + '\"' +
                ", \"x2\":\"" + x2 + '\"' +
                ", \"y2\":\"" + y2 + '\"' +
                ", \"turn\":\"" + turn + '\"' +
                ", \"state\":\"" + state + '\"' +
                ", \"checkState\":\"" + checkState + '\"' +
                ", \"sender\":\"" + sender + '\"' +
                ", \"receiver\":\"" + receiver + '\"' +
                '}';
    }
}

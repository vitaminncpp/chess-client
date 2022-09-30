package com.akshayaap.chess.gui;

import com.akshayaap.chess.game.util.ChessState;

public class State {


    private int chX = -1;
    private int chY = -1;

    private int chXPrev = -1;
    private int chYPrev = -1;

    private int chXNext = -1;
    private int chYNext = -1;

    private boolean turn = true;

    private ChessState state = ChessState.NORMAL_STATE;
    private ChessState checkState = ChessState.CHECK_NONE;

    public State() {

    }

    public void reset() {
        this.state = ChessState.NORMAL_STATE;
        this.checkState = ChessState.CHECK_NONE;

        this.chX = -1;
        this.chY = -1;

        this.chXPrev = -1;
        this.chYPrev = -1;

        this.chXNext = -1;
        this.chYNext = -1;
    }

    public ChessState getState() {
        return this.state;
    }

    public void setState(ChessState state) {
        this.state = state;
    }

    public int getChX() {
        return this.chX;
    }

    public void setChX(int chX) {
        this.chX = chX;
    }

    public int getChY() {
        return this.chY;
    }

    public void setChY(int chY) {
        this.chY = chY;
    }

    public int getChXPrev() {
        return this.chXPrev;
    }

    public void setChXPrev(int chXPrev) {
        this.chXPrev = chXPrev;
    }

    public int getChYNext() {
        return chYNext;
    }

    public void setChYNext(int chYNext) {
        this.chYNext = chYNext;
    }

    public boolean getTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public void toggleTurn() {
        this.turn = !this.turn;
    }

    public int getChYPrev() {
        return this.chYPrev;
    }

    public void setChYPrev(int chYPrev) {
        this.chYPrev = chYPrev;
    }

    public int getChXNext() {
        return this.chXNext;
    }

    public void setChXNext(int chXNext) {
        this.chXNext = chXNext;
    }

    public void setChXY(int x, int y) {
        this.chX = x;
        this.chY = y;
    }

    public void setChXYPrev(int x, int y) {
        this.chXPrev = x;
        this.chYPrev = y;
    }

    public void setChXYNext(int x, int y) {
        this.chXNext = x;
        this.chYNext = y;
    }

    public ChessState getCheckState() {
        return this.checkState;
    }
}
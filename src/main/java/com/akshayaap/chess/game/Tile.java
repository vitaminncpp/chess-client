package com.akshayaap.chess.game;

public class Tile {
    private final int x;
    private final int y;
    private Piece piece;

    public Tile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Piece getPiece() {
        return this.piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }

}

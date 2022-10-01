package com.akshayaap.chess.game;


import com.akshayaap.chess.game.util.ChessState;

public abstract class Piece {


    public static final int PAWN_VALUE = 1;
    public static final int PAWN_TYPE = 0;

    public static final int KNIGHT_VALUE = 3;
    public static final int KNIGHT_TYPE = 1;

    public static final int BISHOP_VALUE = 3;
    public static final int BISHOP_TYPE = 2;

    public static final int ROOK_VALUE = 5;
    public static final int ROOK_TYPE = 3;

    public static final int QUEEN_VALUE = 9;
    public static final int QUEEN_TYPE = 4;

    public static final int KING_VALUE = 10000;
    public static final int KING_TYPE = 5;
    protected final int index;
    protected int x;
    protected int y;
    protected int value;
    protected int type;
    protected boolean[][] moveMap = new boolean[8][8];
    protected boolean[][] attackMap = new boolean[8][8];
    protected boolean moved = false;
    protected boolean alive = true;
    protected int legalMoves = 0;
    protected boolean color;
    protected Player player;
    protected Tile[][] board;

    public Piece(int x, int y, Player player, int index) {
        this.index = index;
        this.x = x;
        this.y = y;
        this.player = player;
        this.color = player.getColor();
        this.board = player.getBoard();
        this.resetAttackMap();
        this.resetMoveMap();
        this.board[x][y].setPiece(this);
    }


    public abstract boolean[][] updateMoveMap();

    public abstract boolean[][] updateAttackMap();

    public void resetMoveMap() {
        this.legalMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                moveMap[i][j] = false;
            }
        }
    }

    public void resetAttackMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                attackMap[i][j] = false;
            }
        }
    }

    public boolean getColor() {
        return this.color;
    }

    public int getType() {
        return this.type;
    }


    public void capture() {
        this.alive = false;
        this.x = -1;
        this.y = -1;
    }

    public void giveLife(int x, int y) {
        this.x = x;
        this.y = y;
        alive = true;
        this.board[x][y].setPiece(this);
    }

    public boolean[][] getMoveMap() {
        return moveMap;
    }

    public int getLegalMoves() {
        return legalMoves;
    }

    public boolean isAlive() {
        return alive;
    }


    public Move moveTo(int x, int y) {
        Move move = new Move();
        move.setSource(this.x, this.y);
        move.setDestination(x, y);
        move.setTurn(player.getColor());
        if (this.moveMap[x][y]) {
            if (this.board[x][y].getPiece() != null) {
                move.setState(ChessState.CAPTURE_MOVE);
                this.player.capture(this.board[x][y].getPiece());
                this.board[x][y].getPiece().capture();
            } else {
                move.setState(ChessState.NORMAL_MOVE);
            }

            this.board[x][y].setPiece(this);
            this.board[this.x][this.y].setPiece(null);
            this.x = x;
            this.y = y;

        } else {
            move.setState(ChessState.ILLEGAL_MOVE);
        }
        return move;
    }

    public boolean testForMoveMap(final int x, final int y) {

        final int pX = this.x;
        final int pY = this.y;

        Piece temp = board[x][y].getPiece();
        board[x][y].setPiece(this);
        board[this.x][this.y].setPiece(null);
        this.x = x;
        this.y = y;
        if (temp != null) {
            temp.capture();
        }

        this.player.getOpponent().updateAttackMap();
        boolean legal = !this.player.updateCheck();
        if (legal) {
            moveMap[x][y] = true;
            this.legalMoves++;
        }
        if (temp != null) {
            temp.giveLife(x, y);
        }
        board[x][y].setPiece(temp);
        board[pX][pY].setPiece(this);
        this.x = pX;
        this.y = pY;
        player.getOpponent().updateAttackMap();
        return legal;
    }

    public void reset() {
        this.resetMoveMap();
        this.resetAttackMap();
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }
}

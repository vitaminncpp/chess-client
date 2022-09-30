package com.akshayaap.chess.game;

public class Knight extends Piece {

    public Knight(int x, int y, Player player, int index) {
        super(x, y, player, index);
        this.type = Piece.KNIGHT_TYPE;
        this.value = Piece.KNIGHT_VALUE;
    }

    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();
        if (x + 2 < 8 && y + 1 < 8) {
            if (board[x + 2][y + 1].getPiece() == null || board[x + 2][y + 1].getPiece().getColor() != this.color) {
                testForMoveMap(x + 2, y + 1);
            }
        }
        if (x + 1 < 8 && y + 2 < 8) {
            if (board[x + 1][y + 2].getPiece() == null || board[x + 1][y + 2].getPiece().getColor() != this.color) {
                testForMoveMap(x + 1, y + 2);
            }
        }
        if (x + 2 < 8 && y - 1 >= 0) {
            if (board[x + 2][y - 1].getPiece() == null || board[x + 2][y - 1].getPiece().getColor() != this.color) {
                testForMoveMap(x + 2, y - 1);
            }
        }
        if (x + 1 < 8 && y - 2 >= 0) {
            if (board[x + 1][y - 2].getPiece() == null || board[x + 1][y - 2].getPiece().getColor() != this.color) {
                testForMoveMap(x + 1, y - 2);
            }
        }

        if (x - 2 >= 0 && y + 1 < 8) {
            if (board[x - 2][y + 1].getPiece() == null || board[x - 2][y + 1].getPiece().getColor() != this.color) {
                testForMoveMap(x - 2, y + 1);
            }
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            if (board[x - 1][y - 2].getPiece() == null || board[x - 1][y - 2].getPiece().getColor() != this.color) {
                testForMoveMap(x - 1, y - 2);
            }
        }
        if (x - 2 >= 0 && y - 1 >= 0) {
            if (board[x - 2][y - 1].getPiece() == null || board[x - 2][y - 1].getPiece().getColor() != this.color) {
                testForMoveMap(x - 2, y - 1);
            }
        }
        if (x - 1 >= 0 && y + 2 < 8) {
            if (board[x - 1][y + 2].getPiece() == null || board[x - 1][y + 2].getPiece().getColor() != this.color) {
                testForMoveMap(x - 1, y + 2);
            }
        }
        return this.attackMap;
    }

    @Override
    public boolean[][] updateAttackMap() {
        this.resetAttackMap();
        if (x + 2 < 8 && y + 1 < 8) {
            if (board[x + 2][y + 1].getPiece() == null || board[x + 2][y + 1].getPiece().getColor() != this.color) {
                attackMap[x + 2][y + 1] = true;
            }
        }
        if (x + 1 < 8 && y + 2 < 8) {
            if (board[x + 1][y + 2].getPiece() == null || board[x + 1][y + 2].getPiece().getColor() != this.color) {
                attackMap[x + 1][y + 2] = true;
            }
        }
        if (x + 2 < 8 && y - 1 >= 0) {
            if (board[x + 2][y - 1].getPiece() == null || board[x + 2][y - 1].getPiece().getColor() != this.color) {
                attackMap[x + 2][y - 1] = true;
            }
        }
        if (x + 1 < 8 && y - 2 >= 0) {
            if (board[x + 1][y - 2].getPiece() == null || board[x + 1][y - 2].getPiece().getColor() != this.color) {
                attackMap[x + 1][y - 2] = true;
            }
        }

        if (x - 2 >= 0 && y + 1 < 8) {
            if (board[x - 2][y + 1].getPiece() == null || board[x - 2][y + 1].getPiece().getColor() != this.color) {
                attackMap[x - 2][y + 1] = true;
            }
        }
        if (x - 1 >= 0 && y - 2 >= 0) {
            if (board[x - 1][y - 2].getPiece() == null || board[x - 1][y - 2].getPiece().getColor() != this.color) {
                attackMap[x - 1][y - 2] = true;
            }
        }
        if (x - 2 >= 0 && y - 1 >= 0) {
            if (board[x - 2][y - 1].getPiece() == null || board[x - 2][y - 1].getPiece().getColor() != this.color) {
                attackMap[x - 2][y - 1] = true;
            }
        }
        if (x - 1 >= 0 && y + 2 < 8) {
            if (board[x - 1][y + 2].getPiece() == null || board[x - 1][y + 2].getPiece().getColor() != this.color) {
                attackMap[x - 1][y + 2] = true;
            }
        }
        return this.attackMap;
    }
}

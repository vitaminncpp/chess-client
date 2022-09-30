package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Rook extends Piece {

    public Rook(int x, int y, Player player, int index) {
        super(x, y, player, index);
        this.type = Piece.ROOK_TYPE;
        this.value = Piece.ROOK_VALUE;
    }

    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        int i = x + 1;
        int j = y;
        while (i < 8) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i++;
        }

        i = x - 1;
        j = y;
        while (i >= 0) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i--;
        }

        i = x;
        j = y + 1;
        while (j < 8) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            j++;
        }

        i = x;
        j = y - 1;
        while (j >= 0) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            j--;
        }
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {

        this.resetAttackMap();

        int i = x + 1;
        int j = y;
        while (i < 8) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i++;
        }

        i = x - 1;
        j = y;
        while (i >= 0) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i--;
        }

        i = x;
        j = y + 1;
        while (j < 8) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            j++;
        }

        i = x;
        j = y - 1;
        while (j >= 0) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            j--;
        }
        return this.attackMap;
    }
}

package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Bishop extends Piece {

    public Bishop(int x, int y, Player player, int index) {
        super(x, y, player, index);
        this.type = Piece.BISHOP_TYPE;
        this.value = Piece.BISHOP_VALUE;
    }

    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i++;
            j++;
        }

        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i++;
            j--;
        }

        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i--;
            j++;
        }

        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j].getPiece() == null) {
                testForMoveMap(i, j);
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    testForMoveMap(i, j);
                }
                break;
            }
            i--;
            j--;
        }
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {

        this.resetAttackMap();
        int i = x + 1;
        int j = y + 1;
        while (i < 8 && j < 8) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i++;
            j++;
        }

        i = x + 1;
        j = y - 1;
        while (i < 8 && j >= 0) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i++;
            j--;
        }

        i = x - 1;
        j = y + 1;
        while (i >= 0 && j < 8) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i--;
            j++;
        }

        i = x - 1;
        j = y - 1;
        while (i >= 0 && j >= 0) {
            if (board[i][j].getPiece() == null) {
                attackMap[i][j] = true;
            } else {
                if (board[i][j].getPiece().getColor() != this.color) {
                    attackMap[i][j] = true;
                }
                break;
            }
            i--;
            j--;
        }
        return this.attackMap;
    }
}

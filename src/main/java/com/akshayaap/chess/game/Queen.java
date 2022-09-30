package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class Queen extends Piece {

    /**
     * @param x
     * @param y
     */
    public Queen(int x, int y, Player player) {
        super(x, y, player, 0);
        this.type = Piece.QUEEN_TYPE;
        this.value = Piece.QUEEN_VALUE;
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

        i = x + 1;
        j = y;
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

        i = x + 1;
        j = y;
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

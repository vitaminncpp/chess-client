package com.akshayaap.chess.game;

/**
 * @Author Akshay
 */
public class King extends Piece {

    private boolean check = false;

    public King(int x, int y, Player player) {
        super(x, y, player, 0);
        this.type = Piece.KING_TYPE;
        this.value = Piece.KING_VALUE;
    }


    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        if (x + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y] && (board[x + 1][y].getPiece() == null || (board[x + 1][y].getPiece() != null && board[x + 1][y].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y);
            }
        }
        if (x - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y] && (board[x - 1][y].getPiece() == null || (board[x - 1][y].getPiece() != null && board[x - 1][y].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y);
            }
        }
        if (y + 1 < 8) {
            if (!player.getThreatMap()[x][y + 1] && (board[x][y + 1].getPiece() == null || (board[x][y + 1].getPiece() != null && board[x][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x, y + 1);
            }
        }
        if (y - 1 >= 0) {
            if (!player.getThreatMap()[x][y - 1] && (board[x][y - 1].getPiece() == null || (board[x][y - 1].getPiece() != null && board[x][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x, y - 1);
            }
        }

        if (x + 1 < 8 && y + 1 < 8) {
            if (!player.getThreatMap()[x + 1][y + 1] && (board[x + 1][y + 1].getPiece() == null || (board[x + 1][y + 1].getPiece() != null && board[x + 1][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y + 1);
            }
        }
        if (x + 1 < 8 && y - 1 >= 0) {
            if (!player.getThreatMap()[x + 1][y - 1] && (board[x + 1][y - 1].getPiece() == null || (board[x + 1][y - 1].getPiece() != null && board[x + 1][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x + 1, y - 1);
            }
        }
        if (x - 1 >= 0 && y + 1 < 8) {
            if (!player.getThreatMap()[x - 1][y + 1] && (board[x - 1][y + 1].getPiece() == null || (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y + 1);
            }
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            if (!player.getThreatMap()[x - 1][y - 1] && (board[x - 1][y - 1].getPiece() == null || (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor() != this.color))) {
                testForMoveMap(x - 1, y - 1);
            }
        }
        updateCheck();
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {
        this.resetAttackMap();
        if (x + 1 < 8) {
            attackMap[x + 1][y] = true;
        }
        if (x - 1 >= 0) {
            attackMap[x - 1][y] = true;
        }
        if (y + 1 < 8) {
            attackMap[x][y + 1] = true;
        }
        if (y - 1 >= 0) {
            attackMap[x][y - 1] = true;
        }

        if (x + 1 < 8 && y + 1 < 8) {
            attackMap[x + 1][y + 1] = true;
        }

        if (x + 1 < 8 && y - 1 >= 0) {
            attackMap[x + 1][y - 1] = true;
        }
        if (x - 1 >= 0 && y + 1 < 8) {
            attackMap[x - 1][y + 1] = true;
        }
        if (x - 1 >= 0 && y - 1 >= 0) {
            attackMap[x - 1][y - 1] = true;
        }
        return this.attackMap;
    }

    public boolean isCheck() {
        return this.check;
    }

    public boolean updateCheck() {
        this.check = this.player.getThreatMap()[x][y];
        return this.check;
    }

}

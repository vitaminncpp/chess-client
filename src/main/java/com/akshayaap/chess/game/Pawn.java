package com.akshayaap.chess.game;

import com.akshayaap.chess.game.util.ChessState;

import java.io.IOException;

public class Pawn extends Piece {

    private boolean promoted = false;

    public Pawn(int x, int y, Player player, int index) {
        super(x, y, player, index);
        this.value = Piece.PAWN_VALUE;
        this.type = Piece.PAWN_TYPE;
    }


    @Override
    public boolean[][] updateMoveMap() {
        this.resetMoveMap();

        if (this.color) {
            if (x + 1 < 8) {
                if (board[x + 1][y].getPiece() == null) {
                    testForMoveMap(x + 1, y);
                    if (x == 1 && board[x + 2][y].getPiece() == null) {
                        testForMoveMap(x + 2, y);
                    }
                }
                if (y + 1 < 8) {
                    if (board[x + 1][y + 1].getPiece() != null && !board[x + 1][y + 1].getPiece().getColor()) {
                        testForMoveMap(x + 1, y + 1);
                    }
                }
                if (y - 1 >= 0) {
                    if (board[x + 1][y - 1].getPiece() != null && !board[x + 1][y - 1].getPiece().getColor()) {
                        testForMoveMap(x + 1, y - 1);
                    }
                }
            }
        } else {
            if (x - 1 >= 0) {
                if (board[x - 1][y].getPiece() == null) {
                    testForMoveMap(x - 1, y);
                    if (x == 6 && board[x - 2][y].getPiece() == null) {
                        testForMoveMap(x - 2, y);
                    }
                }
                if (y + 1 < 8) {
                    if (board[x - 1][y + 1].getPiece() != null && board[x - 1][y + 1].getPiece().getColor()) {
                        testForMoveMap(x - 1, y + 1);
                    }
                }
                if (y - 1 >= 0) {
                    if (board[x - 1][y - 1].getPiece() != null && board[x - 1][y - 1].getPiece().getColor()) {
                        testForMoveMap(x - 1, y - 1);
                    }
                }
            }
        }
        return this.moveMap;
    }

    @Override
    public boolean[][] updateAttackMap() {
        this.resetAttackMap();
        if (this.color) {
            if (x + 1 < 8) {
                if (y + 1 < 8) {
                    attackMap[x + 1][y + 1] = true;
                }
                if (y - 1 >= 0) {
                    attackMap[x + 1][y - 1] = true;
                }
            }
        } else {
            if (x - 1 >= 0) {
                if (y + 1 < 8) {
                    attackMap[x - 1][y + 1] = true;
                }
                if (y - 1 >= 0) {
                    attackMap[x - 1][y - 1] = true;
                }
            }
        }
        return this.attackMap;
    }

    @Override
    public Move moveTo(int x, int y) {
        Move move = super.moveTo(x, y);
        if (!promoted) {
            if (this.color) {
                if (this.x == 7) {
                    player.setPromotionPawn(this);
                    try {
                        player.getPromotionCallback().prompt(this.player);
                        move.setState(ChessState.PROMOTION_MOVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                if (this.x == 0) {
                    player.setPromotionPawn(this);
                    try {
                        player.getPromotionCallback().prompt(this.player);
                        move.setState(ChessState.PROMOTION_MOVE);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return move;
    }

    public void promot() {
        this.promoted = true;
        this.alive = false;
        this.x = -1;
        this.y = -1;
        resetAttackMap();
        resetMoveMap();
    }

}

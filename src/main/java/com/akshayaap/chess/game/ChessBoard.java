package com.akshayaap.chess.game;

import com.akshayaap.chess.game.util.ChessState;

public class ChessBoard {

    public final Tile[][] board;

    public ChessBoard() {
        this.board = new Tile[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j] = new Tile(i, j);
            }
        }
    }

    public Piece getPiece(int x, int y) {
        return this.board[x][y].getPiece();
    }

    public void resetBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.board[i][j].setPiece(null);
            }
        }
    }


    public void printBord() {
        System.out.println("################BEGIN BORAD################");
        for (Tile[] i : this.board) {
            System.out.println("-----------------------------------------");
            System.out.print("|");
            for (Tile j : i) {
                if (j.getPiece() == null) {
                    System.out.print("    |");
                } else {
                    if (j.getPiece().getColor()) {
                        switch (j.getPiece().getType()) {
                            case Piece.PAWN_TYPE -> System.out.print(" PW |");
                            case Piece.KNIGHT_TYPE -> System.out.print(" NW |");
                            case Piece.BISHOP_TYPE -> System.out.print(" BW |");
                            case Piece.ROOK_TYPE -> System.out.print(" RW |");
                            case Piece.QUEEN_TYPE -> System.out.print(" QW |");
                            case Piece.KING_TYPE -> System.out.print(" KW |");
                        }
                    } else {
                        switch (j.getPiece().getType()) {
                            case Piece.PAWN_TYPE -> System.out.print(" PB |");
                            case Piece.KNIGHT_TYPE -> System.out.print(" NB |");
                            case Piece.BISHOP_TYPE -> System.out.print(" BB |");
                            case Piece.ROOK_TYPE -> System.out.print(" RB |");
                            case Piece.QUEEN_TYPE -> System.out.print(" QB |");
                            case Piece.KING_TYPE -> System.out.print(" KB |");
                        }
                    }
                }
            }
            System.out.println();
        }
        System.out.println("-------------------------");
        System.out.println("#################END BORAD#################");
//        System.out.println("##################OBJECTS#################");
//        for(Tile[] i:this.board){
//            for(Tile j:i){
//                System.out.print(j.getPiece()+"\t\t");
//            }
//            System.out.println();
//        }
//        System.out.println("##################OBJECTS#################");
    }

    public Tile[][] getBoard() {
        return this.board;
    }


    public Move moveTo(int chXPrev, int chYPrev, int x, int y) {
        Move move = new Move();
        if (this.board[chXPrev][chYPrev].getPiece() != null) {
            move = this.board[chXPrev][chYPrev].getPiece().moveTo(x, y);
            return move;
        }
        move.reset();
        move.setState(ChessState.ILLEGAL_MOVE);
        move.setSource(chXPrev, chYPrev);
        move.setDestination(x, y);
        return move;
    }
}


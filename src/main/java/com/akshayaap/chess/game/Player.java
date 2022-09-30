package com.akshayaap.chess.game;


import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.game.util.PromotionCallback;

public class Player {

    private final Piece[][] pieces;

    private final boolean color;
    private final boolean[][] attackMap = new boolean[8][8];
    private final Tile[][] board;
    private int legalMoves;
    private boolean[][] threatMap;
    private boolean check;
    private boolean checkMate;
    private boolean stallMate;
    private Player opponent;
    private boolean[][] moveMap = new boolean[8][8];

    private PromotionCallback promotionCallback;
    private CaptureCallBack captureCallBack;
    private Pawn promotionPawn = null;
    private Logable logable;

    public Player(boolean color, Tile[][] board) {
        this.color = color;
        this.board = board;
        this.pieces = new Piece[6][];

        pieces[0] = new Piece[8];
        for (int j = 0; j < 8; j++) {
            pieces[0][j] = new Pawn(this.color ? 1 : 6, j, this, j);
        }

        pieces[1] = new Piece[2];
        pieces[1][0] = new Knight(this.color ? 0 : 7, 1, this, 0);
        pieces[1][1] = new Knight(this.color ? 0 : 7, 6, this, 1);

        pieces[2] = new Piece[2];
        pieces[2][0] = new Bishop(this.color ? 0 : 7, 2, this, 0);
        pieces[2][1] = new Bishop(this.color ? 0 : 7, 5, this, 1);

        pieces[3] = new Piece[2];
        pieces[3][0] = new Rook(this.color ? 0 : 7, 0, this, 0);
        pieces[3][1] = new Rook(this.color ? 0 : 7, 7, this, 1);

        pieces[4] = new Piece[1];
        pieces[4][0] = new Queen(this.color ? 0 : 7, 4, this);

        pieces[5] = new Piece[1];
        pieces[5][0] = new King(this.color ? 0 : 7, 3, this);

        resetMoveMap();
        resetAttackMap();
    }

    private static boolean[][] arrayOR_ACC(boolean[][] dest, boolean[][] src) {
        if (dest.length != src.length || dest[0].length != src[0].length) {
            System.out.println("Can't Perform OR and Accumulation Operation Due to different Dimensions");
        }
        for (int i = 0; i < dest.length; i++) {
            for (int j = 0; j < dest[i].length; j++) {
                dest[i][j] = dest[i][j] || src[i][j];
            }
        }
        return dest;
    }

    public void promot(int type) {
        Piece piece = null;
        switch (type) {
            case -1:
                System.out.println("Aah Snap !");
            case 0:
                System.out.println("WTF !!!");
                return;
            case 1:
                piece = new Knight(promotionPawn.x, promotionPawn.y, this, promotionPawn.index);
                break;
            case 2:
                piece = new Bishop(promotionPawn.x, promotionPawn.y, this, promotionPawn.index);
                break;
            case 3:
                piece = new Rook(promotionPawn.x, promotionPawn.y, this, promotionPawn.index);
                break;
            case 4:
                piece = new Queen(promotionPawn.x, promotionPawn.y, this);
                break;
            case 5:
                System.out.println("WTF are you serious ?!?");
                piece = new King(promotionPawn.x, promotionPawn.y, this);
                break;
            default:
                System.out.println("Solar radiation detected !!");
                break;
        }
        this.pieces[0][promotionPawn.index] = piece;
        promotionPawn.promot();
    }

    public Piece[][] getPieces() {
        return pieces;
    }

    public int getLegalMoves() {
        return legalMoves;
    }

    public boolean[][] getMoveMap() {
        return moveMap;
    }

    public boolean isColor() {
        return color;
    }

    public void reset() {
        for (int j = 0; j < 8; j++) {
            pieces[0][j].giveLife(this.color ? 1 : 6, j);
            pieces[0][j].reset();
        }

        pieces[1][0].giveLife(this.color ? 0 : 7, 1);
        pieces[1][0].reset();
        pieces[1][1].giveLife(this.color ? 0 : 7, 6);
        pieces[1][1].reset();

        pieces[2][0].giveLife(this.color ? 0 : 7, 2);
        pieces[2][0].reset();
        pieces[2][1].giveLife(this.color ? 0 : 7, 5);
        pieces[2][1].reset();

        pieces[3][0].giveLife(this.color ? 0 : 7, 0);
        pieces[3][0].reset();
        pieces[3][1].giveLife(this.color ? 0 : 7, 7);
        pieces[3][1].reset();

        pieces[4][0].giveLife(this.color ? 0 : 7, 4);
        pieces[4][0].reset();

        pieces[5][0].giveLife(this.color ? 0 : 7, 3);
        pieces[5][0].reset();
        resetMoveMap();
        resetAttackMap();
    }

    public void resetAttackMap() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.attackMap[i][j] = false;
            }
        }
    }

    public void resetMoveMap() {
        this.legalMoves = 0;
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                this.moveMap[i][j] = false;
            }
        }
    }

    public boolean isCheck() {
        return check;
    }


    public boolean isCheckMate() {
        return this.checkMate;
    }


    public boolean isStallMate() {
        return this.stallMate;
    }


    public boolean[][] getThreatMap() {
        return threatMap;
    }

    public boolean[][] getAttackMap() {
        return attackMap;
    }

    public Tile[][] getBoard() {
        return board;
    }

    public Player getOpponent() {
        return opponent;
    }

    public void setOpponent(Player player) {
        this.opponent = player;
        this.threatMap = opponent.getAttackMap();
    }

    public boolean getColor() {
        return this.color;
    }

    public void updateAttackMap() {
        this.resetAttackMap();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isAlive()) {
                    arrayOR_ACC(this.attackMap, pieces[i][j].updateAttackMap());
                }
            }
        }
    }

    public void updateMoveMap() {
        this.resetMoveMap();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < pieces[i].length; j++) {
                if (pieces[i][j].isAlive()) {
                    arrayOR_ACC(this.moveMap, pieces[i][j].updateMoveMap());
                    this.legalMoves += pieces[i][j].getLegalMoves();
                }
            }
        }
        updateCheck();
        updateCheckMate();
        updateStallMate();
    }

    public boolean updateCheck() {
        this.check = ((King) pieces[5][0]).updateCheck();
        return check;
    }

    public boolean updateCheckMate() {
        this.checkMate = legalMoves == 0 && this.check;
        return this.checkMate;
    }

    public boolean updateStallMate() {
        this.stallMate = legalMoves == 0 && !this.check;
        return this.stallMate;
    }

    public PromotionCallback getPromotionCallback() {
        return this.promotionCallback;
    }

    public void setPromotionCallback(PromotionCallback callback) {
        this.promotionCallback = callback;
    }

    public void setPromotionPawn(Pawn pawn) {
        this.promotionPawn = pawn;
    }

    public void capture(Piece piece) {
        captureCallBack.capture(piece);
    }

    public void setCaptrueCallback(CaptureCallBack captureCallBack) {
        this.captureCallBack = captureCallBack;
    }

    public void setLogable(Logable logable) {
        this.logable = logable;
    }
}


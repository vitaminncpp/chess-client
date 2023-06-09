package com.akshayaap.chess.game.util;

public enum ChessState {
    NOT_APPLICABLE,
    INVALID_STATE,
    NORMAL_STATE,
    SELECTED_STATE,
    EMPTY_SELECTION,
    INVALID_SELECTION,

    NORMAL_MOVE,
    ILLEGAL_MOVE,
    CAPTURE_MOVE,
    CHECK_MOVE,
    PROMOTION_MOVE,

    CHECK_NONE,
    WHITE_CHECK,
    BLACK_CHECK,
    WHITE_CHECKMATE,
    BLACK_CHECKMATE,
    WHITE_STALEMATE,
    BLACK_STALEMATE,

    OFFLINE,
    ONLINE;
}

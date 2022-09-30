package com.akshayaap.chess.util;

import com.akshayaap.chess.model.ChatMessage;
import com.akshayaap.chess.model.MoveMessage;

public interface ChessActionListener {
    public void onMoveReceived(MoveMessage move);

    public void onChatMessageReceived(ChatMessage message);
}

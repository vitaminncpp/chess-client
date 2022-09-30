package com.akshayaap.chess.game.util;

import com.akshayaap.chess.game.Player;

import java.io.IOException;

public interface PromotionCallback {
    public void prompt(Player player) throws IOException;
}

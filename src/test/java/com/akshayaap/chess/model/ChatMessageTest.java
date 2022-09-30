package com.akshayaap.chess.model;

import org.junit.jupiter.api.Test;

import java.util.Date;

public class ChatMessageTest {
    @Test
    public void TimeStampTest() {
        System.out.println(new Date());
    }

    @Test
    public void toStringTest() {
        System.out.println(new ChatMessage());
    }
}

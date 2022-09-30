package com.akshayaap.chess;

import com.akshayaap.chess.game.util.ChessState;
import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.model.ChatMessage;
import com.akshayaap.chess.model.Message;
import com.akshayaap.chess.model.MoveMessage;
import com.akshayaap.chess.network.Client;
import com.akshayaap.chess.util.ChessActionListener;
import org.junit.jupiter.api.Test;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class ClientTest {


    @Test
    public void stompClientTest() throws ExecutionException, InterruptedException {
        WebSocketClient client = new StandardWebSocketClient();
        WebSocketStompClient stompClient = new WebSocketStompClient(client);
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
        scheduler.afterPropertiesSet();
        stompClient.setTaskScheduler(scheduler);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());

        StompSession session = stompClient.connectAsync("ws://localhost:8080/chess-server", new StompSessionHandler() {

            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("Message Received From Stomp Client:" + payload);
            }

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                System.out.println("Connected to Stomp Client");
            }

            @Override
            public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
                exception.printStackTrace();
            }

            @Override
            public void handleTransportError(StompSession session, Throwable exception) {
                exception.printStackTrace();
            }
        }).get();
        session.subscribe("/game/send", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return Message.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                System.out.println("Message received From Stomp session: " + payload);
            }
        });
        System.out.println("Sending Data to Server...");
        session.send("/chess/play", new Message("Akshay", "Parmar"));
        System.out.println("Data Sent to Sever.. waiting for response...");
        Thread.sleep(25000);
    }


    @Test
    public void ultimatTest() throws ExecutionException, InterruptedException, URISyntaxException {
        Client client = new Client(new Logable() {
            @Override
            public void log(String message) {
                System.out.println(message);
            }
        });
        client.addActionListener(new ChessActionListener() {
            @Override
            public void onMoveReceived(MoveMessage move) {
                System.out.println(move);
            }

            @Override
            public void onChatMessageReceived(ChatMessage message) {
                System.out.println(message);
            }
        });
        client.connectToServer("ws://localhost:8080/chess-server");
        client.subscribe("aa");
        MoveMessage msg = new MoveMessage();
        int i = 1;
        msg.setY1(1);
        msg.setY2(1);
        msg.setTurn(true);
        msg.setSender("aa");
        msg.setReceiver("bb");
        msg.setState(ChessState.NORMAL_MOVE);
        msg.setCheckState(ChessState.CHECK_NONE);
        while (true) {
            Thread.sleep(5000);
            msg.setX1(i);
            msg.setX2(i + 1);
            client.sendMoveMessage(msg);
            i++;
        }
    }


}

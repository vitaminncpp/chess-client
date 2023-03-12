package com.akshayaap.chess.network;

import com.akshayaap.chess.game.util.Logable;
import com.akshayaap.chess.model.ChatMessage;
import com.akshayaap.chess.model.MoveMessage;
import com.akshayaap.chess.util.ChessActionListener;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.simp.stomp.*;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;

public class Client {
    private WebSocketClient client = null;
    private WebSocketStompClient stompClient = null;
    private ThreadPoolTaskScheduler scheduler = null;
    private StompSession session = null;
    private ChessActionListener listener = null;
    private Logable logger = null;

    public Client(Logable logger) throws ExecutionException, InterruptedException {
        this.logger = logger;
        client = new StandardWebSocketClient();
        stompClient = new WebSocketStompClient(client);
        scheduler = new ThreadPoolTaskScheduler();

        scheduler.afterPropertiesSet();
        stompClient.setTaskScheduler(scheduler);
        stompClient.setMessageConverter(new MappingJackson2MessageConverter());
    }

    public void addActionListener(ChessActionListener listener) {
        this.listener = listener;
    }

    public void sendMoveMessage(MoveMessage move) {
        session.send("/chess/play", move);
    }

    public void sendChatMessage(ChatMessage message) {
        session.send("/chess/chat", message);
    }


    public void connectToServer(String uri) throws ExecutionException, InterruptedException, URISyntaxException {
        session = stompClient.connectAsync(uri, new StompSessionHandler() {

            private StompHeaders headers;

            @Override
            public Type getPayloadType(StompHeaders headers) {
                this.headers = headers;
                return MoveMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {

            }

            @Override
            public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
                logger.log("Connected to Stomp Client");
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
    }

    /**
     * @param name
     */
    public void subscribe(String name) {
        session.subscribe("/game/" + name + "/online", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return MoveMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                logger.log("Message received From Stomp session: " + payload);
                listener.onMoveReceived((MoveMessage) payload);
            }
        });

        session.subscribe("/game/" + name + "/message", new StompFrameHandler() {
            @Override
            public Type getPayloadType(StompHeaders headers) {
                return ChatMessage.class;
            }

            @Override
            public void handleFrame(StompHeaders headers, Object payload) {
                logger.log("Message received From Stomp session: " + payload);
                listener.onChatMessageReceived((ChatMessage) payload);
            }
        });
    }

    public void setChessActionListener(ChessActionListener listener) {
        this.listener = listener;
    }
}

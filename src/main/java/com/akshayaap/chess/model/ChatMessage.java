package com.akshayaap.chess.model;


import java.util.Date;

public class ChatMessage {
    private String sender;
    private String receiver;
    private String message;
    private Date timestamp;

    public ChatMessage() {
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "{" +
                "\"sender\":\"" + sender + '\"' +
                ", \"receiver\":\"" + receiver + '\"' +
                ", \"message\":\"" + message + '\"' +
                ", \"timestamp\":\"" + timestamp + '\"' +
                '}';
    }
}

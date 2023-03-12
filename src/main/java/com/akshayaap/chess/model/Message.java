package com.akshayaap.chess.model;

public class Message {

    private String message;
    private User sender;
    private String receiver;

    public Message() {
    }

    public Message(String message, User sender, String receiver) {
        this.message = message;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    @Override
    public String toString() {
        return "{" +
                "\"message\":\"" + message +
                ", \"sender\":" + sender.toString() + '\"' +
                ", \"receiver\":\"" + receiver + '\"' +
                '}';
    }
}

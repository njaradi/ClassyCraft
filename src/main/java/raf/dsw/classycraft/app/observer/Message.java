package raf.dsw.classycraft.app.observer;

import java.time.LocalDateTime;

public class Message {
    private final String text;
    private final MessageType type;
    private final LocalDateTime timestamp;

    public Message(String text, MessageType type, LocalDateTime timestamp) {
        this.text = text;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getText() {
        return text;
    }

    public MessageType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return "[" +
                type + ']' +
                "[" + timestamp +
                "] " + text;
    }
}

package raf.dsw.classycraft.app.observer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenerator implements IPublisher {
    List<ISubscriber> subscribers;

    public void generateMessage(MessageType type, String text) {
        notifySubscribers(new Message(text, type, LocalDateTime.now()));
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if (sub == null)
            return;
        if (this.subscribers == null)
            this.subscribers = new ArrayList<>();
        if (this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if (sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notifySubscribers(Object notification) {
        if (notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;
        for (ISubscriber listener : subscribers) {
            listener.update(notification);
        }
    }
}

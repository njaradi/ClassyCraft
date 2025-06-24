package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.Message;

public class ConsoleLogger implements Logger {

    public ConsoleLogger() {
        ApplicationFramework.getMessageGenerator().addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        logMessage((Message) notification);
    }

    @Override
    public void logMessage(Message message) {
        System.out.println(message.toString());
    }
}

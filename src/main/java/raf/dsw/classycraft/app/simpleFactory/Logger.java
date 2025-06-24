package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Message;

public interface Logger extends ISubscriber {
    void logMessage(Message message);
}

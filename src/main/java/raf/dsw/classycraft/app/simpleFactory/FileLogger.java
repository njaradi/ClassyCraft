package raf.dsw.classycraft.app.simpleFactory;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.Message;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;

public class FileLogger implements Logger {
    public FileLogger() {
        ApplicationFramework.getMessageGenerator().addSubscriber(this);
    }

    @Override
    public void update(Object notification) {
        logMessage((Message) notification);
    }

    @Override
    public void logMessage(Message message) {
        URL url = getClass().getResource("/log/log.txt");
        File logFile = null;
        FileWriter fw = null;
        PrintWriter pw = null;
        try {
            if (url == null) {
                System.err.println("\t/!\\\tNa putanji /target/classes se ne nalazi direktorijum log i u njemu fajl log.txt.");
                System.err.println("\t/!\\\tTrebali bi da se naprave kada se build-uje Maven, ali ovaj put vjerovatno nije uspjelo.");
                System.err.println("\t/!\\\tOvaj program ce puci za 3, 2, 1 ...");
            } else {
                logFile = new File(url.getFile());
            }
            assert logFile != null;
            fw = new FileWriter(logFile, true);
            pw = new PrintWriter(fw);
            pw.println(message.toString());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("FileLogger failed: " + e.getMessage());
            }
        }
    }
}

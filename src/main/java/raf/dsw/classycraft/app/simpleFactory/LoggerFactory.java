package raf.dsw.classycraft.app.simpleFactory;

public class LoggerFactory {

    public Logger createLog(String s) {
        if (s.equals("Console")) {
            return new ConsoleLogger();
        }
        return new FileLogger();
    }
}

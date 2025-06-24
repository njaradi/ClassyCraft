package raf.dsw.classycraft.app.core;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.command.CommandManager;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageGenerator;
import raf.dsw.classycraft.app.repository.ClassyRepository;
import raf.dsw.classycraft.app.simpleFactory.Logger;
import raf.dsw.classycraft.app.simpleFactory.LoggerFactory;

@SuppressWarnings("all")
public class ApplicationFramework {


    //buduca polja za model celog projekta

    //classyRepository je kod njega protected
    //a pravimo ga u initialize
    @Getter
    protected ClassyRepository classyRepository;
    @Getter
    private static final MessageGenerator messageGenerator = new MessageGenerator();
    private static final Logger consoleLogger = new LoggerFactory().createLog("Console");
    private static final Logger fileLogger = new LoggerFactory().createLog("File");



    private MainFrame mainFrame;

    //singlton ovo ne diramo
    private static ApplicationFramework instance;
    private ApplicationFramework(){

    }

    public void initialize(ClassyRepository classyRepository){

        this.classyRepository = classyRepository;
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);
    }

    public static ApplicationFramework getInstance() {
        if (instance == null) {
            instance = new ApplicationFramework();
        }
        return instance;
    }

}

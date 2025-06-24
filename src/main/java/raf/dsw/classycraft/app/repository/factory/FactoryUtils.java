package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public class FactoryUtils {
    public static NodeFactory returnNodeFactory(ClassyNode parent) {
        if (parent instanceof ProjectExplorer) {
            return new ProjectExplorerFactory();
        } else if (parent instanceof Project) {
            return new ProjectFactory();
        } else if(parent instanceof Package){
            return new PackageFactory();
        } else {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.INFORMATION,"Work in progress");
            return null;
        }
    }
}

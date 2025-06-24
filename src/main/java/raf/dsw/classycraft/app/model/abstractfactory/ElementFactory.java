package raf.dsw.classycraft.app.model.abstractfactory;

import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class ElementFactory extends AbstractElementFactory{
    @Override
    public InterClass createInterClass(String name, ClassyNode partent) {
        return null;
    }

    @Override
    public Connection createConnection(ClassyNode partent) {
        return null;
    }
}

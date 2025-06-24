package raf.dsw.classycraft.app.model.abstractfactory;

import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public abstract class AbstractElementFactory {
    public abstract InterClass createInterClass(String name, ClassyNode partent);
    public abstract Connection createConnection(ClassyNode partent);
}

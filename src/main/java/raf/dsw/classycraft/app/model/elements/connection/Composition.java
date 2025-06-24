package raf.dsw.classycraft.app.model.elements.connection;

import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Composition extends Connection {
    public Composition() {
    }

    public Composition(ClassyNode parent) {
        super(parent);
    }
    public Composition(Connection connection){super(connection);}
}

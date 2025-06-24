package raf.dsw.classycraft.app.model.elements.connection;

import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Dependency extends Connection {
    public Dependency() {
    }

    public Dependency(ClassyNode parent) {
        super(parent);
    }
    public Dependency(Connection connection){super(connection);}
}

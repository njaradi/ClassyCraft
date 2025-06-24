package raf.dsw.classycraft.app.model.elements.connection;

import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public class Aggregation extends Connection {
    public Aggregation() {
        super();
    }

    public Aggregation(ClassyNode parent) {
        super(parent);
    }
    public Aggregation(Connection connection){super(connection);}
}

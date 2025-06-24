package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;

public abstract class NodeFactory {
    public abstract ClassyNode createNode(ClassyNode parent);
    public String defaultName(ClassyNode parent) {
        return "New";
    }
}

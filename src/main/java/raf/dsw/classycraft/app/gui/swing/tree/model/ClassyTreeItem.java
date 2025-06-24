package raf.dsw.classycraft.app.gui.swing.tree.model;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import javax.swing.tree.DefaultMutableTreeNode;

@Getter
public class ClassyTreeItem extends DefaultMutableTreeNode implements ISubscriber {
    private final ClassyNode classyNode;

    public ClassyTreeItem(ClassyNode classyNode) {
        this.classyNode = classyNode;
    }

    @Override
    public String toString() {
        if (classyNode != null) return classyNode.getName();
        else return "<<lol>>";
    }

    public void setName(String name) {
        this.classyNode.setName(name);
    }

    @Override
    public void update(Object notification) {
        if (children != null && !children.isEmpty()) {
            children.clear();
        }
        if (notification instanceof Diagram) {
            MainFrame.getInstance().getClassyTree().addChild(this);
        }
    }
}

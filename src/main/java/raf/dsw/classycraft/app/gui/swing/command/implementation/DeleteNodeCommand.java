package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class DeleteNodeCommand extends AbstractCommand {

    private ClassyTreeItem parent;
    private ClassyTreeItem child;

    public DeleteNodeCommand(ClassyTreeItem parent, ClassyTreeItem child) {
        this.parent = parent;
        this.child = child;
    }

    @Override
    public void doCommand() {
        if(child == null ||  parent==null) return;
        child.removeFromParent();
        ((ClassyNodeComposite)parent.getClassyNode()).removeChild(child.getClassyNode());
    }

    @Override
    public void undoCommand() {
        if(child == null ||  parent==null) return;
        parent.add(child);
        ((ClassyNodeComposite)parent.getClassyNode()).addChild(child.getClassyNode());
    }
}

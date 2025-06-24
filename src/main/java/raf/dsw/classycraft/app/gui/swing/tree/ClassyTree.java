package raf.dsw.classycraft.app.gui.swing.tree;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

public interface ClassyTree {
    ClassyTreeView generateTree(ProjectExplorer projectExplorer);
    void addChild(ClassyTreeItem item);
    ClassyTreeItem getSelectedNode();
    void removeChild(ClassyTreeItem child);
}

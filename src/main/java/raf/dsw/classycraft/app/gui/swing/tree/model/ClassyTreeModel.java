package raf.dsw.classycraft.app.gui.swing.tree.model;

import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

public class ClassyTreeModel extends DefaultTreeModel {
    public ClassyTreeModel(TreeNode root) {
        super(root);
    }
    @Override
    public ClassyTreeItem getRoot() {
        return (ClassyTreeItem) super.getRoot();
    }
}

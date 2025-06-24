package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.controler.ClassyTreeCellEditor;
import raf.dsw.classycraft.app.gui.swing.tree.controler.ClassyTreeSelectionListener;

import javax.swing.*;
import javax.swing.tree.DefaultTreeModel;

public class ClassyTreeView extends JTree {
    public ClassyTreeView(DefaultTreeModel model) {
        setModel(model);
        ClassyTreeCellRenderer cellRenderer = new ClassyTreeCellRenderer();
        addTreeSelectionListener(new ClassyTreeSelectionListener());
        setCellEditor(new ClassyTreeCellEditor(this,cellRenderer));
        setCellRenderer(cellRenderer);
        setEditable(true);
    }
}

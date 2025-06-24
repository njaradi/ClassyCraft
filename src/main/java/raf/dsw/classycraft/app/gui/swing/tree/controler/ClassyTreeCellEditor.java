package raf.dsw.classycraft.app.gui.swing.tree.controler;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.EventObject;

public class ClassyTreeCellEditor extends DefaultTreeCellEditor implements ActionListener {
    private Object clickedOn = null;

    public ClassyTreeCellEditor(JTree tree, DefaultTreeCellRenderer renderer) {
        super(tree, renderer);
    }

    public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded, boolean leaf, int row) {
        clickedOn = value;
        JTextField edit = new JTextField(value.toString());
        edit.addActionListener(this);
        return edit;
    }

    public boolean isCellEditable(EventObject event) {
        if (event instanceof MouseEvent) {
            return ((MouseEvent) event).getClickCount() == 3;
        }
        return false;
    }

    public void actionPerformed(ActionEvent e) {
        if (!(clickedOn instanceof ClassyTreeItem)) {
            return;
        }
        if(((ClassyTreeItem) clickedOn).getClassyNode() instanceof ProjectExplorer)
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije dozvoljeno preimenovati Project Explorer");
            return;
        }
        ClassyTreeItem clicked = (ClassyTreeItem) clickedOn;
        clicked.setName(e.getActionCommand());
    }
}

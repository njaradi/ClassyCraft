package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class DeleteNodeAction extends AbstractClassyAction {
    public DeleteNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        putValue(SMALL_ICON, loadIcon("/images/icons/delete.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Nije selektovan cvor.");
            return;
        }
        if(selected.getClassyNode() instanceof ProjectExplorer)
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Nije dozvoljeno brisanje Project Explorer-a");
            return;
        }
        MainFrame.getInstance().getClassyTree().removeChild(selected);
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).resetLastSelected();
    }
}

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
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class RenameNodeAction extends AbstractClassyAction {

    public RenameNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F2, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/rename.png"));
        putValue(NAME, "Rename");
        putValue(SHORT_DESCRIPTION, "Rename node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected =  MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null)
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nista nije selektovano");
            return;
        }
        if(selected.getClassyNode() instanceof ProjectExplorer)
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije dozvoljeno preimenovati Project explorer");
            return;
        }
        ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).renameChild(selected);
    }
}

package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class RenameAuthorAction extends AbstractClassyAction {

    public RenameAuthorAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/author.png"));
        putValue(NAME, "Rename Author");
        putValue(SHORT_DESCRIPTION, "Rename author");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected =  MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || !(selected.getClassyNode() instanceof Project))
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije selektovan projekat");
            return;
        }
        ((Project)selected.getClassyNode()).setAutor((JOptionPane.showInputDialog("Novo ime autora:",((Project) selected.getClassyNode()).getAutor())));
    }
}

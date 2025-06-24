package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class NewNodeAction extends AbstractClassyAction {
    public NewNodeAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/new.png"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New Node");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Nije selektovan nijedan cvor.");
            return;
        }
        if ((selected.getClassyNode() instanceof Diagram)) {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Selektovan je dijagram.");
            return;
        }
        if ((selected.getClassyNode() instanceof InterClass)) {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Selektovan je element dijagrama.");
            return;
        }
        MainFrame.getInstance().getClassyTree().addChild(selected);
    }
}

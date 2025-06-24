package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class DeleteElementAction extends AbstractClassyAction {
    private DeleteElementAction deleteElementAction;

    public DeleteElementAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/delete-element.png"));
        putValue(NAME, "Delete");
        putValue(SHORT_DESCRIPTION, "Delete an element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startDeleteState();
    }
}

package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class SelectElementAction extends AbstractClassyAction {

    private SelectElementAction selectElementAction;

    public SelectElementAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/select-element.png"));
        putValue(NAME, "Select");
        putValue(SHORT_DESCRIPTION, "Select an element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startSelectState();
    }
}

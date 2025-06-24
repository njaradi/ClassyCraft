package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddContentAction extends AbstractClassyAction {
    private AddContentAction addContentAction;

    public AddContentAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/add-content.png"));
        putValue(NAME, "Content");
        putValue(SHORT_DESCRIPTION, "Add Content");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startAddContentState();
    }
}

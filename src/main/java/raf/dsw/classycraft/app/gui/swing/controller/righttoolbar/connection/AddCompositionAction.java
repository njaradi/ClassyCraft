package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddCompositionAction extends AbstractClassyAction {

    public AddCompositionAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/icons/add-connection.png"));
        putValue(NAME, "Composition");
        putValue(SHORT_DESCRIPTION, "Add Composition");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startAddCompositionState();
    }
}

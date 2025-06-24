package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddGeneralisationAction extends AbstractClassyAction {

    public AddGeneralisationAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/images/icons/add-connection.png"));
        putValue(NAME, "Generalisation");
        putValue(SHORT_DESCRIPTION, "Add Generalisation");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startAddGeneralisationState();
    }
}

package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class AddConnectionAction extends AbstractClassyAction {

    public AddConnectionAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
                //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/add-connection.png"));
        putValue(NAME, "Connection");
        putValue(SHORT_DESCRIPTION, "Add Connection");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPopupMenu jPopupMenu = new JPopupMenu();
        jPopupMenu.add(MainFrame.getInstance().getActionManager().getAddDependencyAction());
        jPopupMenu.add(MainFrame.getInstance().getActionManager().getAddGeneralisationAction());
        jPopupMenu.add(MainFrame.getInstance().getActionManager().getAddCompositionAction());
        jPopupMenu.add(MainFrame.getInstance().getActionManager().getAddAggregationAction());
        jPopupMenu.show(MainFrame.getInstance(), (int) MainFrame.getInstance().getMousePosition().getX(), (int) MainFrame.getInstance().getMousePosition().getY());
        jPopupMenu.setVisible(true);
    }
}

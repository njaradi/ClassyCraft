package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class MoveElementAction  extends AbstractClassyAction {

    public MoveElementAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/move-zoom.png"));
        putValue(NAME, "Move and Zoom");
        putValue(SHORT_DESCRIPTION, "Move and Zoom");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startMoveState();
    }
}

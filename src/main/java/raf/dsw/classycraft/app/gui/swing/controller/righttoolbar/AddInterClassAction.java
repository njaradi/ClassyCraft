package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class AddInterClassAction extends AbstractClassyAction {
    private AddInterClassAction addInterClassAction;

    public AddInterClassAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/add-interclass.png"));
        putValue(NAME, "InterClass");
        putValue(SHORT_DESCRIPTION, "Add a class, enum or interface");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getDesktop().startAddInterClassState();
    }
}

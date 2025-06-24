package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class RedoAction extends AbstractClassyAction {

    public RedoAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/file-redo.png"));
        putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "redo");
        setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        MainFrame.getInstance().getCommandManager().doCommand();
    }
}

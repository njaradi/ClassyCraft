package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class RedoDiagramAction extends AbstractClassyAction {

    public RedoDiagramAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/redo.png"));
        //putValue(NAME, "Redo");
        putValue(SHORT_DESCRIPTION, "Redo");
        //setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((DiagramView)MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent()).getCommandManager().doCommand();
    }
}

package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import java.awt.event.ActionEvent;

public class UndoDiagramAction extends AbstractClassyAction {

    public UndoDiagramAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/undo.png"));
        //putValue(NAME, "Undo");
        putValue(SHORT_DESCRIPTION, "Undo");
        //setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ((DiagramView)MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent()).getCommandManager().undoCommand();
    }
}

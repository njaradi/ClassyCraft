package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.gui.swing.view.PackageView;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.state.concrete.AddInterClassState;

import java.awt.event.ActionEvent;

public class TestAction extends AbstractClassyAction {
    // Ova akcija je privremeno ovdej da direktno otvori dijagram. Da ne bi gubili po zivota.

    public TestAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_DOWN_MASK));
        putValue(NAME, "Test");
        putValue(SHORT_DESCRIPTION, "Test");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected = MainFrame.getInstance().getClassyTree().getSelectedNode();
        if (selected == null) {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Nije selektovan ProjectExplorer.");
            return;
        }
        // Projekat
        MainFrame.getInstance().getClassyTree().addChild(selected);
        selected = (ClassyTreeItem) selected.getLastChild();
        // Paket
        MainFrame.getInstance().getClassyTree().addChild(selected);
        selected = (ClassyTreeItem) selected.getLastChild();
        // Dijagram
        MainFrame.getInstance().getClassyTree().addChild(selected);

        //otvara dijagram
        PackageView desktop = MainFrame.getInstance().getDesktop();
        if(desktop.getPack()!=null)
            desktop.getPack().removeSubscriber(desktop);
        ((Package) selected.getClassyNode()).addSubscriber(desktop);
        desktop.updateView((Package) selected.getClassyNode());

        MainFrame.getInstance().getDesktop().startAddInterClassState();
    }

}

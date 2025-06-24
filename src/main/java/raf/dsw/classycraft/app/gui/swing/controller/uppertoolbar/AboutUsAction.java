package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.AboutUsFrame;

import java.awt.event.ActionEvent;

public class AboutUsAction extends AbstractClassyAction {
    private AboutUsFrame aboutUsFrame;

    public AboutUsAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, ActionEvent.ALT_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/about.png"));
        putValue(NAME, "About us");
        putValue(SHORT_DESCRIPTION, "About us");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (aboutUsFrame == null) {
            aboutUsFrame = new AboutUsFrame();
        }
        aboutUsFrame.setVisible(true);
    }

}

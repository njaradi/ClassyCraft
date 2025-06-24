package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class ExitAction extends AbstractClassyAction {

    public ExitAction() {
        // deo koda za ucitavanje ikonice...
        Icon icon = loadIcon("/images/icons/exit.png");
        // bitno
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_F4, InputEvent.ALT_DOWN_MASK));
        putValue(SMALL_ICON, icon);
        putValue(NAME, "Exit");
        putValue(SHORT_DESCRIPTION, "Exit");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.exit(0);
    }
}

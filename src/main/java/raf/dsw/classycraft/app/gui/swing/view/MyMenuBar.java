package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {
    public MyMenuBar() {
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportImageAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExportCodeAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getExitAction());

        JMenu editMenu = new JMenu("Edit");
        editMenu.setMnemonic(KeyEvent.VK_E);
        editMenu.add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getRenameAuthorAction());
        editMenu.add(MainFrame.getInstance().getActionManager().getAboutUsAction());

        add(fileMenu);
        add(editMenu);
    }
}

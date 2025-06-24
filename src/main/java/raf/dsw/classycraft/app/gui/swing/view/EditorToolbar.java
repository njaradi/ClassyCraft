package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;

public class EditorToolbar extends JToolBar {
    public EditorToolbar() {
        super(VERTICAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getAddInterClassAction());
        add(MainFrame.getInstance().getActionManager().getAddConnectionAction());
        add(MainFrame.getInstance().getActionManager().getAddContentAction());
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        add(MainFrame.getInstance().getActionManager().getSelectElementAction());
        add(MainFrame.getInstance().getActionManager().getMoveElementAction());
        add(MainFrame.getInstance().getActionManager().getDuplicateElementAction());
        add(Box.createVerticalGlue());
    }
}

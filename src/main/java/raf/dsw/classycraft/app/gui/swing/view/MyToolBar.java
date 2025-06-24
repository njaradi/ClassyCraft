package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;

import javax.swing.*;

@Getter
public class MyToolBar extends JToolBar {
    private final JLabel currentStateLabel = new JLabel("Select");
    public MyToolBar() {
        super(HORIZONTAL);
        setFloatable(false);
        add(MainFrame.getInstance().getActionManager().getNewNodeAction());
        add(MainFrame.getInstance().getActionManager().getDeleteNodeAction());
        add(MainFrame.getInstance().getActionManager().getRenameNodeAction());
        add(MainFrame.getInstance().getActionManager().getRenameAuthorAction());
        add(new Separator());
        add(MainFrame.getInstance().getActionManager().getSaveAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getOpenAction());
        add(new Separator());
        add(MainFrame.getInstance().getActionManager().getSaveTemplateAction());
        add(MainFrame.getInstance().getActionManager().getOpenTemplateAction());
        add(new Separator());
        add(MainFrame.getInstance().getActionManager().getExportImageAction());
        add(MainFrame.getInstance().getActionManager().getExportCodeAction());
        add(new Separator());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getTestAction());
        add(Box.createHorizontalGlue());
        add(new JLabel("State: "));
        add(currentStateLabel);
        add(new JLabel("  "));
        add(MainFrame.getInstance().getActionManager().getExitAction());
    }
}

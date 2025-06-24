package raf.dsw.classycraft.app.gui.swing.controller.righttoolbar;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.DuplicateCommand;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.ClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.EnumPainter;
import raf.dsw.classycraft.app.model.painter.interclass.InterfacePainter;

import java.awt.event.ActionEvent;
import java.util.List;

public class DuplicateElementAction extends AbstractClassyAction {

    public DuplicateElementAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(
        //KeyEvent.VK_A, ActionEvent.CTRL_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/duplicate-element.png"));
        putValue(NAME, "Duplicate");
        putValue(SHORT_DESCRIPTION, "Duplicate selected element");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        List<DiagramElement> selected = ((DiagramView) MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent()).getSelectionModel();
        InterClass interClass;
        InterClassPainter interClassPainter;
        if (selected.size() == 1) {
            if (selected.get(0) instanceof ClassyClass) {
                interClass = new ClassyClass((InterClass) selected.get(0));
                interClassPainter = new ClassPainter(interClass);
            } else if (selected.get(0) instanceof ClassyInterface) {
                interClass = new ClassyInterface((InterClass) selected.get(0));
                interClassPainter = new InterfacePainter(interClass);
            } else {
                interClass = new ClassyEnum((InterClass) selected.get(0));
                interClassPainter = new EnumPainter(interClass);
            }
        } else {
            return;
        }
        AbstractCommand command = new DuplicateCommand(((DiagramView)MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent()).getDiagram(), interClass, interClassPainter);
        ((DiagramView)MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent()).getCommandManager().addCommand(command);
        MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent().repaint();
    }
}

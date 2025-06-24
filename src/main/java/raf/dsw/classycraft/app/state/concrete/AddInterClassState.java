package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.AddElementCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.InterClassFrame;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.ClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.EnumPainter;
import raf.dsw.classycraft.app.model.painter.interclass.InterfacePainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddInterClassState implements State {
    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {

        FontMetrics fm = diagramView.getGraphics().getFontMetrics();
        InterClass ic;
        InterClassPainter icp;
        InterClassFrame interClassFrame = new InterClassFrame(diagramView);
        if (interClassFrame.isCanceled()) return;
        if(interClassFrame.getRadioClass().isSelected())
        {
            ic = new ClassyClass(interClassFrame.getTextFieldName().getText(), diagramView.getDiagram());
            icp = new ClassPainter(ic);
            ic.setWidth(fm.stringWidth("<<Class>>"+20));
        } else if (interClassFrame.getRadioInterface().isSelected())
        {
            ic = new ClassyInterface(interClassFrame.getTextFieldName().getText(), diagramView.getDiagram());
            icp = new InterfacePainter(ic);
            ic.setWidth(fm.stringWidth("<<Interface>>"+20));
        } else if(interClassFrame.getRadioEnum().isSelected())
        {
            ic = new ClassyEnum(interClassFrame.getTextFieldName().getText(), diagramView.getDiagram());
            icp = new EnumPainter(ic);
            ic.setWidth(fm.stringWidth("<<Enum>>"+20));
        } else
        {
            return;
        }
        ic.setLocation(tP);
        ic.setHeight(5 + 3 * fm.getHeight());
        if (fm.stringWidth(ic.getName()+20) > ic.getWidth()) {
            ic.setWidth(fm.stringWidth(ic.getName()+20));
        }

        boolean intersects = true;
        int x = tP.x;
        int y = tP.y;
        while (intersects) {
            intersects = false;
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass && ic.intersects((InterClass) d)) {
                    intersects = true;
                    x += 10;
                    ic.setLocation(new Point(x, y));
                }
            }
        }

        AbstractCommand command = new AddElementCommand(diagramView.getDiagram(),ic,icp);
        diagramView.getCommandManager().addCommand(command);
        diagramView.repaint();
        //diagramView.getPainterList().add(icp);
        //diagramView.getDiagram().addChild(ic);
    }



    @Override
    public void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP) {
        //ApplicationFramework.getMessageGenerator().generateMessage(MessageType.INFORMATION, "Aktiviran mouseDrag stanja AddInterClass!");
    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP) {
        //ApplicationFramework.getMessageGenerator().generateMessage(MessageType.INFORMATION, "Aktiviran mouseRelease stanja AddInterClass!");
    }

    @Override
    public void mouseWheel(MouseWheelEvent e, DiagramView diagramView) {

    }

}

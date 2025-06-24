package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.Selection;
import raf.dsw.classycraft.app.model.painter.SelectPainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class SelectState implements State {
    Selection selection;
    SelectPainter sp;

    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {
        selection = new Selection(tP,tP);
        sp = new SelectPainter(selection);
        if (!e.isControlDown()) {
            diagramView.getSelectionModel().clear();
        }
        for(ClassyNode d: diagramView.getDiagram().getChildren())
        {
            if(d instanceof DiagramElement && ((DiagramElement)d).contains(tP))
            {
                if(diagramView.getSelectionModel().contains(d))
                    diagramView.getSelectionModel().remove(d);
                else
                    diagramView.getSelectionModel().add(((DiagramElement) d));
            }
        }
        diagramView.getPainterList().add(sp);
        diagramView.repaint();
    }

    @Override
    public void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP) {
        for(ClassyNode d: diagramView.getDiagram().getChildren())
        {
            if(d instanceof InterClass)
            {
                if(selection.containsInterClass((InterClass) d))
                {
                    if(!diagramView.getSelectionModel().contains(d))
                        diagramView.getSelectionModel().add(((DiagramElement) d));
                } else {
                    if(diagramView.getSelectionModel().contains(d))
                        diagramView.getSelectionModel().remove(((DiagramElement) d));
                }
            }
            if(d instanceof Connection)
            {
                if(selection.containsConnection((Connection) d))
                {
                    if(!diagramView.getSelectionModel().contains(d))
                        diagramView.getSelectionModel().add(((DiagramElement) d));
                } else {
                    if(diagramView.getSelectionModel().contains(d))
                        diagramView.getSelectionModel().remove(((DiagramElement) d));
                }
            }
        }
        selection.setB(tP);
        diagramView.repaint();
    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP) {
        diagramView.getPainterList().remove(sp);
        diagramView.repaint();
    }

    @Override
    public void mouseWheel(MouseWheelEvent e, DiagramView diagramView) {

    }
}

package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.DeleteSelectedCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.painter.ConnectionPainter;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class DeleteState implements State {
    List<Object> forDeletion = new ArrayList<>();
    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {
        int option = -2;
        for(DiagramElement diagramElement: diagramView.getSelectionModel())
        {
            if(diagramElement.contains(tP))
            {
                option = JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da zelite obrisati selekciju?", "Question",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (option == 0) {
                    for (DiagramElement d: diagramView.getSelectionModel()) {
                        for (ElementPainter p : diagramView.getPainterList()) {
                            if (d instanceof InterClass && p instanceof InterClassPainter && ((InterClassPainter) p).getInterClass().equals(d)) {
                                forDeletion.add(d);
                                forDeletion.add(p);
                            }
                            if (d instanceof Connection && p instanceof ConnectionPainter && ((ConnectionPainter) p).getConnection().equals(d)) {
                                forDeletion.add(d);
                                forDeletion.add(p);
                            }
                        }
                    }
                }
                break;
            }
        }
        if (option == -2) {
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof DiagramElement && ((DiagramElement) d).contains(tP)) {
                    diagramView.getSelectionModel().clear();
                    diagramView.getSelectionModel().add((DiagramElement) d);
                    diagramView.repaint();
                    option = JOptionPane.showConfirmDialog(new JFrame(), "Da li ste sigurni da zelite obrisati ovaj element?", "Question",
                            JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if (option == 0) {
                        for (ElementPainter p : diagramView.getPainterList()) {
                            if (d instanceof InterClass && p instanceof InterClassPainter && ((InterClassPainter) p).getInterClass().equals(d)) {
                                forDeletion.add(d);
                                forDeletion.add(p);
                            }
                            if (d instanceof Connection && p instanceof ConnectionPainter && ((ConnectionPainter) p).getConnection().equals(d)) {
                                forDeletion.add(d);
                                forDeletion.add(p);
                            }
                        }
                    }
                    break;
                }
            }
        }
        for (ElementPainter p : diagramView.getPainterList()) {
            if (p instanceof ConnectionPainter && !forDeletion.contains(p)) {
                if (forDeletion.contains(((ConnectionPainter) p).getConnection().getFrom())
                        || forDeletion.contains(((ConnectionPainter) p).getConnection().getTo())) {
                    forDeletion.add(((ConnectionPainter) p).getConnection());
                    forDeletion.add(p);
                }
            }
        }
        AbstractCommand command = new DeleteSelectedCommand(diagramView.getDiagram(), forDeletion);
        diagramView.getCommandManager().addCommand(command);
        forDeletion.clear();
        diagramView.repaint();
    }

    @Override
    public void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP) {

    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP) {

    }

    @Override
    public void mouseWheel(MouseWheelEvent e, DiagramView diagramView) {

    }

}

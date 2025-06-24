package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.EditContentCommand;
import raf.dsw.classycraft.app.gui.swing.view.ConnectionFrame;
import raf.dsw.classycraft.app.gui.swing.view.ContentFrame;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.connection.Aggregation;
import raf.dsw.classycraft.app.model.elements.connection.Composition;
import raf.dsw.classycraft.app.model.elements.connection.Dependency;
import raf.dsw.classycraft.app.model.elements.connection.Generalization;
import raf.dsw.classycraft.app.model.elements.content.ClassyAttribute;
import raf.dsw.classycraft.app.model.elements.content.ClassyMethod;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.model.painter.ConnectionPainter;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.model.painter.connection.AggregationPainter;
import raf.dsw.classycraft.app.model.painter.connection.CompositionPainter;
import raf.dsw.classycraft.app.model.painter.connection.DependencyPainter;
import raf.dsw.classycraft.app.model.painter.connection.GeneralizationPainter;
import raf.dsw.classycraft.app.model.painter.interclass.ClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.EnumPainter;
import raf.dsw.classycraft.app.model.painter.interclass.InterfacePainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class AddContentState implements State {
    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {
        AbstractCommand command;
        for (ClassyNode d : diagramView.getDiagram().getChildren()) {
            if (d instanceof InterClass && ((InterClass) d).contains(tP)) {
                InterClass d1;
                InterClassPainter p1;
                ContentFrame contentFrame = new ContentFrame(diagramView, (InterClass) d);
                if (contentFrame.isCanceled())
                    return;
                if (d instanceof ClassyClass) {
                    d1 = new ClassyClass((ClassyClass) d);
                    d1.setName(contentFrame.getClassName().getText());
                    ((ClassyClass) d1).setAbstract(contentFrame.getAbstractCheckBox().isSelected());
                    ((ClassyClass) d1).getAttributes().clear();
                    for (Object a : contentFrame.getAttributeModel().toArray()) {
                        ((ClassyClass) d1).getAttributes().add((ClassyAttribute) a);
                    }
                    ((ClassyClass) d1).getMethods().clear();
                    for (Object a : contentFrame.getMethodModel().toArray()) {
                        ((ClassyClass) d1).getMethods().add((ClassyMethod) a);
                    }
                    p1 = new ClassPainter(d1);
                } else if (d instanceof ClassyInterface) {
                    d1 = new ClassyInterface((ClassyInterface) d);
                    d1.setName(contentFrame.getClassName().getText());
                    ((ClassyInterface) d1).getMethods().clear();
                    for (Object a : contentFrame.getMethodModel().toArray()) {
                        ((ClassyInterface) d1).getMethods().add((ClassyMethod) a);
                    }
                    p1 = new InterfacePainter(d1);
                } else {
                    d1 = new ClassyEnum((ClassyEnum) d);
                    d1.setName(contentFrame.getClassName().getText());
                    ((ClassyEnum) d1).getEnumerators().clear();
                    for (Object a : contentFrame.getEnumModel().toArray()) {
                        ((ClassyEnum) d1).getEnumerators().add(((String) a).toUpperCase());
                    }
                    p1 = new EnumPainter(d1);
                }
                for (ElementPainter p : diagramView.getPainterList()) {
                    if (p instanceof InterClassPainter && ((InterClassPainter)p).getInterClass().equals(d)) {
                        command = new EditContentCommand(diagramView.getDiagram(), (DiagramElement) d, p, d1, p1);
                        diagramView.getCommandManager().addCommand(command);
                        break;
                    }
                }
                break;
            }
            if (d instanceof Connection && ((Connection) d).contains(tP)) {
                ConnectionFrame connectionFrame = new ConnectionFrame(diagramView, (Connection) d);
                if (connectionFrame.isCanceled()) {
                    return;
                }
                Connection d1;
                ConnectionPainter p1;
                if (connectionFrame.getConnectionType().getSelectedItem() == "Aggregation") {
                    d1 = new Aggregation(diagramView.getDiagram());
                    d1.setTo(((Connection) d).getTo());
                    d1.setFrom(((Connection) d).getFrom());
                    p1 = new AggregationPainter(d1);
                } else if (connectionFrame.getConnectionType().getSelectedItem() == "Composition") {
                    d1 = new Composition(diagramView.getDiagram());
                    d1.setTo(((Connection) d).getTo());
                    d1.setFrom(((Connection) d).getFrom());
                    p1 = new CompositionPainter(d1);
                } else if (connectionFrame.getConnectionType().getSelectedItem() == "Dependency") {
                    d1 = new Dependency(diagramView.getDiagram());
                    d1.setTo(((Connection) d).getTo());
                    d1.setFrom(((Connection) d).getFrom());
                    p1 = new DependencyPainter(d1);
                } else /*if (connectionFrame.getConnectionType().getSelectedItem() == "Generalization")*/ {
                    d1 = new Generalization(diagramView.getDiagram());
                    d1.setTo(((Connection) d).getTo());
                    d1.setFrom(((Connection) d).getFrom());
                    p1 = new GeneralizationPainter(d1);
                }
                if (connectionFrame.isReverse()) {
                    var temp = d1.getFrom();
                    d1.setFrom(d1.getTo());
                    d1.setTo(temp);
                }
                for (ElementPainter p : diagramView.getPainterList()) {
                    if (p instanceof ConnectionPainter && ((ConnectionPainter)p).getConnection().equals(d)) {
                        command = new EditContentCommand(diagramView.getDiagram(), (DiagramElement) d, p, d1, p1);
                        diagramView.getCommandManager().addCommand(command);
                        break;
                    }
                }
                break;

            }
        }
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

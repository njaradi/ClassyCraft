package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.AddConnectionCommand;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.connection.Aggregation;
import raf.dsw.classycraft.app.model.painter.ConnectionPainter;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.model.painter.connection.AggregationPainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.util.ArrayList;
import java.util.List;

public class AddAggregationState implements State {
    private Connection connection;
    private ConnectionPainter p;
    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {
        for (ClassyNode d : diagramView.getDiagram().getChildren()) {
            if (d instanceof InterClass && ((InterClass) d).contains(tP.x,tP.y)) {
                connection = new Aggregation(diagramView.getDiagram());
                connection.setFrom((InterClass) d);
                break;
            }
        }
        if (connection != null) {
            connection.setA(connection.getFrom().center());
            connection.setB(tP);
            p = new AggregationPainter(connection);
            diagramView.getPainterList().add(p);
            diagramView.getDiagram().addChild(connection);
        }
    }

    @Override
    public void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP) {
        if (connection != null) {
            connection.intersectA(tP);
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass && ((InterClass) d).contains(tP)) {
                    connection.intersectA(((InterClass) d).center());
                    connection.intersectB((InterClass) d);
                    break;
                }
                connection.setB(tP);
            }
            diagramView.repaint();
        }
    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP) {
        List<Object> deleteList = new ArrayList<>();
        if (connection != null) {
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass && ((InterClass) d).contains(tP)) {
                    connection.intersectA(((InterClass) d).center());
                    connection.intersectB((InterClass) d);
                    connection.setTo((InterClass) d);
                    AbstractCommand command = new AddConnectionCommand(connection,p,diagramView.getDiagram());
                    diagramView.getCommandManager().addCommand(command);
                }
            }
            if (connection.getTo() == null) {
                diagramView.getDiagram().removeChild(connection);
                diagramView.getPainterList().remove(p);
            }

            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof Connection && !d.equals(connection) && connection.getTo()!=null) {
                    if((connection.getFrom().equals(((Connection) d).getFrom()) && connection.getTo().equals(((Connection) d).getTo())) ||
                            (connection.getFrom().equals(((Connection) d).getTo()) && connection.getTo().equals(((Connection) d).getFrom())))
                    {
                        deleteList.add(d);
                        for(ElementPainter cp: diagramView.getPainterList())
                        {
                            if(cp instanceof ConnectionPainter)
                            {
                                if(((ConnectionPainter) cp).getConnection() == d)
                                {
                                    deleteList.add(cp);
                                }
                            }
                        }
                    }
                }
            }
            connection = null;
            p = null;
        }
        for (Object o: deleteList) {
            if (o instanceof ElementPainter) {
                diagramView.getPainterList().remove(o);
            }
            if (o instanceof DiagramElement) {
                diagramView.getSelectionModel().remove(o);
                diagramView.getDiagram().removeChild((ClassyNode) o);
            }
        }
        deleteList.clear();
        diagramView.repaint();
    }

    @Override
    public void mouseWheel(MouseWheelEvent e, DiagramView diagramView) {

    }



}

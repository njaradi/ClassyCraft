package raf.dsw.classycraft.app.state.concrete;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;

public class MoveState implements State {
    Point startstart;
    Point start;

    @Override
    public void mousePress(MouseEvent e, DiagramView diagramView, Point tP) {
        start = tP;
        startstart = tP;
    }

    @Override
    public void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP) {
        if (diagramView.getSelectionModel().isEmpty()) {
            for (ClassyNode d: diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass) {
                    ((InterClass) d).setLocation(new Point(((InterClass) d).getLocation().x+tP.x-start.x, ((InterClass) d).getLocation().y+tP.y-start.y));
                }
            }
        } else {
            for (DiagramElement d: diagramView.getSelectionModel()) {
                if (d instanceof InterClass) {
                    ((InterClass) d).setLocation(new Point(((InterClass) d).getLocation().x+tP.x-start.x, ((InterClass) d).getLocation().y+tP.y-start.y));
                    // Moglo je biti samo ovo i for jedan premjesten ispod ovog... ((InterClass) d).getLocation().translate(e.getX()-start.x, e.getY()-start.y);
                }
            }
        }
        start = tP;
    }

    @Override
    public void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP) {
        if (!diagramView.getSelectionModel().isEmpty()) {
            boolean intersectsExist = false;
            for (DiagramElement d: diagramView.getSelectionModel()) {
                for (ClassyNode dd: diagramView.getDiagram().getChildren()) {
                    if (d instanceof InterClass && dd instanceof InterClass && !diagramView.getSelectionModel().contains(dd)) {
                        if (((InterClass) d).intersects((InterClass) dd)) {
                            intersectsExist = true;
                            break;
                        }
                    }
                }
                if (intersectsExist) break;
            }
            if (!intersectsExist) return;
            for (DiagramElement d: diagramView.getSelectionModel()) {
                if (d instanceof InterClass) {
                    ((InterClass) d).setLocation(new Point(((InterClass) d).getLocation().x+startstart.x-start.x,
                            ((InterClass) d).getLocation().y+startstart.y-start.y));
                }
            }
        }
    }

    @Override
    public void mouseWheel(MouseWheelEvent e, DiagramView diagramView) {
        if (e.getWheelRotation() < 0 && diagramView.getScaleFactor() <= 2.5) {
            diagramView.setScaleFactor(diagramView.getScaleFactor() * 1.1);
        }
        if (e.getWheelRotation() > 0 && diagramView.getScaleFactor() >= 0.65) {
            diagramView.setScaleFactor(diagramView.getScaleFactor() / 1.1);
        }
        diagramView.setMouseX(e.getX());
        diagramView.setMouseY(e.getY());
        diagramView.setAt(new AffineTransform());
        diagramView.getAt().scale(diagramView.getScaleFactor(), diagramView.getScaleFactor());
        diagramView.repaint();

    }
}

package raf.dsw.classycraft.app.state;

import raf.dsw.classycraft.app.gui.swing.view.DiagramView;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public interface State {
    void mousePress(MouseEvent e, DiagramView diagramView, Point tP);
    void mouseDrag(MouseEvent e, DiagramView diagramView, Point tP);
    void mouseRelease(MouseEvent e, DiagramView diagramView, Point tP);
    void mouseWheel(MouseWheelEvent e, DiagramView diagramView);
}


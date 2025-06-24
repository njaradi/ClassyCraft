package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.command.CommandManager;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.painter.ConnectionPainter;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.model.painter.SelectPainter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.state.concrete.DeleteState;
import raf.dsw.classycraft.app.state.concrete.MoveState;
import raf.dsw.classycraft.app.state.concrete.SelectState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class DiagramView extends JPanel implements ISubscriber {
    private AffineTransform at = new AffineTransform();
    private Diagram diagram;
    private final List<ElementPainter> painterList = new ArrayList<>();
    private final List<DiagramElement> selectionModel = new ArrayList<>();
    private final CommandManager commandManager = new CommandManager(false);
    // TODO: Vrati zbog laptop ekrana
    private double scaleFactor = 1;
    private double mouseX;
    private double mouseY;

    public DiagramView(Diagram d) {
        this.diagram = d;
        setBackground(Color.WHITE);
        MouseController mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        addMouseWheelListener(mouseController);
    }

    @Override
    public void update(Object notification) {
        this.repaint();
    }

    @Override
    public void repaint() {
        //rearrange();
        /*if (this.diagram != null) {
            for (ClassyNode d: this.diagram.getChildren()) {
                if (d instanceof Connection) {
                    ((Connection) d).intersectA();
                    ((Connection) d).intersectB();
                }
            }
        }*/
        super.repaint();
    }

    public class MouseController extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            super.mousePressed(e);
            MainFrame.getInstance().getDesktop().getStateManager().getCurrent().mousePress(e, DiagramView.this, new Point((int) (e.getX() / scaleFactor), (int) (e.getY() / scaleFactor)));
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            super.mouseDragged(e);
            MainFrame.getInstance().getDesktop().getStateManager().getCurrent().mouseDrag(e, DiagramView.this, new Point((int) (e.getX() / scaleFactor), (int) (e.getY() / scaleFactor)));
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            super.mouseReleased(e);
            MainFrame.getInstance().getDesktop().getStateManager().getCurrent().mouseRelease(e, DiagramView.this, new Point((int) (e.getX() / scaleFactor), (int) (e.getY() / scaleFactor)));
        }

        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            super.mouseWheelMoved(e);
            MainFrame.getInstance().getDesktop().getStateManager().getCurrent().mouseWheel(e, DiagramView.this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        graphics2D.setTransform(at);

        if(!(MainFrame.getInstance().getDesktop().getStateManager().getCurrent() instanceof SelectState
                || MainFrame.getInstance().getDesktop().getStateManager().getCurrent() instanceof DeleteState
                || MainFrame.getInstance().getDesktop().getStateManager().getCurrent() instanceof MoveState))
        {
            this.selectionModel.clear();
        }

        for (ElementPainter p : this.painterList) {
            if (p instanceof ConnectionPainter) {
                if (selectionModel.contains(((ConnectionPainter)p).getConnection())){
                    p.selectPaint(graphics2D);
                }
            }
            if (p instanceof InterClassPainter) {
                if (selectionModel.contains(((InterClassPainter) p).getInterClass())) {
                    p.selectPaint(graphics2D);
                }
            }
        }
        for (ElementPainter p : this.painterList) {
            if (p instanceof InterClassPainter) {
                p.paint(graphics2D);
            }
        }
        for (ElementPainter p : this.painterList) {
            if (p instanceof ConnectionPainter) {
                ((ConnectionPainter) p).getConnection().intersectA();
                ((ConnectionPainter) p).getConnection().intersectB();
                p.paint(graphics2D);
            }
            if (p instanceof SelectPainter) {
                p.paint(graphics2D);
            }
        }
    }

    @Override
    public String toString() {
        return "DiagramView " + diagram.getName();
    }
}

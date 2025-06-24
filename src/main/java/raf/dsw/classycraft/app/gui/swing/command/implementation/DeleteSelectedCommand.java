package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import java.util.ArrayList;
import java.util.List;

public class DeleteSelectedCommand extends AbstractCommand {
    List<Object> forDeletion;
    private final Diagram diagram;

    public DeleteSelectedCommand(Diagram diagram, List<Object> forDeletion) {
        this.diagram = diagram;
        this.forDeletion = new ArrayList<>(forDeletion);
    }

    @Override
    public void undoCommand() {
        for (Object o: forDeletion) {
            if (o instanceof InterClass) {
                diagram.addChild((ClassyNode) o);
            }
        }
        for (Object o: forDeletion) {
            if (o instanceof ElementPainter) {
                diagram.giveDiagramView().getPainterList().add((ElementPainter) o);
            }
            if (o instanceof Connection) {
                diagram.addChild((ClassyNode) o);
            }
        }
    }

    @Override
    public void doCommand() {
        for (Object o: forDeletion) {
            if (o instanceof ElementPainter) {
                diagram.giveDiagramView().getPainterList().remove(o);
            }
            if (o instanceof DiagramElement) {
                diagram.giveDiagramView().getSelectionModel().remove(o);
                diagram.removeChild((ClassyNode) o);
            }
        }
    }
}

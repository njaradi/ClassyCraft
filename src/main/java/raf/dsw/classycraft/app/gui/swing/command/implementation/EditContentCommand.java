package raf.dsw.classycraft.app.gui.swing.command.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.painter.ElementPainter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

@Getter
@Setter
public class EditContentCommand extends AbstractCommand {
    private Diagram diagram;
    private DiagramElement oldElement;
    private DiagramElement newElement;
    private ElementPainter oldPainter;
    private ElementPainter newPainter;

    public EditContentCommand(Diagram diagram, DiagramElement oldElement, ElementPainter oldPainter, DiagramElement newElement, ElementPainter newPainter) {
        this.diagram = diagram;
        this.oldElement = oldElement;
        this.newElement = newElement;
        this.oldPainter = oldPainter;
        this.newPainter = newPainter;
        if (newElement instanceof InterClass) ((InterClass) newElement).setLocation(((InterClass) oldElement).getLocation());
    }

    @Override
    public void doCommand() {
        diagram.removeChild(oldElement);
        diagram.addChild(newElement);
        diagram.giveDiagramView().getPainterList().remove(oldPainter);
        diagram.giveDiagramView().getPainterList().add(newPainter);
        if (oldElement instanceof InterClass) {
            for (ClassyNode n : diagram.getChildren()) {
                if (n instanceof Connection) {
                    if (((Connection) n).getFrom().equals(oldElement)) {
                        ((Connection) n).setFrom((InterClass) newElement);
                    }
                    if (((Connection) n).getTo().equals(oldElement)) {
                        ((Connection) n).setTo((InterClass) newElement);
                    }
                }
            }
        }
    }

    @Override
    public void undoCommand() {
        diagram.removeChild(newElement);
        diagram.addChild(oldElement);
        diagram.giveDiagramView().getPainterList().remove(newPainter);
        diagram.giveDiagramView().getPainterList().add(oldPainter);
        if (newElement instanceof InterClass) {
            for (ClassyNode n : diagram.getChildren()) {
                if (n instanceof Connection) {
                    if (((Connection) n).getFrom().equals(newElement)) {
                        ((Connection) n).setFrom((InterClass) oldElement);
                    }
                    if (((Connection) n).getTo().equals(newElement)) {
                        ((Connection) n).setTo((InterClass) oldElement);
                    }
                }
            }
        }
    }

    @Override
    public String toString() {
        if (oldPainter == null)
            return "EditContentCommand{" +
                    "oldElement=" + oldElement.getName() +
                    ", newElement=" + newElement.getName() +
                    ", diagram=" + diagram.getName() +
                    '}';
        else
            return "EditContentCommand{" +
                    "oldElement=" + oldElement.getName() +
                    ", newElement=" + newElement.getName() +
                    ", diagram=" + diagram.getName() +
                    ", painter=" + oldPainter +
                    '}';
    }
}

package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

public class DuplicateCommand extends AbstractCommand {
    private final Diagram diagram;
    private final InterClass interClass;
    private final InterClassPainter interClassPainter;

    public DuplicateCommand(Diagram diagram, InterClass interClass, InterClassPainter interClassPainter) {
        this.diagram = diagram;
        this.interClass = interClass;
        this.interClassPainter = interClassPainter;
    }

    @Override
    public void doCommand() {
        diagram.addChild(interClass);
        diagram.giveDiagramView().getPainterList().add(interClassPainter);
    }

    @Override
    public void undoCommand() {
        diagram.removeChild(interClass);
        diagram.giveDiagramView().getPainterList().remove(interClassPainter);
    }
}

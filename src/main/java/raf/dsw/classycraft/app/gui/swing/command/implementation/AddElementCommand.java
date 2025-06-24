package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.painter.InterClassPainter;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

public class AddElementCommand extends AbstractCommand {
    private final Diagram diagram;
    private final InterClass interClass;
    private final InterClassPainter painter;
    public AddElementCommand(Diagram diagram, InterClass interClass, InterClassPainter painter) {
        this.diagram = diagram;
        this.interClass = interClass;
        this.painter = painter;
    }

    @Override
    public void doCommand() {
        diagram.giveDiagramView().getPainterList().add(painter);
        diagram.addChild(interClass);
    }

    @Override
    public void undoCommand() {
        diagram.giveDiagramView().getPainterList().remove(painter);
        diagram.removeChild(interClass);
    }
}

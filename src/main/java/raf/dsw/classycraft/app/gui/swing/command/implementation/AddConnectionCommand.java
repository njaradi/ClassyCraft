package raf.dsw.classycraft.app.gui.swing.command.implementation;

import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.painter.ConnectionPainter;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

public class AddConnectionCommand extends AbstractCommand {
    private Diagram diagram;
    private Connection connection;
    private ConnectionPainter painter;

    public AddConnectionCommand(Connection connection, ConnectionPainter painter, Diagram diagram) {
        this.connection = connection;
        this.painter = painter;
        this.diagram = diagram;
    }

    @Override
    public void doCommand() {
        if(!diagram.giveDiagramView().getPainterList().contains(painter))
            diagram.giveDiagramView().getPainterList().add(painter);
        if(!diagram.getChildren().contains(connection))
            diagram.addChild(connection);
    }

    @Override
    public void undoCommand() {
        diagram.giveDiagramView().getPainterList().remove(painter);
        diagram.removeChild(connection);
    }
}

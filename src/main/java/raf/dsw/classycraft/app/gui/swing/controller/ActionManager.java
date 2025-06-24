package raf.dsw.classycraft.app.gui.swing.controller;

import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.*;
import raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection.AddAggregationAction;
import raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection.AddCompositionAction;
import raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection.AddDependencyAction;
import raf.dsw.classycraft.app.gui.swing.controller.righttoolbar.connection.AddGeneralisationAction;
import raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar.*;

@Getter
public class ActionManager {
    private RenameAuthorAction renameAuthorAction;
    private ExitAction exitAction;
    private AboutUsAction aboutUsAction;
    private NewNodeAction newNodeAction;
    private DeleteNodeAction deleteNodeAction;
    private RenameNodeAction renameNodeAction;
    private SaveAction saveAction;
    private TestAction testAction;
    private SaveAsAction saveAsAction;
    private OpenAction openAction;
    private OpenTemplateAction openTemplateAction;
    private SaveTemplateAction saveTemplateAction;
    private ExportCodeAction exportCodeAction;

    private AddConnectionAction addConnectionAction;
    private AddContentAction addContentAction;
    private AddInterClassAction addInterClassAction;
    private DeleteElementAction deleteElementAction;
    private SelectElementAction selectElementAction;
    private MoveElementAction moveElementAction;
    private DuplicateElementAction duplicateElementAction;

    private AddDependencyAction addDependencyAction;
    private AddGeneralisationAction addGeneralisationAction;
    private AddCompositionAction addCompositionAction;
    private AddAggregationAction addAggregationAction;

    private UndoDiagramAction undoDiagramAction;
    private UndoAction undoAction;
    private RedoDiagramAction redoDiagramAction;
    private RedoAction redoAction;

    private ExportImageAction exportImageAction;

    public ActionManager() {
        initialiseActions();
    }

    private void initialiseActions() {
        exitAction = new ExitAction();
        aboutUsAction = new AboutUsAction();
        newNodeAction = new NewNodeAction();
        deleteNodeAction = new DeleteNodeAction();
        renameAuthorAction = new RenameAuthorAction();
        renameNodeAction = new RenameNodeAction();
        saveAction = new SaveAction();
        saveAsAction = new SaveAsAction();
        openAction = new OpenAction();
        testAction = new TestAction();

        undoDiagramAction = new UndoDiagramAction();
        redoDiagramAction = new RedoDiagramAction();
        undoAction = new UndoAction();
        redoAction = new RedoAction();
        openTemplateAction = new OpenTemplateAction();
        saveTemplateAction = new SaveTemplateAction();
        exportCodeAction = new ExportCodeAction();

        addConnectionAction = new AddConnectionAction();
        addContentAction = new AddContentAction();
        addInterClassAction = new AddInterClassAction();
        deleteElementAction = new DeleteElementAction();
        selectElementAction = new SelectElementAction();
        moveElementAction = new MoveElementAction();
        duplicateElementAction = new DuplicateElementAction();

        addAggregationAction = new AddAggregationAction();
        addCompositionAction = new AddCompositionAction();
        addDependencyAction = new AddDependencyAction();
        addGeneralisationAction = new AddGeneralisationAction();

        exportImageAction = new ExportImageAction();
    }
}

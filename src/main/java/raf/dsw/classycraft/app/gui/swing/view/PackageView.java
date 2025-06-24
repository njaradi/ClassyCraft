package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import raf.dsw.classycraft.app.model.elements.connection.Aggregation;
import raf.dsw.classycraft.app.model.elements.connection.Composition;
import raf.dsw.classycraft.app.model.elements.connection.Dependency;
import raf.dsw.classycraft.app.model.elements.connection.Generalization;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.model.painter.connection.AggregationPainter;
import raf.dsw.classycraft.app.model.painter.connection.CompositionPainter;
import raf.dsw.classycraft.app.model.painter.connection.DependencyPainter;
import raf.dsw.classycraft.app.model.painter.connection.GeneralizationPainter;
import raf.dsw.classycraft.app.model.painter.interclass.ClassPainter;
import raf.dsw.classycraft.app.model.painter.interclass.EnumPainter;
import raf.dsw.classycraft.app.model.painter.interclass.InterfacePainter;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.state.StateManager;

import javax.swing.*;
@Getter
public class PackageView extends JPanel implements ISubscriber {
    private final JTabbedPane tabs = new JTabbedPane();
    private final JLabel project = new JLabel();
    private final JLabel author = new JLabel();
    private Package pack;
    private final EditorToolbar editToolbar;

    private final StateManager stateManager = new StateManager();

    public PackageView() {
        editToolbar = new EditorToolbar();
        BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
        setLayout(box);
        Box tabEdit = Box.createHorizontalBox();
        Box projBox = Box.createHorizontalBox();
        Box authBox = Box.createHorizontalBox();
        projBox.add(project);
        projBox.add(Box.createHorizontalGlue());
        authBox.add(author);
        authBox.add(Box.createHorizontalGlue());

        Box aboveTabs = Box.createHorizontalBox();
        Box vertical = Box.createVerticalBox();
        vertical.add(projBox);
        vertical.add(authBox);
        aboveTabs.add(vertical);
        aboveTabs.add(new JButton(MainFrame.getInstance().getActionManager().getUndoDiagramAction()));
        aboveTabs.add(new JButton(MainFrame.getInstance().getActionManager().getRedoDiagramAction()));

        add(aboveTabs);
        tabEdit.add(tabs);
        tabEdit.add(editToolbar);
        add(tabEdit);
    }

    public void updateView(Package pack){
        this.pack = pack;

        updateNames(pack);
        tabs.removeAll();

        for(ClassyNode d:pack.getChildren())
        {
            if(d instanceof Diagram)
            {
                DiagramView diagramView;
                if (((Diagram)d).giveDiagramView() == null) {
                    diagramView = new DiagramView((Diagram) d);
                    ((Diagram) d).addSubscriber(diagramView);
                    for (ClassyNode n: ((Diagram) d).getChildren()) {
                        if (n instanceof ClassyClass) {
                            diagramView.getPainterList().add(new ClassPainter((ClassyClass) n));
                        } else if (n instanceof ClassyInterface) {
                            diagramView.getPainterList().add(new InterfacePainter((ClassyInterface) n));
                        } else if (n instanceof ClassyEnum) {
                            diagramView.getPainterList().add(new EnumPainter((ClassyEnum) n));
                        } else if (n instanceof Aggregation) {
                            diagramView.getPainterList().add(new AggregationPainter((Aggregation) n));
                        } else if (n instanceof Composition) {
                            diagramView.getPainterList().add(new CompositionPainter((Composition) n));
                        } else if (n instanceof Dependency) {
                            diagramView.getPainterList().add(new DependencyPainter((Dependency) n));
                        } else if (n instanceof Generalization) {
                            diagramView.getPainterList().add(new GeneralizationPainter((Generalization) n));
                        }
                    }
                } else {
                    diagramView = ((Diagram)d).giveDiagramView();
                }
                tabs.addTab(d.getName(), diagramView);
            }
        }
    }

    public void updateNames(Package pack) {
        if(pack.getParent() instanceof Project)
        {
            project.setText(pack.getParent().getName());
            author.setText(((Project)pack.getParent()).getAutor());
        }
        else
        {
            updateView((Package) pack.getParent());
        }
    }

    @Override
    public void update(Object notification) {
        this.updateView((Package) notification);
    }

    public void startAddCompositionState(){
        this.stateManager.setAddComopsitionState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Composition");
    }
    public void startAddAggregationState(){
        this.stateManager.setAddAggregationState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Aggregation");
    }
    public void startAddDependencyState(){
        this.stateManager.setAddDependencyState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Dependency");
    }
    public void startAddGeneralisationState(){
        this.stateManager.setAddGeneralisationState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Generalisation");
    }
    public void startAddContentState(){
        this.stateManager.setAddContentState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Content");
    }
    public void startAddInterClassState(){
        this.stateManager.setAddInterClassState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Interclass");
    }
    public void startDeleteState(){
        this.stateManager.setDeleteState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Delete");
    }
    public void startSelectState(){
        this.stateManager.setSelectState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Select");
    }
    public void startMoveState(){
        this.stateManager.setMoveState();
        MainFrame.getInstance().getToolBar().getCurrentStateLabel().setText("Move");
    }
}

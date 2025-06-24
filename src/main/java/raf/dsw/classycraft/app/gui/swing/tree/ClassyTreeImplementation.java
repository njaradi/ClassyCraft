package raf.dsw.classycraft.app.gui.swing.tree;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.gui.swing.command.AbstractCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.DeleteNodeCommand;
import raf.dsw.classycraft.app.gui.swing.command.implementation.NewNodeCommand;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeModel;
import raf.dsw.classycraft.app.gui.swing.tree.view.ClassyTreeView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
import raf.dsw.classycraft.app.repository.factory.FactoryUtils;
import raf.dsw.classycraft.app.repository.factory.NodeFactory;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
@Getter
@Setter
public class ClassyTreeImplementation implements ClassyTree {
    private ClassyTreeView treeView;
    private ClassyTreeModel treeModel;

    @Override
    public ClassyTreeView generateTree(ProjectExplorer projectExplorer) {
        ClassyTreeItem root = new ClassyTreeItem(projectExplorer);
        treeModel = new ClassyTreeModel(root);
        treeView = new ClassyTreeView(treeModel);
        return treeView;
    }

    @Override
    public void addChild(ClassyTreeItem parent) {
        if (parent.getClassyNode() instanceof Diagram) {
            for (ClassyNode c: ((Diagram) parent.getClassyNode()).getChildren()) {
                if (c instanceof InterClass) parent.add(new ClassyTreeItem(c));
                treeView.expandPath(treeView.getSelectionPath());
                SwingUtilities.updateComponentTreeUI(treeView);
            }
            return;
        }
        ClassyNode child = createNode(parent.getClassyNode());
        if (child == null) return;
        ClassyTreeItem x = new ClassyTreeItem(child);
        if (child instanceof Diagram) ((Diagram) child).addSubscriber(x);
        parent.add(x);

        AbstractCommand command = new NewNodeCommand(parent, x);
        MainFrame.getInstance().getCommandManager().addCommand(command);

        ((ClassyNodeComposite) parent.getClassyNode()).addChild(child);
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public ClassyTreeItem getSelectedNode() {
        return (ClassyTreeItem) treeView.getLastSelectedPathComponent();
    }

    public void resetLastSelected() {
        treeView.setSelectionPath(null);
    }

    @Override
    public void removeChild(ClassyTreeItem child) {
        if (!(child.getClassyNode().getParent() instanceof Diagram) && child.getClassyNode().getParent() != null)
            ((ClassyNodeComposite)child.getClassyNode().getParent()).removeChild(child.getClassyNode());

        AbstractCommand command = new DeleteNodeCommand((ClassyTreeItem) child.getParent(), child);
        MainFrame.getInstance().getCommandManager().addCommand(command);

        child.removeFromParent();
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public void renameChild(ClassyTreeItem selected) {
        (selected.getClassyNode()).setName((JOptionPane.showInputDialog("Ime cvora:",(selected.getClassyNode()).getName())));
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    public ClassyNode createNode(ClassyNode parent) {
        NodeFactory factory = FactoryUtils.returnNodeFactory(parent);
        return factory.createNode(parent);
    }

    public void loadProject(Project node) {
        ClassyTreeItem loadedProject = new ClassyTreeItem(node);
        treeModel.getRoot().add(loadedProject);

        ClassyNodeComposite mapNode = (ClassyNodeComposite) treeModel.getRoot().getClassyNode();
        mapNode.addChild(node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

        if (!node.getChildren().isEmpty()) {
            for (ClassyNode n : node.getChildren()) {
                loadChildren(loadedProject, n);
            }
        }
    }

    public void loadChildren(ClassyTreeItem parentTreeItem, ClassyNode node) {
        ClassyTreeItem loadedNode = new ClassyTreeItem(node);
        parentTreeItem.add(loadedNode);

        ClassyNodeComposite mapNode = (ClassyNodeComposite) parentTreeItem.getClassyNode();
        if(!mapNode.getChildren().contains(node))
            mapNode.addChild(node);

        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);

        if (node instanceof ClassyNodeComposite && !((ClassyNodeComposite)node).getChildren().isEmpty()) {
            for (ClassyNode n : ((ClassyNodeComposite) node).getChildren()) {
                loadChildren(loadedNode, n);
            }
        }
    }
}

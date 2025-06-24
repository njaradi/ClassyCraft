package raf.dsw.classycraft.app.gui.swing.tree.view;

import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;
import java.net.URL;

public class ClassyTreeCellRenderer extends DefaultTreeCellRenderer {
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, sel,expanded, leaf, row, hasFocus);
        URL imageURL = null;

        if(((ClassyTreeItem)value).getClassyNode() instanceof ProjectExplorer)
        {
            imageURL = getClass().getResource("/images/icons/explorer.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof Project)
        {
            imageURL = getClass().getResource("/images/icons/project.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof Package)
        {
            imageURL = getClass().getResource("/images/icons/package.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof Diagram)
        {
            imageURL = getClass().getResource("/images/icons/diagram.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof ClassyClass)
        {
            imageURL = getClass().getResource("/images/icons/class.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof ClassyInterface)
        {
            imageURL = getClass().getResource("/images/icons/interface.png");
        }
        if(((ClassyTreeItem)value).getClassyNode() instanceof ClassyEnum)
        {
            imageURL = getClass().getResource("/images/icons/enum.png");
        }

        Icon icon = null;
        if (imageURL != null)
            icon = new ImageIcon(imageURL);
        setIcon(icon);

        return this;
    }
}

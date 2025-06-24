package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Project;
import raf.dsw.classycraft.app.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProjectExplorerFactory extends NodeFactory {
    @Override
    public ClassyNode createNode(ClassyNode parent) {
        var value = JOptionPane.showInputDialog("Naziv autora:");
        if (value == null) return null;
        return new Project(defaultName(parent), parent, value);
    }

    @Override
    public String defaultName(ClassyNode parent) {
        String name="New Project";
        List<Integer> integerList = new ArrayList<>();
        boolean existsNP = false;
        for(ClassyNode p : ((ProjectExplorer) parent).getChildren())
        {
            if(Objects.equals(p.getName(), "New Project"))
            {
                existsNP =true;
                if(((ProjectExplorer) parent).getChildren().size() == 1 )
                    return "New Project (1)";

            }

            if(p.getName().contains("New Project (") && p.getName().contains(")"))
            {
                String[] split1 = p.getName().split("\\(");
                String[] split2 = split1[split1.length-1].split("\\)");
                try {
                    integerList.add(Integer.valueOf((split2[0])));
                }catch (Exception e)
                {
                    System.out.println("nije int");
                }
            }
        }
        if(!existsNP)
            return name;
        if(!integerList.isEmpty())
        {
            Collections.sort(integerList);
            int i;
            for(i=0; i<integerList.size()-1;i++)
            {
                if(integerList.get(i)!=integerList.get(i+1)-1)
                {
                    return "New Project ("+(integerList.get(i)+1)+")";
                }
            }
            return "New Project ("+(integerList.get(i)+1)+")";
        }
        return name;
    }
}

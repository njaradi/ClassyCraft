package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ProjectFactory extends NodeFactory {
    @Override
    public ClassyNode createNode(ClassyNode parent) {
        return new Package(defaultName(parent), parent);
    }

    @Override
    public String defaultName(ClassyNode parent) {
        String name="New Package";
        List<Integer> integerList = new ArrayList<>();
        boolean existsNP = false;
        for(ClassyNode p : ((Project) parent).getChildren())
        {
            if(Objects.equals(p.getName(), "New Package"))
            {
                existsNP =true;
                if(((Project) parent).getChildren().size() == 1 )
                    return "New Package (1)";

            }

            if(p.getName().contains("New Package (") && p.getName().contains(")"))
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
                    return "New Package ("+(integerList.get(i)+1)+")";
                }
            }
            return "New Package ("+(integerList.get(i)+1)+")";
        }
        return name;
    }
}

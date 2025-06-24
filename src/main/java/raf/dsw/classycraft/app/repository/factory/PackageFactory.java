package raf.dsw.classycraft.app.repository.factory;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class PackageFactory extends NodeFactory {
    @Override
    public ClassyNode createNode(ClassyNode parent) {
        String[] options = { "Diagram", "Package", "Cancel" };
        var choice = JOptionPane.showOptionDialog(null, "Create diagram or package?", "Question",
                JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);
        if (choice == 0) {
            return new Diagram(defaultNameDiagram(parent), parent);
        }
        if (choice == 1) {
            return new Package(defaultName(parent), parent);
        }
        return null;
    }


    public String defaultNameDiagram(ClassyNode parent) {
        String name="New Diagram";
        List<Integer> integerList = new ArrayList<>();
        boolean existsND = false;
        for(ClassyNode d : ((Package) parent).getChildren())
        {
            if(d instanceof Diagram)
            {
                if(Objects.equals(d.getName(), "New Diagram"))
                {
                    existsND =true;
                    System.out.println("Nasao novi dijagram");
                }

                if(d.getName().contains("New Diagram (") && d.getName().contains(")"))
                {
                    String[] split1 = d.getName().split("\\(");
                    String[] split2 = split1[split1.length-1].split("\\)");
                    try {
                        integerList.add(Integer.valueOf((split2[0])));
                    }catch (Exception e)
                    {
                        System.out.println("nije int");
                    }
                }
            }
        }
        if(!existsND)
            return name;
        else if(integerList.isEmpty())
        {
            return "New Diagram (1)";
        }
        else
        {
            Collections.sort(integerList);
            int i;
            for(i=0; i<integerList.size()-1;i++)
            {
                if(integerList.get(i)!=integerList.get(i+1)-1)
                {
                    return "New Diagram ("+(integerList.get(i)+1)+")";
                }
            }
            return "New Diagram ("+(integerList.get(i)+1)+")";
        }
    }

    @Override
    public String defaultName(ClassyNode parent) {
        String name="New Package";
        List<Integer> integerList = new ArrayList<>();
        boolean existsNP = false;
        for(ClassyNode p : ((Package) parent).getChildren())
        {
            if(p instanceof Package)
            {
                if(Objects.equals(p.getName(), "New Package"))
                {
                    existsNP =true;
                    System.out.println("Nasao novi paket");
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
        }
        if(!existsNP)
            return name;
        else if(integerList.isEmpty())
            return "New Package (1)";
        else
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
    }
}
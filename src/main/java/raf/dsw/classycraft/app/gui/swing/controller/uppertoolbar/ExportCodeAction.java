package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.model.DiagramElement;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.content.ClassyAttribute;
import raf.dsw.classycraft.app.model.elements.content.ClassyMethod;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

public class ExportCodeAction extends AbstractClassyAction {

    public ExportCodeAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent));
        putValue(SMALL_ICON, loadIcon("/images/icons/code.png"));
        putValue(NAME, "Export Code");
        putValue(SHORT_DESCRIPTION, "Export to code");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected =  MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || !(selected.getClassyNode() instanceof Project))
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije selektovan projekat");
            return;
        }
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        Path path;
        if (jFileChooser.showDialog(new JFrame(), "Export") == JFileChooser.APPROVE_OPTION) {
            path = Paths.get(jFileChooser.getCurrentDirectory().getPath());
            createFolder(selected.getClassyNode(),path);

            path = Paths.get(path + "/" + selected.getClassyNode().getName());

             createFolders(selected.getClassyNode(),path);
//            for(ClassyNode p :((Project) selected.getClassyNode()).getChildren())
//            {
//                createFolder(p,path);
//            }

        }
    }
    //elementi nisu composite
    //private Stack<ClassyNode> stack = new Stack<>();

    public void createFolders(ClassyNode p,Path path)
    {
        Stack<ClassyNode> stack = new Stack<>();
        Stack<ClassyNode> textStack = new Stack<>();
        if(p instanceof Project){
            stack.addAll(((Project)p).getChildren());
        }
        else if (p instanceof Package) {
            stack.addAll(((Package)p).getChildren());
        }
        else if(p instanceof Diagram)
        {
            textStack.addAll(((Diagram)p).getChildren());
        }
        while (!stack.isEmpty())
        {
            ClassyNode c = stack.pop();
            createFolder(c,path);
            createFolders(c, Paths.get(path + "/" + c.getName()));
        }
        while (!textStack.isEmpty())
        {
            ClassyNode element = textStack.pop();
            File myCode = null;
            if(element instanceof InterClass)
                myCode = new File(path+"/"+element.getName()+".java");
            try {
                if(myCode!=null){
                    myCode.createNewFile();
                    createFile(myCode, (DiagramElement) element);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }



    public void createFile(File myCode, DiagramElement element)
    {
        FileWriter fw = null;
        PrintWriter pw = null;
        try{
            fw = new FileWriter(myCode, false);
            pw = new PrintWriter(fw);
            pw.println(writeCode(element));
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (pw != null) {
                    pw.close();
                    fw.close();
                }
            } catch (IOException e) {
                System.err.println("create file failed: " + e.getMessage());
            }
        }
    }

    public String writeCode(DiagramElement element)
    {
        String code = "package ";
        code+= givePackage(element.getParent())+";\n";
        if(element instanceof ClassyClass)
        {
            code = writeClassCode((ClassyClass) element,code);
        } else if (element instanceof ClassyInterface) {
            code = writeInterfaceCode((ClassyInterface) element,code);
        } else if (element instanceof ClassyEnum) {
            code = writeEnumCode((ClassyEnum) element,code);
        }
        return code;
    }

    private String givePackage(ClassyNode element) {

        if(element instanceof Project)
        {
            return element.getName().toLowerCase().replace(" ","");
        }
        return givePackage(element.getParent())+"."+element.getName().toLowerCase().replace(" ","");
    }

    private String writeEnumCode(ClassyEnum element,String code) {
        code+="public enum "+element.getName()+" {\n   ";
        StringBuilder codeBuilder = new StringBuilder(code);
        for(String enumerator : element.getEnumerators())
        {
            codeBuilder.append(enumerator).append(", ");
        }
        code = codeBuilder.toString();
        code+="\n}";
        return code;
    }

    private String writeInterfaceCode(ClassyInterface element,String code) {
        code+="public interface "+element.getName()+" {\n";
        StringBuilder codeBuilder = new StringBuilder(code);
        for(ClassyMethod method : element.getMethods())
        {
            codeBuilder.append("    ").append(method.getReturnType()).append(" ").append(method.getName()).append("(").append(method.getArguments()).append(");\n");
        }
        code = codeBuilder.toString();
        code+="}";
        return code;
    }

    private String writeClassCode(ClassyClass element,String code) {
        if(element.isAbstract())
            code+="public abstract class "+element.getName()+" {\n";
        else
            code+="public class "+element.getName()+" {\n";
        StringBuilder codeBuilder = new StringBuilder(code);
        for(ClassyAttribute attribute : element.getAttributes())
        {
            if(attribute.isStatic())
            {
                codeBuilder.append("     ").append(attribute.getVisibility().toString().toLowerCase()).append(" static ").append(attribute.getType()).append(" ").append(attribute.getName()).append(";\n");
            }
            else
            {
                codeBuilder.append("     ").append(attribute.getVisibility().toString().toLowerCase()).append(" ").append(attribute.getType()).append(" ").append(attribute.getName()).append(";\n");
            }
        }
        codeBuilder.append("\n");
        for(ClassyMethod method : element.getMethods())
        {
            if(method.isStatic())
            {
                codeBuilder.append("     ").append(method.getVisibility().toString().toLowerCase()).append(" static ").append(method.getReturnType()).append(" ").append(method.getName()).append("(").append(method.getArguments()).append(");\n");
            } else if (method.isAbstract()) {
                codeBuilder.append("     ").append(method.getVisibility().toString().toLowerCase()).append(" abstract ").append(method.getReturnType()).append(" ").append(method.getName()).append("(").append(method.getArguments()).append(");\n");
            }else
                codeBuilder.append("     ").append(method.getVisibility().toString().toLowerCase()).append(" ").append(method.getReturnType()).append(" ").append(method.getName()).append("(").append(method.getArguments()).append(");\n");
        }
        code = codeBuilder.toString();
        code+="}";
        return code;
    }

    public void createFolder(ClassyNode n, Path newPath)
    {
        boolean created = new File(newPath+"/"+n.getName()).mkdir();
        //System.out.println("created"+newPath+"/"+n.getName());
    }
}

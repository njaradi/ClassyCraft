package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.Visible;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.model.elements.content.ClassyAttribute;
import raf.dsw.classycraft.app.model.elements.content.ClassyMethod;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
@Getter
@Setter
public class ContentFrame extends JDialog{
    private JTextField className = new JTextField();
    private JCheckBox abstractCheckBox = new JCheckBox("Abstract");

    private JComboBox<Visible> visibilityAttribute = new JComboBox<>();
    private JTextField textTypeAttribute = new JTextField("tip argumenta");
    private JTextField textAttributeName = new JTextField("naziv");
    private JCheckBox staticACheckBox = new JCheckBox("Static");
    private JButton addAttributeButton = new JButton("+");
    private DefaultListModel<ClassyAttribute> attributeModel = new DefaultListModel<>();
    private JList<ClassyAttribute> jAttributeList = new JList<>( attributeModel );

    private JButton removeAttribute = new JButton("-");

    private JComboBox<Visible> visibilityMethod = new JComboBox<>();
    private JCheckBox staticCheckBox = new JCheckBox("Static");
    private JCheckBox abrstractMethodCheckBox = new JCheckBox("Abstract");
    private JTextField textTypeMethod = new JTextField("ReturnType");
    private JTextField textMethodName = new JTextField("name");
    private JTextField textMethodArguments = new JTextField("arguments,...");
    private JButton addMethodButton = new JButton("+");
    private DefaultListModel<ClassyMethod> methodModel = new DefaultListModel<>();
    private JList<ClassyMethod> jMethodList = new JList<>( methodModel );

    private JButton removeMethod = new JButton("-");

    private DefaultListModel<String> enumModel = new DefaultListModel<>();
    private JList<String> jEnumList = new JList<>( enumModel );

    private JButton okButton = new JButton("Ok");
    private boolean isCanceled = true;

    public ContentFrame(DiagramView diagramView, InterClass interClass){
        JPanel jPanel = new JPanel();
        setSize(600,600);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Edit");
        Box box = new Box(BoxLayout.Y_AXIS);
        box.setBorder(new EmptyBorder(10,10,10,10));

        Box xBox = new Box(BoxLayout.X_AXIS);
        xBox.setBorder(new EmptyBorder(10,10,10,10));

        className.setText(interClass.getName());
        xBox.add(className);

        box.add(xBox);
        Box argumentBox = new Box(BoxLayout.X_AXIS);
        argumentBox.setBorder(new EmptyBorder(10,10,10,10));
        argumentBox.add(visibilityAttribute);
            visibilityAttribute.addItem(Visible.PRIVATE);
            visibilityAttribute.addItem(Visible.PUBLIC);
            visibilityAttribute.addItem(Visible.PROTECTED);
            visibilityAttribute.addItem(Visible.DEFAULT);
        argumentBox.add(staticACheckBox);
        argumentBox.add(textTypeAttribute);
        argumentBox.add(textAttributeName);
        argumentBox.add(addAttributeButton);
        argumentBox.add(removeAttribute);

        Box methodBox = new Box(BoxLayout.X_AXIS);
        methodBox.setBorder(new EmptyBorder(10,10,10,10));
        methodBox.add(visibilityMethod);
        visibilityMethod.addItem(Visible.PRIVATE);
        visibilityMethod.addItem(Visible.PUBLIC);
        visibilityMethod.addItem(Visible.PROTECTED);
        visibilityMethod.addItem(Visible.DEFAULT);
        methodBox.add(staticCheckBox);
        methodBox.add(abrstractMethodCheckBox);
        methodBox.add(textTypeMethod);
        methodBox.add(textMethodName);
        methodBox.add(textMethodArguments);
        methodBox.add(addMethodButton);
        methodBox.add(removeMethod);


        if(interClass instanceof ClassyClass)
        {
            abstractCheckBox.setSelected(((ClassyClass) interClass).isAbstract());
            xBox.add(abstractCheckBox);

            for(ClassyAttribute a:  ((ClassyClass) interClass).getAttributes())
            {
                attributeModel.addElement(a);
            }
            for(ClassyMethod a:  ((ClassyClass) interClass).getMethods())
            {
                methodModel.addElement(a);
            }

            box.add(argumentBox);
            box.add(new JScrollPane(jAttributeList));
            box.add(methodBox);
            box.add(new JScrollPane(jMethodList));
        } else if (interClass instanceof ClassyInterface) {
            box.add(methodBox);

            for(ClassyMethod a:  ((ClassyInterface) interClass).getMethods())
            {
                methodModel.addElement(a);
            }

            box.add(new JScrollPane(jMethodList));
        }
        else {
            argumentBox.remove(visibilityAttribute);
            argumentBox.remove(textTypeAttribute);
            argumentBox.remove(staticACheckBox);
            argumentBox.remove(jAttributeList);
            argumentBox.add(jEnumList);
            for(String a: ((ClassyEnum) interClass).getEnumerators())
            {
                enumModel.addElement(a.toUpperCase());
            }
            box.add(argumentBox);
            box.add(new JScrollPane(jEnumList));
        }

        addAttributeButton.addActionListener(e -> {
            if (interClass instanceof ClassyClass) {
                if (textTypeAttribute.getText().isEmpty()) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Atribut mora imati tip.");
                    return;
                }
                if (textAttributeName.getText().isEmpty()) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Atribut mora imati ime.");
                    return;
                }
                ClassyAttribute attribute = new ClassyAttribute(
                        (Visible) visibilityAttribute.getSelectedItem(),textTypeAttribute.getText(),textAttributeName.getText(),staticACheckBox.isSelected());
                if (attributeModel.contains(attribute)) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Atribut ovog imena vec postoji.");
                    return;
                }
                attributeModel.addElement(attribute);
            } else {
                String s = textAttributeName.getText().toUpperCase();
                if (enumModel.contains(s)) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Enumerator ovog imena vec postoji.");
                    return;
                }
                enumModel.addElement(s);
            }
        });

        addMethodButton.addActionListener(e -> {
            if (staticCheckBox.isSelected() && getAbrstractMethodCheckBox().isSelected()) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Metoda ne moze biti i abstract i static.");
                return;
            }
            if (textTypeMethod.getText().isEmpty()) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Metoda mora imati povratni tip ili void.");
                return;
            }
            if (textMethodName.getText().isEmpty()) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Metoda mora imati ime.");
                return;
            }
            ClassyMethod method = new ClassyMethod(
                    (Visible) visibilityMethod.getSelectedItem(),textTypeMethod.getText(),
                    textMethodName.getText(),staticCheckBox.isSelected(),textMethodArguments.getText());
            method.setAbstract(getAbrstractMethodCheckBox().isSelected());
            if (methodModel.contains(method)) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Metoda ovog imena i ovih argumenata vec postoji.");
                return;
            }
            methodModel.addElement(method);
        });

        removeAttribute.addActionListener(e -> {
            if(jAttributeList.getSelectedValue()!=null)
            {
                attributeModel.remove(jAttributeList.getSelectedIndex());
            }
            if(jEnumList.getSelectedValue()!=null)
            {
                enumModel.remove(jEnumList.getSelectedIndex());
            }
        });
        removeMethod.addActionListener(e -> {
            if(jMethodList.getSelectedValue()!=null)
            {
                methodModel.remove(jMethodList.getSelectedIndex());
            }
        });

        okButton.addActionListener(e -> {
            isCanceled = false;
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass && d.getName().equals(className.getText()) && !d.equals(interClass)) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Element ovog imena vec postoji.");
                    return;
                }
            }
            if (className.getText().isEmpty()) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Ime je prazno.");
                return;
            }
            dispose();
        });

        box.add(okButton);

        jPanel.add(box);
        setContentPane(jPanel);
        setModal(true);
        setVisible(true);
        pack();
    }


}

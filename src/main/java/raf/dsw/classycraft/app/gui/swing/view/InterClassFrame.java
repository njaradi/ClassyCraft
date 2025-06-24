package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.model.elements.InterClass;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import javax.swing.*;

@Getter
public class InterClassFrame extends JDialog {

    JRadioButton radioClass = new JRadioButton("Class");
    JRadioButton radioInterface = new JRadioButton("Interface");
    JRadioButton radioEnum = new JRadioButton("Enum");
    ButtonGroup buttonGroup = new ButtonGroup();
    JTextField textFieldName = new JTextField();
    JButton okButton = new JButton("Ok");
    private boolean canceled = true;

    public InterClassFrame(DiagramView diagramView){
        JPanel jPanel = new JPanel();
        setSize(250, 120);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("New element");
        Box box = new Box(BoxLayout.Y_AXIS);

        Box box1 = new Box(BoxLayout.X_AXIS);
        Box box2 = new Box(BoxLayout.X_AXIS);
        Box box3 = new Box(BoxLayout.X_AXIS);

        box1.add(new JLabel("Ime: "));
        box1.add(Box.createGlue());
        box1.add(textFieldName);

        buttonGroup.add(radioClass);
        buttonGroup.add(radioInterface);
        buttonGroup.add(radioEnum);
        box2.add(new JLabel("Tip:"));
        box2.add(Box.createGlue());
        box2.add(radioClass);
        box2.add(radioInterface);
        box2.add(radioEnum);

        box.add(box2);
        box.add(box1);
        okButton.addActionListener(e -> {
            for (ClassyNode d : diagramView.getDiagram().getChildren()) {
                if (d instanceof InterClass && d.getName().equals(textFieldName.getText())) {
                    ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Element ovog imena vec postoji.");
                    return;
                }
            }
            if (buttonGroup.getSelection() == null) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Nije selektovan tip elementa.");
                return;
            }
            if (textFieldName.getText().isEmpty()) {
                ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR, "Ime je prazno.");
                return;
            }
            canceled = false;
            dispose();
        });
        box3.add(Box.createGlue());
        box3.add(okButton);
        box.add(box3);
        jPanel.add(box);
        setContentPane(jPanel);
        setModal(true);
        setVisible(true);
    }
}
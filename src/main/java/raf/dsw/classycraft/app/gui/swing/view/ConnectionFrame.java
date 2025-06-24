package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.elements.Connection;
import raf.dsw.classycraft.app.model.elements.connection.Aggregation;
import raf.dsw.classycraft.app.model.elements.connection.Composition;
import raf.dsw.classycraft.app.model.elements.connection.Dependency;

import javax.swing.*;
@Getter
@Setter
public class ConnectionFrame extends JDialog{
    private JComboBox<String> connectionType = new JComboBox<>();
    private JButton reverseButton = new JButton("Reverse");
    private JButton okButton = new JButton("Ok");
    private boolean isCanceled = true;
    private boolean reverse = false;
    public ConnectionFrame(DiagramView diagramView,Connection connection){
        JPanel jPanel = new JPanel();
        setSize(550, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("Edit");
        Box box = new Box(BoxLayout.Y_AXIS);

        connectionType.addItem("Aggregation");
        connectionType.addItem("Composition");
        connectionType.addItem("Dependency");
        connectionType.addItem("Generalization");
        if(connection instanceof Aggregation) {
            connectionType.setSelectedIndex(0);
        }else if(connection instanceof Composition){
            connectionType.setSelectedIndex(1);
        } else if (connection instanceof Dependency) {
            connectionType.setSelectedIndex(2);
        } else{
            connectionType.setSelectedIndex(3);
        }

        box.add(connectionType);
        box.add(reverseButton);
        reverseButton.addActionListener(e -> reverse = !reverse);

        okButton.addActionListener(e -> {
            isCanceled = false;
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

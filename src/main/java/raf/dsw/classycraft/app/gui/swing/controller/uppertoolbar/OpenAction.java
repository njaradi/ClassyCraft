package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class OpenAction extends AbstractClassyAction {

    public OpenAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/open.png"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open project");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON","json"));
        if(jFileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            Project project;
            try {
                project = objectMapper.readValue(jFileChooser.getSelectedFile(), Project.class);
                ((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).loadProject(project);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

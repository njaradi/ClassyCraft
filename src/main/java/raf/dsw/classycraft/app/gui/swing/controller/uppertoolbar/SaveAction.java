package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Project;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

public class SaveAction extends AbstractClassyAction {

    public SaveAction() {
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/save.png"));
        putValue(NAME, "Save");
        putValue(SHORT_DESCRIPTION, "Save project");
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
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON","json"));
        if (((Project) selected.getClassyNode()).getPath() != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(((Project) selected.getClassyNode()).getPath(), selected.getClassyNode());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (jFileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
            ((Project) selected.getClassyNode()).setPath(jFileChooser.getSelectedFile());
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(jFileChooser.getSelectedFile(), selected.getClassyNode());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

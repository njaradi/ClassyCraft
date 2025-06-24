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
import java.io.IOException;

public class SaveAsAction extends AbstractClassyAction {

    public SaveAsAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent));
        putValue(SMALL_ICON, loadIcon("/images/icons/save-as.png"));
        putValue(NAME, "Save As");
        putValue(SHORT_DESCRIPTION, "Save project to other path");
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
        jFileChooser.setSelectedFile(((Project) selected.getClassyNode()).getPath());
        jFileChooser.setFileFilter(new FileNameExtensionFilter("JSON","json"));
        if (jFileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
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

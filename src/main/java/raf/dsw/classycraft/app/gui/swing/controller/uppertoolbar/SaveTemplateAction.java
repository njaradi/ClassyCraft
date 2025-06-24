package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Diagram;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class SaveTemplateAction extends AbstractClassyAction{

    public SaveTemplateAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/temple-save.png"));
        putValue(NAME, "Save template");
        putValue(SHORT_DESCRIPTION, "Save Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ClassyTreeItem selected =  MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || !(selected.getClassyNode() instanceof Diagram))
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije selektovan dijagram");
            return;
        }

        JFileChooser jFileChooser = new JFileChooser();
        URL url = getClass().getResource("/templates");
        if(url!=null)
            jFileChooser.setCurrentDirectory(new File(url.getFile()));

        if (jFileChooser.showSaveDialog(new JFrame()) == JFileChooser.APPROVE_OPTION) {
            ObjectMapper objectMapper = new ObjectMapper();
            try {
                objectMapper.writeValue(jFileChooser.getSelectedFile(), selected.getClassyNode());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

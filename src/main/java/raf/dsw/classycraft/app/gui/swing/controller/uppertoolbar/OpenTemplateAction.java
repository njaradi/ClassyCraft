package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import com.fasterxml.jackson.databind.ObjectMapper;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class OpenTemplateAction extends AbstractClassyAction {
    public OpenTemplateAction() {
        //(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/temple-open.png"));
        putValue(NAME, "Open template");
        putValue(SHORT_DESCRIPTION, "Open Template");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ClassyTreeItem selected =  MainFrame.getInstance().getClassyTree().getSelectedNode();
        if(selected == null || !(selected.getClassyNode() instanceof Package))
        {
            ApplicationFramework.getMessageGenerator().generateMessage(MessageType.ERROR,"Nije selektovan paket");
            return;
        }
        JFileChooser jFileChooser = new JFileChooser();
        URL url = getClass().getResource("/templates");
        if(url!=null)
            jFileChooser.setCurrentDirectory(new File(url.getFile()));
            //jFileChooser.setSelectedFile();
        if(jFileChooser.showOpenDialog(new JFrame()) == JFileChooser.APPROVE_OPTION)
        {
            ObjectMapper objectMapper = new ObjectMapper();
            Diagram diagram;
            try {
                diagram = objectMapper.readValue(jFileChooser.getSelectedFile(), Diagram.class);
                ((ClassyTreeImplementation) MainFrame.getInstance().getClassyTree()).loadChildren(selected,diagram);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}

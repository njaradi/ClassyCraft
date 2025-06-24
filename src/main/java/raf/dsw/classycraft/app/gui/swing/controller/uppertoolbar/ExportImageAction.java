package raf.dsw.classycraft.app.gui.swing.controller.uppertoolbar;

import raf.dsw.classycraft.app.gui.swing.controller.AbstractClassyAction;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportImageAction extends AbstractClassyAction {
    public ExportImageAction() {
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, InputEvent.SHIFT_DOWN_MASK));
        putValue(SMALL_ICON, loadIcon("/images/icons/image.png"));
        putValue(NAME, "Export image");
        putValue(SHORT_DESCRIPTION, "Export image");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel paintPane = ((DiagramView) MainFrame.getInstance().getDesktop().getTabs().getSelectedComponent());
        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("PNG","png"));
        if(jFileChooser.showDialog(new JFrame(), "Export") == JFileChooser.APPROVE_OPTION)
        {
            BufferedImage image = new BufferedImage(paintPane.getWidth(), paintPane.getHeight(), BufferedImage.TYPE_INT_RGB);
            Graphics2D g = image.createGraphics();
            paintPane.printAll(g);
            g.dispose();
            try {
                String filename = jFileChooser.getSelectedFile().toString().toLowerCase();
                if (!filename.endsWith(".png"))
                    filename = jFileChooser.getSelectedFile().toString() + ".png";
                ImageIO.write(image, "png", new File(filename));
            } catch (IOException exp) {
                throw new RuntimeException(exp);
            }
        }
    }
}

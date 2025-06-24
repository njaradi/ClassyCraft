package raf.dsw.classycraft.app.gui.swing.view;

import javax.swing.*;
import java.net.URL;

public class AboutUsFrame extends JFrame {
    public AboutUsFrame() {
        JPanel jPanel = new JPanel();
        setSize(550, 350);
        setLocationRelativeTo(null);
        setResizable(false);
        setTitle("About Us");
        Box box1 = new Box(BoxLayout.Y_AXIS);
        Box box2 = new Box(BoxLayout.Y_AXIS);

        JLabel img1 = new JLabel();
        URL imageURL = getClass().getResource("/images/creators/ivana.jpg");
        if (imageURL != null) {
            Icon imageIcon1 = new ImageIcon(imageURL);
            img1.setIcon(imageIcon1);
        } else {
            System.err.println("Resource not found: ivana.jpg");
        }
        box1.add(img1);
        box1.add(new JLabel("Ivana Njaradi"));
        box1.add(new JLabel("RN 67/2022"));
        box1.add(new JLabel("injaradi6722rn@raf.rs"));

        JLabel img2 = new JLabel();
        imageURL = getClass().getResource("/images/creators/matija.jpg");
        if (imageURL != null) {
            Icon imageIcon2 = new ImageIcon(imageURL);
            img2.setIcon(imageIcon2);
        } else {
            System.err.println("Resource not found: matija.jpg");
        }
        box2.add(img2);
        box2.add(new JLabel("Matija Tomić"));
        box2.add(new JLabel("RN 43/2022"));
        box2.add(new JLabel("mtomic4322rn@raf.rs"));

        jPanel.add(box1);
        jPanel.add(box2);
        setContentPane(jPanel);
    }
}

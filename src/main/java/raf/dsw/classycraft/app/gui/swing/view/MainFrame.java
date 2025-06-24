package raf.dsw.classycraft.app.gui.swing.view;

import lombok.Getter;
import raf.dsw.classycraft.app.core.ApplicationFramework;
import raf.dsw.classycraft.app.gui.swing.command.CommandManager;
import raf.dsw.classycraft.app.gui.swing.controller.ActionManager;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTree;
import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.tree.model.ClassyTreeItem;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.observer.Message;
import raf.dsw.classycraft.app.observer.MessageType;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.implementation.Package;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@Getter
public class MainFrame extends JFrame implements ISubscriber {
    private static MainFrame instance;
    private ActionManager actionManager;
    //buduca polja za sve komponente view-a na glavnom prozoru
    private MyToolBar toolBar;
    private ClassyTree classyTree;
    private PackageView desktop;
    private CommandManager commandManager;

    private MainFrame() {
        ApplicationFramework.getMessageGenerator().addSubscriber(this);
    }

    private void initialize() {
        actionManager = new ActionManager();
        commandManager = new CommandManager(true);
        classyTree = new ClassyTreeImplementation();
        desktop = new PackageView();


        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;
        setSize(screenWidth * 2 / 3, screenHeight * 2 / 3);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("ClassyCrafT");

        MyMenuBar menu = new MyMenuBar();
        setJMenuBar(menu);

        toolBar = new MyToolBar();
        add(toolBar, BorderLayout.NORTH);

        //JTree projectExplorer = mapTree.generateTree(ApplicationFramework.getInstance().getMapRepository().getProjectExplorer());
        JTree tree = classyTree.generateTree(ApplicationFramework.getInstance().getClassyRepository().getRoot());
        tree.setToggleClickCount(0);
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                if (selPath == null) return;
                ClassyNode selNode = ((ClassyTreeItem)selPath.getLastPathComponent()).getClassyNode();
                if (e.getClickCount() == 2 && selNode instanceof Package) {
                    if(desktop.getPack()!=null)
                        desktop.getPack().removeSubscriber(desktop);
                    ((Package) selNode).addSubscriber(desktop);
                    desktop.updateView((Package) selNode);
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(tree);
        scrollPane.setMaximumSize(new Dimension(200,150));

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, scrollPane, desktop);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
    }

    public static MainFrame getInstance() {
        if (instance == null) {
            instance = new MainFrame();
            instance.initialize();
        }
        return instance;
    }

    @Override
    public void update(Object notification) {
        JFrame jFrame = new JFrame();
        if (((Message) notification).getType() == MessageType.WARNING) {
            JOptionPane.showMessageDialog(jFrame, ((Message) notification).getText(), "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (((Message) notification).getType() == MessageType.ERROR) {
            JOptionPane.showMessageDialog(jFrame, ((Message) notification).getText(), "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(jFrame, ((Message) notification).getText(), "Information", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}

package raf.dsw.classycraft.app.gui.swing.command;

import raf.dsw.classycraft.app.gui.swing.tree.ClassyTreeImplementation;
import raf.dsw.classycraft.app.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {

    //lista koja predstavlja stek na kome se nalaze konkretne izvršene komande
    private final List<AbstractCommand> commands = new ArrayList<>();
    //pokazivač steka, sadrži redni broj komande za undo / redo operaciju
    private int currentCommand = 0;

    private final boolean treeCommandManager;

    public CommandManager(boolean treeCommandManager) {
        this.treeCommandManager = treeCommandManager;
    }

    /*
     * Dodaje novu komandu na stek i poziva izvršavanje komande
     */
    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size())
            commands.remove(currentCommand);
        commands.add(command);
        doCommand();
    }

    /*
     * Metoda koja poziva izvršavanje konkretne komande
     */
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getTreeView());
            if (treeCommandManager) MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
            //ApplicationFramework.getInstance().getGui().enableUndoAction();
        }
        if(currentCommand==commands.size()){
            if (treeCommandManager) MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
            //ApplicationFramework.getInstance().getGui().disableRedoAction();
        }
    }

    /*
     * Metoda koja poziva redo konkretne komande
     */
    public void undoCommand(){
        if(currentCommand > 0){
            if (treeCommandManager) MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
            //ApplicationFramework.getInstance().getGui().enableRedoAction();
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(((ClassyTreeImplementation)MainFrame.getInstance().getClassyTree()).getTreeView());
        }
        if(currentCommand==0){
            if (treeCommandManager) MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
            //ApplicationFramework.getInstance().getGui().disableUndoAction();
        }
    }

}

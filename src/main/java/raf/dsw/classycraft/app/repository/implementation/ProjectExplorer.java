package raf.dsw.classycraft.app.repository.implementation;

import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

public class ProjectExplorer extends ClassyNodeComposite {
    public ProjectExplorer(String name) {
        super(name, null);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child instanceof Project) {
            Project project = (Project) child;
            if (!this.getChildren().contains(project)) {
                this.getChildren().add(project);
            }
        }
    }


}

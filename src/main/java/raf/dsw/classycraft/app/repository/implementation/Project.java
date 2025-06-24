package raf.dsw.classycraft.app.repository.implementation;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

import java.io.File;

@Getter
@Setter
public class Project extends ClassyNodeComposite {

    private String autor;
    //FOLDER path - gde se projekat cuva na racunaru
    private File path;

    public Project() {
        super();
    }

    public Project(String name, ClassyNode parent) {
        super(name, parent);
    }

    public Project(String name, ClassyNode parent, String autor, File path) {
        super(name, parent);
        this.autor = autor;
        this.path = path;
    }

    public Project(String name, ClassyNode parent, String autor) {
        super(name, parent);
        this.autor = autor;
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child instanceof Package) {
            Package pack = (Package) child;
            if (!this.getChildren().contains(pack)) {
                this.getChildren().add(pack);
            }
        }
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        if (this.getChildren() != null) {
            for (ClassyNode p : this.getChildren()) {
                ((Package) p).notifySubscribers(p);
            }
        }
    }

    public void setAutor(String autor) {
        this.autor = autor;
        for(ClassyNode p : this.getChildren())
        {
            ((Package)p).notifySubscribers(p);
        }
    }
}

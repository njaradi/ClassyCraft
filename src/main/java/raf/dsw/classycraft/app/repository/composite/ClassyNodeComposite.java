package raf.dsw.classycraft.app.repository.composite;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ClassyNodeComposite extends ClassyNode {

    @JsonManagedReference
    List<ClassyNode> children;

    public ClassyNodeComposite() {
        super();
    }

    public List<ClassyNode> getChildren() {
        return children;
    }

    public abstract void addChild(ClassyNode child);

    public void removeChild(ClassyNode child)
    {
        this.getChildren().remove(child);

    }

    public ClassyNodeComposite(String name, ClassyNode parent) {
        super(name, parent);
        this.children = new ArrayList<>();
    }
}

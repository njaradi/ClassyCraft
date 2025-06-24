package raf.dsw.classycraft.app.model.elements;

import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.Visible;

@Getter
@Setter
public abstract class ClassContent {
    private Visible visibility;
    private String name;
    private boolean isStatic;

    public ClassContent(Visible visibility, String name, boolean isStatic) {
        this.visibility = visibility;
        this.name = name;
        this.isStatic = isStatic;
    }

    public ClassContent() {

    }
}

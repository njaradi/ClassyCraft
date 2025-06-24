package raf.dsw.classycraft.app.repository.composite;

import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.model.elements.connection.Aggregation;
import raf.dsw.classycraft.app.model.elements.connection.Composition;
import raf.dsw.classycraft.app.model.elements.connection.Dependency;
import raf.dsw.classycraft.app.model.elements.connection.Generalization;
import raf.dsw.classycraft.app.model.elements.content.ClassyAttribute;
import raf.dsw.classycraft.app.model.elements.content.ClassyMethod;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyClass;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyEnum;
import raf.dsw.classycraft.app.model.elements.interclass.ClassyInterface;
import raf.dsw.classycraft.app.repository.implementation.Diagram;
import raf.dsw.classycraft.app.repository.implementation.Package;
import raf.dsw.classycraft.app.repository.implementation.Project;

import java.util.Objects;
@Getter
@Setter
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Project.class, name = "Project"),
        @JsonSubTypes.Type(value = Package.class, name = "Package"),
        @JsonSubTypes.Type(value = Diagram.class, name = "Diagram"),
        @JsonSubTypes.Type(value = Aggregation.class, name = "Aggregation"),
        @JsonSubTypes.Type(value = Composition.class, name = "Composition"),
        @JsonSubTypes.Type(value = Dependency.class, name = "Dependency"),
        @JsonSubTypes.Type(value = Generalization.class, name = "Generalization"),
        @JsonSubTypes.Type(value = ClassyClass.class, name = "ClassyClass"),
        @JsonSubTypes.Type(value = ClassyInterface.class, name = "ClassyInterface"),
        @JsonSubTypes.Type(value = ClassyEnum.class, name = "ClassyEnum"),
        @JsonSubTypes.Type(value = ClassyAttribute.class, name = "ClassyAttribute"),
        @JsonSubTypes.Type(value = ClassyMethod.class, name = "ClassyMethod")}
)
public abstract class ClassyNode {

    private String name;
    @JsonBackReference
    private ClassyNode parent;

    public ClassyNode(String name, ClassyNode parent) {
        this.name = name;
        this.parent = parent;
    }

    public ClassyNode() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ClassyNode that = (ClassyNode) o;
        return Objects.equals(name, that.name) && Objects.equals(parent, that.parent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, parent);
    }
}

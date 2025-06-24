package raf.dsw.classycraft.app.repository.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;

@Getter
@Setter
public class Package extends ClassyNodeComposite implements IPublisher {
    @JsonIgnore
    private ISubscriber subscriber;

    public Package() {
    }

    public Package(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child instanceof Package){
            Package pack = (Package) child;
            if (!this.getChildren().contains(pack)){
                this.getChildren().add(pack);
            }
        } else if (child instanceof Diagram) {
            Diagram diagram = (Diagram) child;
            if (!this.getChildren().contains(diagram)){
                this.getChildren().add(diagram);
                notifySubscribers(this);
            }
        }
    }


    @Override
    public void removeChild(ClassyNode child) {
        super.removeChild(child);
        notifySubscribers(this);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        this.subscriber = sub;
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        this.subscriber = null;
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null)
        {
            return;
        }
        for(ClassyNode p: this.getChildren())
        {
            if(p instanceof Package)
            {
                ((Package) p).notifySubscribers(p);
            }
        }
        if(this.subscriber == null)
        {
            return;
        }
        subscriber.update(notification);
    }
}

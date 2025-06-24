package raf.dsw.classycraft.app.repository.implementation;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import raf.dsw.classycraft.app.gui.swing.view.DiagramView;
import raf.dsw.classycraft.app.observer.IPublisher;
import raf.dsw.classycraft.app.observer.ISubscriber;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;
import raf.dsw.classycraft.app.repository.composite.ClassyNodeComposite;
@Getter
public class Diagram extends ClassyNodeComposite implements IPublisher {
    @JsonIgnore
    ISubscriber subscriberTreeItem;
    @JsonIgnore
    ISubscriber subscriberView;

    public Diagram() {
    }

    @Override
    public void addChild(ClassyNode child) {
        if (child != null) this.getChildren().add(child);
        notifySubscribers(this);
    }

    public Diagram(String name, ClassyNode parent) {
        super(name, parent);
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub instanceof DiagramView)
        {
            subscriberView = sub;
        } else {
            subscriberTreeItem = sub;
        }
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(subscriberView.equals(sub))
        {
            subscriberView = null;
        }
        if(subscriberTreeItem.equals(sub))
        {
            subscriberTreeItem = null;
        }
    }

    @Override
    public void notifySubscribers(Object notification) {
        if(notification == null || this.subscriberView == null || this.subscriberTreeItem == null)
        {
            return;
        }
        subscriberTreeItem.update(notification);
        subscriberView.update(notification);
    }

    @Override
    public void setName(String name) {
        super.setName(name);
        if (this.getParent() != null) {
            ((Package)this.getParent()).notifySubscribers(this.getParent());
        }
    }

    @Override
    public void removeChild(ClassyNode child) {
        super.removeChild(child);
        notifySubscribers(this);
    }

    public DiagramView giveDiagramView()
    {
        if (subscriberView != null && subscriberView instanceof DiagramView) {
            return (DiagramView) subscriberView;
        }
        return null;
    }
}

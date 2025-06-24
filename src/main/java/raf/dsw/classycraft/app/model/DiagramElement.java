package raf.dsw.classycraft.app.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import raf.dsw.classycraft.app.repository.composite.ClassyNode;

import java.awt.*;

@Getter
public abstract class DiagramElement extends ClassyNode {
    @JsonIgnore
    private final Color color = Color.BLACK;
    @JsonIgnore
    private final int stroke = 1;
    public DiagramElement(String name, ClassyNode parent) {
        super(name, parent);
    }

    public DiagramElement() {

    }

    public Point intersect(Point a1, Point b1, Point a2, Point b2) {
        double a = a1.x, b = a1.y, c = b1.x, d = b1.y, p = a2.x, q = a2.y, r = b2.x, s = b2.y;
        double det = (c - a) * (s - q) - (r - p) * (d - b);
        if (det == 0) {
            return new Point(0, 0);
        }
        return new Point((int) (((a * d - b * c) * (p - r) - (a - c) * (p * s - q * r)) / det),
                (int) (((a * d - b * c) * (q - s) - (b - d) * (p * s - q * r)) / det));
    }
    public abstract boolean contains(Point e);
}

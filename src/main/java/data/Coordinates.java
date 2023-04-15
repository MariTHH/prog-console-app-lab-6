package data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * A class with person coordinates
 */
@XmlType(propOrder = {"x", "y"})
public class Coordinates {
    private long x;
    private int y;

    public Coordinates(long x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
    }

    public long getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @XmlElement
    public void setX(long x) {
        this.x = x;
    }

    @XmlElement
    public void setY(int y) {
        this.y = y;
    }

}
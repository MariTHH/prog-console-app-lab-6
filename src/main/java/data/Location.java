package data;

import javax.xml.bind.annotation.*;

/**
 * A class with person location
 */
@XmlRootElement(name = "Location")
@XmlAccessorType(XmlAccessType.FIELD)
public class Location {
    private Double x;
    private float y;
    private String name;

    public Location(Double x, float y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public Location() {
    }

    public Double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String getLocationName() {
        return name;
    }

    public void setLocationName(String name) {
        this.name = name;
    }

}
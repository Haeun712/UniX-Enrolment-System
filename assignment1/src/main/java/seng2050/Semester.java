package seng2050;

import java.io.Serializable;

public class Semester implements Serializable {
    private String name;
    private Boolean isOpen;

    public Semester() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(Boolean isOpen) {
        this.isOpen = isOpen;
    }
}

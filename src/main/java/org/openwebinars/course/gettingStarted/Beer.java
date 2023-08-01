package org.openwebinars.course.gettingStarted;

public class Beer {
    private String name;
    private int capacity;

    public Beer(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }

    public Beer() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}

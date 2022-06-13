package main.com.train.application.model;

public class City {
    protected String name;
    public boolean visited;

    public City(String name) {
        this.name = name;
        this.visited = false;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

    public boolean getVisited() {
        return this.visited;
    }


    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj.getClass() != getClass() || obj == null) {
            return false;
        }

        City rhsTown = (City) obj;
        return this.name.equals(rhsTown.name);
    }

    @Override
    public int hashCode() {
        if (this.name == null) return 0;
        return this.name.hashCode();
    }

    @Override
    public String toString() {
        return this.name.toString();
    }
}

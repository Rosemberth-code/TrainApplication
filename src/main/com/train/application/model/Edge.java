package main.com.train.application.model;

public class Edge {

    public City origin;
    public City destination;
    public int weight;
    public Edge nextEdge;

    public Edge(City origin, City destination, int weight) {
        this.origin = origin;
        this.destination = destination;
        this.weight = weight;
        this.nextEdge = null;
    }

    public Edge(City origin, City destination) {
        this(origin, destination, Integer.MAX_VALUE);
    }

    public Edge next(Edge edge) {
        this.nextEdge = edge;
        return this;
    }

    public City getOrigin() {
        return origin;
    }

    public City getDestination() {
        return destination;
    }

    public int getWeight() {
        return weight;
    }

    public Edge getNextEdge() {
        return nextEdge;
    }

    public String toString() {
        return origin + " to " + destination;
    }
}

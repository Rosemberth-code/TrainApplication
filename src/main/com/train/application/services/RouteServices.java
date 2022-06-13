package main.com.train.application.services;

import main.com.train.application.model.City;
import main.com.train.application.model.Edge;

import java.util.ArrayList;
import java.util.Hashtable;

public class RouteServices {
    public Hashtable<City, Edge> routingTable;

    public RouteServices() {
        this.routingTable = new Hashtable<>();

    }

    public int getDistanceBetweenCities(ArrayList<City> cities) throws NoSuchRouteException {

        int MAXIMUM_ROUTES = 2;
        if (cities.size() < MAXIMUM_ROUTES) {
            return 0;
        }

        int distance, depth, index;
        distance = depth = index = 0;

        while (index < cities.size() - 1) {
            if (this.routingTable.containsKey(cities.get(index))) {
                Edge route = this.routingTable.get(cities.get(index));
                while (route != null) {
                    if (route.destination.equals(cities.get(index + 1))) {
                        distance += route.weight;
                        depth++;
                        break;
                    }
                    route = route.nextEdge;
                }
            } else {
                throw new NoSuchRouteException();
            }
            index++;
        }

        if (depth != cities.size() - 1) {
            throw new NoSuchRouteException();
        }
        return distance;
    }

    public int getNumberStopsBetweenCities(City origin, City destination, int limit) {
        return findRoutes(origin, destination, 0, limit);
    }

    private int findRoutes(City origin, City dest, int depth, int limit) {
        int routes = 0;
        if (this.routingTable.containsKey(origin) && this.routingTable.containsKey(dest)) {
            if (depth == limit) {
                return 0;
            }
            depth++;
            origin.setVisited(true);

            Edge edge = this.routingTable.get(origin);
            while (edge != null) {
                if (edge.getDestination().equals(dest)) {
                    routes++;
                    edge = edge.getNextEdge();
                    continue;
                } else if (!edge.getDestination().getVisited()) {
                    depth--;
                    routes += findRoutes(edge.getDestination(), dest, depth, limit);

                }
                edge = edge.getNextEdge();
            }
        } else {
            noRouteException();
        }

        origin.setVisited(false);
        return routes;
    }

    public int shortestRouteBetweenTowns(City origin, City destination) {
        return findShortestRoute(origin, destination, 0, 0);
    }

    public int findShortestRoute(City origin, City dest, int distance, int shortestPath) {
        if (this.routingTable.containsKey(origin) && this.routingTable.containsKey(dest)) {
            origin.setVisited(true);
            Edge edge = this.routingTable.get(origin);
            while (edge != null) {
                if (edge.getDestination() == dest || !edge.getDestination().getVisited()) {
                    distance += edge.getWeight();
                }

                if (edge.getDestination().equals(dest)) {
                    if (shortestPath == 0 || distance < shortestPath)
                        shortestPath = distance;
                    origin.setVisited(false);
                    return shortestPath;
                }
                /*Some backtracking and recursion */
                else if (!edge.getDestination().getVisited()) {
                    shortestPath = findShortestRoute(edge.getDestination(), dest, distance, shortestPath);
                    distance -= edge.getWeight();
                }
                edge = edge.getNextEdge();
            }
        } else {
            noRouteException();
        }

        origin.setVisited(false);
        return shortestPath;
    }

    /*
     * Find number of routes between towns;
     */
    public int numRoutesWithin(City origin, City dest, int maxDistance) {
        return findAllRoutesBetweenTowns(origin, dest, 0, maxDistance);
    }

    private int findAllRoutesBetweenTowns(City origin, City destination, int weight, int maxDistance) {
        int routes = 0;
        if (this.routingTable.containsKey(origin) && this.routingTable.containsKey(destination)) {

            Edge edge = this.routingTable.get(origin);
            while (edge != null) {
                weight += edge.getWeight();
                if (weight <= maxDistance) {
                    if (edge.getDestination().equals(destination)) {
                        routes++;
                        routes += findAllRoutesBetweenTowns(edge.getDestination(), destination, weight, maxDistance);
                        edge = edge.getNextEdge();
                        continue;
                    } else {
                        routes += findAllRoutesBetweenTowns(edge.getDestination(), destination, weight, maxDistance);
                        weight -= edge.getWeight();
                    }
                } else
                    weight -= edge.getWeight();

                edge = edge.getNextEdge();
            }
        } else {
            noRouteException();

        }
        return routes;

    }


    public void noRouteException() {
        System.out.println("NO SUCH ROUTE");

    }

    public String calculateTotalTimeForRoute(ArrayList<City> towns) {

        int totalTime;
        int distance;

        try {
            distance = getDistanceBetweenCities(towns);
            totalTime = distance + 2 * (towns.size() - 2);

        } catch (Exception e) {
            return e.getMessage();
        }

        return String.valueOf(totalTime);
    }

    public class NoSuchRouteException extends Exception {

        @Override
        public String getMessage() {
            return "NO SUCH ROUTE";
        }
    }
}

package main.com.train.application;

import main.com.train.application.enums.CitiesEnum;
import main.com.train.application.model.City;
import main.com.train.application.model.Edge;
import main.com.train.application.services.RouteServices;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        RouteServices graph = new RouteServices();

        City a = new City(CitiesEnum.CITY_A.name());
        City b = new City(CitiesEnum.CITY_B.name());
        City c = new City(CitiesEnum.CITY_C.name());
        City d = new City(CitiesEnum.CITY_D.name());
        City e = new City(CitiesEnum.CITY_E.name());


        graph.routingTable.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        graph.routingTable.put(b, new Edge(b, c, 4));
        graph.routingTable.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        graph.routingTable.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        graph.routingTable.put(e, new Edge(e, b, 3));

        //1. Distance of route A-B-C
        ArrayList<City> routes1 = new ArrayList<>();
        routes1.add(a);
        routes1.add(b);
        routes1.add(c);

        //2. Distance of route A-D
        ArrayList<City> routes2 = new ArrayList<>();
        routes2.add(a);
        routes2.add(d);

        //3. Distance of route A-D-C
        ArrayList<City> routes3 = new ArrayList<>();
        routes3.add(a);
        routes3.add(d);
        routes3.add(c);

        //4. Distance of route A-E-B-C-D
        ArrayList<City> routes4 = new ArrayList<>();
        routes4.add(a);
        routes4.add(e);
        routes4.add(b);
        routes4.add(c);
        routes4.add(d);

        //5. Distance of route A-E-D
        ArrayList<City> routes5 = new ArrayList<>();
        routes5.add(a);
        routes5.add(e);
        routes5.add(d);

        try {
            System.out.println("Output#1  " + graph.getDistanceBetweenCities(routes1));
            System.out.println("Output#2  " + graph.getDistanceBetweenCities(routes2));
            System.out.println("Output#3  " + graph.getDistanceBetweenCities(routes3));
            System.out.println("Output#4  " + graph.getDistanceBetweenCities(routes4));
            System.out.println("Output#5  " + graph.calculateTotalTimeForRoute(routes5));
            System.out.println("Output#6  " + graph.getNumberStopsBetweenCities(c, c, 3));
            System.out.println("Output#7  " + graph.getNumberStopsBetweenCities(a, c, 4));
            System.out.println("Output#8  " + graph.shortestRouteBetweenTowns(a, c));
            System.out.println("Output#9  " + graph.shortestRouteBetweenTowns(b, b));
            System.out.println("Output#10  " + graph.numRoutesWithin(c, c, 30));

        } catch (Throwable ex) {
            throw new RuntimeException(ex.getMessage());
        }
    }
}

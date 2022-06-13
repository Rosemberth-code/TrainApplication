package test.unit_test.com.train;

import main.com.train.application.enums.CitiesEnum;
import main.com.train.application.model.City;
import main.com.train.application.model.Edge;
import main.com.train.application.services.RouteServices;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RouteServicesTest {

    static RouteServices routeServices = new RouteServices();
    static City a = new City(CitiesEnum.CITY_A.name());
    static City b = new City(CitiesEnum.CITY_B.name());
    static City c = new City(CitiesEnum.CITY_C.name());
    static City d = new City(CitiesEnum.CITY_D.name());
    static City e = new City(CitiesEnum.CITY_E.name());

    @BeforeAll
    public static void init() {
        routeServices.routingTable.put(a, new Edge(a, b, 5).next(new Edge(a, d, 5).next(new Edge(a, e, 7))));
        routeServices.routingTable.put(b, new Edge(b, c, 4));
        routeServices.routingTable.put(c, new Edge(c, d, 8).next(new Edge(c, e, 2)));
        routeServices.routingTable.put(d, new Edge(d, c, 8).next(new Edge(d, e, 6)));
        routeServices.routingTable.put(e, new Edge(e, b, 3));
    }


    @Test
    @DisplayName("Validation for getDistanceBetweenCitiesMethod should be 0")
    void ZeroDistanceBetweenCities() throws RouteServices.NoSuchRouteException {
        ArrayList<City> routes = new ArrayList<>();
        routes.add(a);
        assertEquals(0, routeServices.getDistanceBetweenCities(routes), "Distance between Cities is 0");
    }

    @Test
    @DisplayName("Validation for DistanceBetweenCitiesABC should be 9")
    void DistanceBetweenCitiesABC() throws RouteServices.NoSuchRouteException {
        ArrayList<City> routesABC = new ArrayList<>();
        routesABC.add(a);
        routesABC.add(b);
        routesABC.add(c);
        assertEquals(9, routeServices.getDistanceBetweenCities(routesABC), "Distance between Cities is 9");
    }

    @Test
    @DisplayName("Validation for DistanceBetweenCitiesAD should be 5")
    void DistanceBetweenCitiesAD() throws RouteServices.NoSuchRouteException {
        ArrayList<City> routesAD = new ArrayList<>();
        routesAD.add(a);
        routesAD.add(d);
        assertEquals(5, routeServices.getDistanceBetweenCities(routesAD), "Distance between Cities is 5");
    }

    @Test
    @DisplayName("Validation for DistanceBetweenCitiesAD should be 13")
    void DistanceBetweenCitiesADC() throws RouteServices.NoSuchRouteException {
        ArrayList<City> routesADC = new ArrayList<>();
        routesADC.add(a);
        routesADC.add(d);
        routesADC.add(c);
        assertEquals(13, routeServices.getDistanceBetweenCities(routesADC), "Distance between Cities is 13");
    }

    @Test
    @DisplayName("Validation for DistanceBetweenCitiesAD should be 13")
    void ThreeStopsMaximum() {
        assertEquals(2, routeServices.getNumberStopsBetweenCities(c, c, 3), " Number of trips starting at C,ending at C with 3 stops max");
    }

    @Test
    @DisplayName("Validation for DistanceBetweenCitiesAD should be 13")
    void FourStopsMaximum() {
        assertEquals(3, routeServices.getNumberStopsBetweenCities(a, c, 4), "Number of trips starting at A,ending at C with 4 stops max");
    }

    @Test
    @DisplayName("Validation for shortestRouteBetweenTowns should be 9")
    void ValidationShortestRouteBetweenACCities() {
        assertEquals(9, routeServices.shortestRouteBetweenTowns(a, c), "The length of the shortest route from A to C.");
    }

    @Test
    @DisplayName("Validation for shortestRouteBetweenTowns should be 9")
    void ValidationShortestRouteBetweenBBCities() {
        assertEquals(9, routeServices.shortestRouteBetweenTowns(b, b), "The length of the shortest route from B to B.");
    }

    @Test
    @DisplayName("Validation for numRoutesWithin should be 7")
    void ValidationRoutesWithin() {
        assertEquals(7, routeServices.numRoutesWithin(c, c, 30), "The number of different routes from C to C with a distance of less than 30");
    }

    @Test
    @DisplayName("Validation for getDistanceBetweenCitiesMethod should see an exception")
    void exceptionTesting() {
        ArrayList<City> routes5 = new ArrayList<>();
        routes5.add(a);
        routes5.add(e);
        routes5.add(d);
        Throwable exception = assertThrows(RouteServices.NoSuchRouteException.class, () -> routeServices.getDistanceBetweenCities(routes5));
        assertEquals("NO SUCH ROUTE", exception.getMessage(), "OK");
    }

    @Test
    @DisplayName("Validation for getDistanceBetweenCitiesMethod should works")
    void getNumberStopsBetweenCitiesValidation() {
        RouteServices graph2 = new RouteServices();
        assertEquals(0, graph2.getNumberStopsBetweenCities(a, b, 3), "Number Stops between Cities is 0");
    }
}

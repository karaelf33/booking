package com.flight.booking.utils;

import com.flight.booking.entity.*;

import java.util.HashMap;
import java.util.Map;

public class MapperUtils {

    private MapperUtils() {

    }


    public static Map<String, String> entityToHashMapMapper(Airport airport) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(airport.getId()));
        resultHashmap.put("name", airport.getName());
        resultHashmap.put("location", airport.getLocation());
        return resultHashmap;
    }

    public static Map<String, String> entityToHashMapMapper(AirlineCompany airlineCompany) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(airlineCompany.getId()));
        resultHashmap.put("name", airlineCompany.getName());
        return resultHashmap;
    }

    public static Map<String, String> entityToHashMapMapper(Route route) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(route.getId()));
        resultHashmap.put("name", route.getName());
        resultHashmap.put("departureDate", route.getDepartureDate().toString());
        resultHashmap.put("landingDate", route.getLandingDate().toString());
        resultHashmap.put("departureAirport", route.getDeparture().getName());
        resultHashmap.put("landingAirport", route.getLanding().getName());
        return resultHashmap;
    }

    public static Map<String, String> entityToHashMapMapper(Flight flight) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(flight.getId()));
        resultHashmap.put("flightCode", flight.getCode());
        resultHashmap.put("currentAttendanceNumber", String.valueOf(flight.getCurrentAttendanceNumber()));
        resultHashmap.put("quota", String.valueOf(flight.getQuota()));
        resultHashmap.put("airlineCompanyName", flight.getAirlineCompany().getName());
        resultHashmap.put("routeName", flight.getRoute().getName());

        return resultHashmap;

    }

    public static Map<String, String> entityToHashMapMapper(Customer customer) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("name",customer.getNameSurname());
        resultHashmap.put("creditCardNumber", customer.getCardNumber());
        return resultHashmap;
    }
    public static Map<String, String> entityToHashMapMapper(Ticket ticket) {

        HashMap<String, String> resultHashmap = new HashMap<>();
        resultHashmap.put("id", String.valueOf(ticket.getId()));
        resultHashmap.put("nameSurName", String.valueOf(ticket.getCustomer().getNameSurname()));
        resultHashmap.put("flightNumber", String.valueOf(ticket.getFlight().getCode()));
        resultHashmap.put("seatNumber", String.valueOf(ticket.getSeatNumber()));
        resultHashmap.put("airlineCompany", ticket.getFlight().getAirlineCompany().getName());
        resultHashmap.put("routeName", ticket.getFlight().getRoute().getName());
        return resultHashmap;
    }


}

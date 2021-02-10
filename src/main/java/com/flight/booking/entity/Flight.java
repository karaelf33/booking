package com.flight.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "FLIGHT")
@Data
public class Flight {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "CODE")
    private String code;

    @ManyToOne
    private AirlineCompany airlineCompany;

    @Column(name = "QUOTA")
    private int quota;

    @Column(name = "CURRENT_ATTENDANCE_NUMBER")
    private int currentAttendanceNumber;

    @OneToMany
    private List<Ticket> tickets;

    @ManyToOne
    private Route route;

    public static Flight buildFlight(String code,
                                     int quota,
                                     AirlineCompany airlineCompany,
                                     Route route) {

        Flight flight = new Flight();
        flight.setCode(code);
        flight.setQuota(quota);
        flight.setCurrentAttendanceNumber(0);
        flight.setAirlineCompany(airlineCompany);
        flight.setRoute(route);
        return flight;
    }
}

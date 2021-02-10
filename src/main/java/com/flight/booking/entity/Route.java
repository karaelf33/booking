package com.flight.booking.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "ROUTE")
@Data
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME",unique = true)
    @NotNull
    private String name;

    @ManyToOne
    private Airport departure;

    @ManyToOne
    private Airport landing;

    @OneToMany(mappedBy = "route")
    private List<Flight> flights;

    private Date departureDate;

    private Date landingDate;

    public static Route buildRoute(Date departureDate,
                                   Date landingDate,
                                   Airport departure,
                                   Airport landing,
                                   String name) {

        Route route = new Route();
        route.setDeparture(departure);
        route.setLanding(landing);
        route.setDepartureDate(departureDate);
        route.setLandingDate(landingDate);
        route.setName(name);
        return route;
    }


}

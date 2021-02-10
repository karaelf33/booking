package com.flight.booking.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "AIRPORT")
@Data
public class Airport {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true)
    @NotNull
    private String name;

    @Column(name = "LOCATION")
    @NotNull
    private String location;

    @ManyToMany(mappedBy = "airports")
    private List<AirlineCompany> airlineCompanies;

    @OneToMany(mappedBy = "departure")
    private List<Route> depatures;

    @OneToMany(mappedBy = "landing")
    private List<Route> landings;

    public static Airport buildAirport(String name, String location) {
        Airport airport = new Airport();
        airport.setName(name);
        airport.setLocation(location);
        return airport;
    }


    @Override
    public int hashCode() {
        return Objects.hash(id, name, location);
    }
}

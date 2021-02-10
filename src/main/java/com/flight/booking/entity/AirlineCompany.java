package com.flight.booking.entity;

import com.sun.istack.NotNull;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "AIRLINE_COMPANY")
@Data
public class AirlineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME", unique = true)
    @NotNull
    private String name;

    @OneToMany(mappedBy = "airlineCompany")
    private List<Flight> flights;

    @ManyToMany
    private List<Airport> airports;

    public static AirlineCompany buildAirlineCompany(String name) {
        AirlineCompany airlineCompany = new AirlineCompany();
        airlineCompany.setName(name);
        return airlineCompany;
    }

}

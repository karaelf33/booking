package com.flight.booking.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "TICKET")
@Data
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "SEAT_NUMBER")
    private String seatNumber;

    @Column(name = "PRICE")
    private int price;


    @ManyToOne
    private Flight flight;

    @ManyToOne
    private Customer customer;

    public static Ticket buildTicket(int price,
                                     String seatNumber,
                                     Flight flight) {

        Ticket ticket = new Ticket();
        ticket.setPrice(price);
        ticket.setSeatNumber(seatNumber);
        ticket.setFlight(flight);
        ticket.setCustomer(null);
        return ticket;
    }

}

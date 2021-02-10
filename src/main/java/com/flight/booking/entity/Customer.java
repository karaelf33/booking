package com.flight.booking.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CUSTOMER")
@Data
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME_SURNAME", unique = true)
    private String nameSurname;

    @Column(name = "CARD_NUMBER")
    private String cardNumber;

    @OneToMany(mappedBy = "customer")
    private List<Ticket> tickets;

    public static Customer buildCustomer(String nameSurname,
                                       String cardNumber) {

        Customer customer = new Customer();
        customer.setNameSurname(nameSurname);
        customer.setCardNumber(cardNumber);
        return customer;
    }


}

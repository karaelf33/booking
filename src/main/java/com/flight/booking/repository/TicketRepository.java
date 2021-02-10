package com.flight.booking.repository;

import com.flight.booking.entity.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TicketRepository extends JpaRepository<Ticket,Integer> {

    int countTicketsByCustomerNotNull();

    List<Ticket> findByCustomerNotNull();

    List<Ticket> findAllByCustomerIsNull();

//    int findTopByPrice();

}

package com.flight.booking.repository;

import com.flight.booking.entity.AirlineCompany;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AirlineCompanyRepository extends JpaRepository<AirlineCompany,Integer> {

    Optional<AirlineCompany> findByName(String name);
}

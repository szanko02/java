package com.zanko.lab_12.database;

import com.zanko.lab_12.models.Flight;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FlightRepository {
    String save(Flight flight);

    void update(Flight flight);

    void delete(String id);

    Flight findById(String id);

    List<Flight> findAll();
}

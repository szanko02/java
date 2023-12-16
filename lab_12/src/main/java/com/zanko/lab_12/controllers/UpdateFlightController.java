package com.zanko.lab_12.controllers;

import com.zanko.lab_12.database.FlightRepository;
import com.zanko.lab_12.models.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/flights")
public class UpdateFlightController {

    private final FlightRepository flightRepository;


    public UpdateFlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;

    }

    @PostMapping("/update")
    public String updateFlight(
            @RequestParam("id") String id,
            @RequestParam("start") String start,
            @RequestParam("destination") String destination,
            @RequestParam("airplane") String airplane,
            @RequestParam("seats") int seats
    ) {
        Flight flight = flightRepository.findById(id);
        if (flight != null) {
            flight.setStart(start);
            flight.setDestination(destination);
            flight.setAirplane(airplane);
            flight.setSeats(seats);
            flightRepository.update(flight);
        }
        return "redirect:/flights";
    }

    @PostMapping("/delete")
    public String DeleteFlight(
            @RequestParam("id") String id) {

        Optional<Flight> optionalFlight = Optional.ofNullable(flightRepository.findById(id));
        if (optionalFlight.isPresent()) {
            flightRepository.delete(optionalFlight.get().getId());
        }
        return "redirect:/flights";
    }
}

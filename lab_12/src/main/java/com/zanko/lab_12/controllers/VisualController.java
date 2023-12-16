package com.zanko.lab_12.controllers;

import com.zanko.lab_12.database.FlightRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VisualController {

    private final FlightRepository flightRepository;


    public VisualController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/home")
    public String displayHome() {
        return "home";
    }

    @GetMapping("/flights")
    public String displayAllFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights";
    }

    @GetMapping("/flights/add")
    public String displayAddFlightForm() {
        return "add_flight";
    }

    @GetMapping("/flights/edit")
    public String displayEditFlightsForm(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "edit_flight";
    }

}

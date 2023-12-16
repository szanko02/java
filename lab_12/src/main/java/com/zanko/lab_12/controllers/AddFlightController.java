package com.zanko.lab_12.controllers;

import com.zanko.lab_12.database.FlightRepository;
import com.zanko.lab_12.models.Flight;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/flights")
public class AddFlightController {

    private final FlightRepository flightRepository;

    public AddFlightController(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/flights/add")
    public String showAddFlightForm() {
        return "add-flight"; // Возвращает имя вашего HTML-файла с формой
    }

    @PostMapping
    public String addFlight(
            @RequestParam("start") String start,
            @RequestParam("destination") String destination,
            @RequestParam("airplane") String airplane,
            @RequestParam("seats") int seats
    ) {
        // Создайте новый объект Flight с полученными данными
        Flight flight = new Flight();
        flight.setStart(start);
        flight.setDestination(destination);
        flight.setAirplane(airplane);
        flight.setSeats(seats);

        // Сохраните нового сотрудника в базе данных
        flightRepository.save(flight);

        // Перенаправьте пользователя на страницу со списком сотрудников или на другую страницу, если это необходимо
        return "redirect:/flights"; // Измените путь, если нужно
    }
}

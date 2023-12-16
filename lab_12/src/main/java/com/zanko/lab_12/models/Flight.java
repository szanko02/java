package com.zanko.lab_12.models;

import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class Flight {
    @Id
    private String id;
    private String start;
    private String destination;
    private String airplane;
    private int seats;

    public Flight() {
        this.id = generateShortId();
    }

    public Flight(String start, String destination, String airplane, int seats) {
        this.start = start;
        this.destination = destination;
        this.airplane = airplane;
        this.seats = seats;
    }

    private String generateShortId() {
        String uuid = UUID.randomUUID().toString().replace("-", "");
        return uuid.substring(0, 6);
    }

    // Геттеры и сеттеры для всех полей

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStart() {
        return start;
    }
    public void setStart(String start) {
        this.start = start;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getAirplane() {
        return airplane;
    }

    public void setAirplane(String airplane) {
        this.airplane = airplane;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}

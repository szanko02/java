package com.zanko.lab_12.database.impl;

import com.zanko.lab_12.database.FlightRepository;
import com.zanko.lab_12.models.Flight;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class JdbcFlightRepository implements FlightRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcFlightRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
        createTableIfNotExists(); // Call the method to create the table if it doesn't exist
    }

    // Method to create the "flights" table if it doesn't exist
    private void createTableIfNotExists() {
        jdbcTemplate.execute("CREATE TABLE IF NOT EXISTS flights (" +
                "id VARCHAR(36) PRIMARY KEY," +
                "start VARCHAR(255) NOT NULL," +
                "destination VARCHAR(255) NOT NULL," +
                "airplane VARCHAR(255) NOT NULL," +
                "seats INT NOT NULL" +
                ")");
    }

    public Flight getFlightById(String id) {
        String query = "SELECT id, start, destination, airplane, seats FROM flights WHERE id = ?";
        List<Flight> flights = jdbcTemplate.query(query, (resultSet, rowNum) -> mapFlight(resultSet), id);

        return flights.isEmpty() ? null : flights.get(0);
    }

    public @ResponseBody String save(Flight flight) {
        String checkQuery = "SELECT COUNT(*) FROM flights WHERE start = ? AND destination = ? AND airplane = ? AND seats = ?";
        int count = jdbcTemplate.queryForObject(checkQuery, Integer.class, flight.getStart(), flight.getDestination(), flight.getAirplane(), flight.getSeats());

        if (count > 0) {
            return "Такой рейс уже существует";
        }

        String insertQuery = "INSERT INTO flights (id, start, destination, airplane, seats) VALUES (?, ?, ?, ?, ?)";
        jdbcTemplate.update(insertQuery, flight.getId(), flight.getStart(), flight.getDestination(), flight.getAirplane(), flight.getSeats());

        return "Рейс успешно добавлен";
    }

    public void update(Flight flight) {
        String query = "UPDATE flights SET start = ?, destination = ?, airplane = ?, seats = ? WHERE id = ?";
        jdbcTemplate.update(query, flight.getStart(), flight.getDestination(), flight.getAirplane(), flight.getSeats(), flight.getId());
    }

    public Flight findById(String id) {
        String query = "SELECT * FROM flights WHERE id = ?";
        List<Flight> flights = jdbcTemplate.query(query, (resultSet, rowNum) -> mapFlight(resultSet), id);

        return flights.isEmpty() ? null : flights.get(0);
    }

    public List<Flight> findAll() {
        String query = "SELECT * FROM flights";
        return jdbcTemplate.query(query, (resultSet, rowNum) -> mapFlight(resultSet));
    }

    private Flight mapFlight(ResultSet resultSet) throws SQLException {
        Flight flight = new Flight();
        flight.setId(resultSet.getString("id"));
        flight.setStart(resultSet.getString("start"));
        flight.setDestination(resultSet.getString("destination"));
        flight.setAirplane(resultSet.getString("airplane"));
        flight.setSeats(resultSet.getInt("seats"));
        return flight;
    }

    @Override
    public void delete(String id) {
        String query = "DELETE FROM flights WHERE id = ?";
        jdbcTemplate.update(query, id);
    }

}

package business;

import data.FilePDF;
import domain.Airline;
import domain.Airplane;
import domain.AirplaneModel;
import domain.Flight;
import domain.Passenger;
import domain.Ticket;

public class Main {
    public static void main(String[] args) {
        // Crear objetos necesarios
        Ticket ticket = new Ticket(1, "123", 1, "Economico");
        Passenger passenger = new Passenger("123", "John", "Doe", "10/11/1990", "john.doe@example.com", "123456789");
        Airline airline = new Airline("Example Airlines", "United States");
        Airplane airplane = new Airplane("ABC123", "2021", "Example Airlines", "Boeing 737");
        AirplaneModel airplaneModel = new AirplaneModel("Boeing 737", "Boeing", 10, 100, 200);
        Flight flight = new Flight(0, "New York", "2023/06/25", "10:00", "Los Angeles", "2023-06-25", "13:00", null, 0, 0, 0);
        
        // Crear el ticket
        FilePDF filePDF = new FilePDF();
        filePDF.createTicket(ticket, passenger, airline, airplane, airplaneModel, flight);
    }
}
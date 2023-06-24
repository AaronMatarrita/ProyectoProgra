package data;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import domain.Airline;
import domain.Airplane;
import domain.AirplaneModel;
import domain.Flight;
import domain.Passenger;
import domain.Ticket;

public class FilePDF {
	private static final Font TITLE_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 14);
	private static final Font SECTION_FONT = FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12);

	public void createTicket(Ticket ticket, Passenger passenger, Airline airline, Airplane airplane,
			AirplaneModel airplaneModel, Flight flight) {
		try {
			Document document = new Document();
			@SuppressWarnings("unused")
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("Ticket.pdf"));
			document.open();

			addTitle(document, "Información del Tiquete");
			addSectionTitle(document, "Número de tiquete");
			addContent(document, String.valueOf(ticket.getTicketNumber()));

			addSectionTitle(document, "Información del Pasajero");
			addContent(document, "Nombre: " + passenger.getName());
			addContent(document, "Apellidos: " + passenger.getLastname());
			addContent(document, "Fecha de nacimiento: " + passenger.getDateofbirth());
			addContent(document, "Email: " + passenger.getEmail());
			addContent(document, "Número de teléfono: " + passenger.getPhoneNumber());
			addContent(document, "Fecha y hora de compra del tiquete: " + getCurrentDateTime());

			addSectionTitle(document, "Información de la Aerolínea");
			addContent(document, "Nombre: " + airline.getName());
			addContent(document, "Centro de operaciones: " + airline.getCountry());

			addSectionTitle(document, "Información del Avión");
			addContent(document, "Matrícula: " + airplane.getId());
			addContent(document, "Año: " + airplane.getYear());
			addContent(document, "Aerolínea: " + airplane.getAirline());
			addContent(document, "Modelo: " + airplane.getAirplaneModel());

			addSectionTitle(document, "Información del modelo de avión");
			addContent(document, "Modelo: " + airplaneModel.getName());
			addContent(document, "Marca: " + airplaneModel.getBrand());
			addContent(document, "C. Asientos ejecutivos: " + airplaneModel.getBusinessClassSeats());
			addContent(document, "C. Asientos turistas: " + airplaneModel.getTouristClassSeats());
			addContent(document, "C. Asientos económicos: " + airplaneModel.getEconomyClassSeats());

			addSectionTitle(document, "Información del vuelo");
			addContent(document, "Lugar de salida: " + flight.getDepartureCity());
			addContent(document, "Fecha de salida: " + flight.getDepartureDate());
			addContent(document, "Hora de salida: " + flight.getDepartureTime());
			addContent(document, "Lugar de llegada: " + flight.getArrivalCity());
			addContent(document, "Fecha de llegada: " + flight.getArrivalDate());
			addContent(document, "Hora de llegada: " + flight.getArrivalTime());

			document.close();
			System.out.println("El archivo PDF ha sido creado correctamente.");
		} catch (DocumentException | FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	
	private void addTitle(Document document, String title) throws DocumentException {
		Paragraph paragraph = new Paragraph(title, TITLE_FONT);
		paragraph.setAlignment(Element.ALIGN_CENTER);
		paragraph.setSpacingAfter(10);
		document.add(paragraph);
	}

	private void addSectionTitle(Document document, String sectionTitle) throws DocumentException {
		Paragraph paragraph = new Paragraph(sectionTitle, SECTION_FONT);
		paragraph.setSpacingBefore(5);
		paragraph.setSpacingAfter(5);
		document.add(paragraph);
	}

	private void addContent(Document document, String content) throws DocumentException {
		Paragraph paragraph = new Paragraph(content);
		paragraph.setSpacingAfter(2);
		document.add(paragraph);
	}

	private String getCurrentDateTime() {
		LocalDateTime currentDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return currentDateTime.format(formatter);
	}
}
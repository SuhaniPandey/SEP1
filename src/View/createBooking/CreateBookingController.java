package View.createBooking;

import View.AlertBox;
import View.booking.SelectedRoomFromBooking;
import entity.Booking;
import entity.Guest;
import entity.Room;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;
import utils.ViewHandler;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CreateBookingController implements Initializable {

    @FXML
    private TextField firstName, lastName, address, phoneNo, nationality;
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker dateOfBirth, arrivalDate, departureDate;
    private ModelManager modelManager;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
        initializeDates();

    }


    private void initializeDates() {
        arrivalDate.setValue(SelectedRoomFromBooking.getArrivalDate());
        departureDate.setValue(SelectedRoomFromBooking.getDepartureDate());
        arrivalDate.setEditable(false);
        arrivalDate.setDisable(true);
        arrivalDate.setOpacity(1);
        departureDate.setOpacity(1);
        departureDate.setDisable(true);
        departureDate.setEditable(false);

    }

    @FXML
    private void onBookPressed() {
        String firstNameText = firstName.getText();
        String lastNameText = lastName.getText();
        String addressText = address.getText();
        String nationalityText = nationality.getText();
        String phoneNumberText = phoneNo.getText();
        LocalDate dateOfBirthText = dateOfBirth.getValue();

        Guest guest = new Guest(firstNameText, lastNameText, phoneNumberText, nationalityText, addressText, dateOfBirthText);
        Room room = SelectedRoomFromBooking.getSelectedRoom();
        Booking booking = new Booking(guest, room);
        booking.setArrival(arrivalDate.getValue());
        booking.setDeparture(departureDate.getValue());
        modelManager.addBooking(booking);
        AlertBox.display("Booking has been added");
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(ViewHandler.getrooms());


    }
}
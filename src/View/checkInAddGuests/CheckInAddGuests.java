package View.checkInAddGuests;

import View.AlertBox;
import entity.Booking;
import entity.Guest;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;
import utils.ViewHandler;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckInAddGuests {

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private TextField firstNameCheckIn, lastNameCheckIn, address, phoneNo, nationality;
    @FXML
    private DatePicker dateOfBirth;

    private ModelManager modelManager;
    private Booking selectedBooking;
    private ArrayList<Guest> addedGuests;


    public void initialize() {
        modelManager = new ModelManager();
        addedGuests = new ArrayList<>();
    }

    @FXML
    private void checkInPressed(ActionEvent actionEvent) {
        modelManager.addGuestsToBooking(selectedBooking, addedGuests);
        modelManager.checkIn(selectedBooking);
        AlertBox.display("Checked in successfully");

    }

    @FXML
    private void addGuestPressed() {
        String firstName = firstNameCheckIn.getText();
        String lastname = lastNameCheckIn.getText();
        String nationalityText = nationality.getText();
        String phone = phoneNo.getText();
        String addressText = address.getText();
        LocalDate date = dateOfBirth.getValue();
        addedGuests.add(new Guest(firstName, lastname, phone, nationalityText, addressText, date));
        clearFields();
        AlertBox.display("Guest : " + firstName + " " + lastname + " has been added to the booking");

    }

    private void clearFields() {
        firstNameCheckIn.clear();
        lastNameCheckIn.clear();
        nationality.clear();
        address.clear();
        phoneNo.clear();
        dateOfBirth.setValue(null);
    }

    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }

    @FXML
    private void onBackPressed() {
        clearFields();
        addedGuests = null;
        anchorPane.getChildren().setAll(ViewHandler.getbooking());

    }
}

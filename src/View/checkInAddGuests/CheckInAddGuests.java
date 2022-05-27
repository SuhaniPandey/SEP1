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


    /**
     * > This function initializes the modelManager and addedGuests variables
     */
    public void initialize() {
        modelManager = new ModelManager();
        addedGuests = new ArrayList<>();
    }

    /**
     * When the check in button is pressed, add the guests to the booking, then
     * check in the booking
     */
    @FXML
    private void checkInPressed( ) {
        modelManager.addGuestsToBooking(selectedBooking, addedGuests);

        modelManager.checkIn(selectedBooking);
        AlertBox.display("Checked in successfully");

    }

    /**
     * The function adds a guest to the booking, and clears the fields
     */
    @FXML
    private void addGuestPressed() {
        String firstName = firstNameCheckIn.getText().trim();
        String lastname = lastNameCheckIn.getText().trim();
        String nationalityText = nationality.getText().trim();
        String phone = phoneNo.getText().trim();
        String addressText = address.getText().trim();
        LocalDate date = dateOfBirth.getValue();
        addedGuests.add(new Guest(firstName, lastname, phone, nationalityText, addressText, date));
        clearFields();
        AlertBox.display("Guest : " + firstName + " " + lastname + " has been added to the booking");

    }

    /**
     * It clears all the fields in the check-in tab
     */
    private void clearFields() {
        firstNameCheckIn.clear();
        lastNameCheckIn.clear();
        nationality.clear();
        address.clear();
        phoneNo.clear();
        dateOfBirth.setValue(null);
    }

    /**
     * The function setSelectedBooking() is a public function that takes a Booking
     * object as a parameter and sets the selectedBooking variable to the Booking
     * object passed in
     *
     * @param selectedBooking The selected booking object.
     */
    public void setSelectedBooking(Booking selectedBooking) {
        this.selectedBooking = selectedBooking;
    }

    /**
     * It clears the fields and sets the anchorPane to the booking view
     */
    @FXML
    private void onBackPressed() {
        clearFields();
        addedGuests = null;
        anchorPane.getChildren().setAll(ViewHandler.getbooking());

    }
}

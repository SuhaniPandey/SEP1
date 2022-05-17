package View.booking;

import View.AlertBox;
import entity.Booking;
import entity.BookingList;
import entity.Guest;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;
import utils.ViewHandler;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField firstName, lastName, address, phoneNo, nationality;
    @FXML
    private DatePicker dateOfBirth, arrivalDate, departureDate;

    // cancel
    @FXML
    private TextField firstNameCancel, lastNameCancel, phoneNoCancel;


    // All bookings fields......
    @FXML
    private TableView<Booking> allBookingsTableView;
    @FXML
    private TableColumn<Booking, String> bookerAll;
    @FXML
    private TableColumn<Booking, Integer> roomNumberAll;
    @FXML
    private TableColumn<Booking, LocalDate> checkInAll, checkoutAll;
    @FXML
    private TableColumn<Booking, Boolean> isCheckedInAll;
    @FXML
    private DatePicker dateToFilter, dateFromFilter;

    private ObservableList<Booking> allBookingsToShow;


    private Room selectedRoom;
    private ModelManager modelManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
        initializeDates();
        allBookingsToShow = FXCollections.observableArrayList();
        initializeAllBookingTable();
    }

    private void initializeAllBookingTable() {
        bookerAll.setCellValueFactory(
                new PropertyValueFactory<>("bookerName"));
        roomNumberAll.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));
        checkInAll.setCellValueFactory(
                new PropertyValueFactory<>("arrival"));
        checkoutAll.setCellValueFactory(
                new PropertyValueFactory<>("departure"));
        isCheckedInAll.setCellValueFactory(
                new PropertyValueFactory<>("isCheckedIn"));

        fetchAllBookings();
        allBookingsTableView.setItems(allBookingsToShow);

    }


    private void fetchAllBookings() {
        BookingList bookingList = modelManager.getAllBookings();
        allBookingsToShow.setAll(bookingList.getBookings());
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

    @FXML
    private void onCancelPressed() {
        String firstNameCancelText = firstNameCancel.getText();
        String lastnameCancelText = lastNameCancel.getText();
        String phoneNumberCancelText = phoneNoCancel.getText();
        modelManager.deleteBooking(firstNameCancelText, lastnameCancelText, phoneNumberCancelText);
        firstNameCancel.clear();
        lastNameCancel.clear();
        AlertBox.display("Booking removed succesfully");

    }

    @FXML
    private void onFilterPressed() {
        LocalDate dateFrom = dateFromFilter.getValue();
        LocalDate dateTo = dateToFilter.getValue();

        if (dateFrom==null || dateTo == null){
            AlertBox.display("Both dates filters must be set");
            return;
        }
        if (dateTo.isBefore(dateFrom)){
            AlertBox.display("Date to cannot be before dateFrom");
            return;
        }
        ArrayList<Booking> filteredBooking = modelManager.filterBookings(dateFrom, dateTo);
        allBookingsToShow.setAll(filteredBooking);
    }

    @FXML
    private void resetFilterPressed( ) {
        dateFromFilter.getEditor().clear();
        dateToFilter.getEditor().clear();
        fetchAllBookings();

    }
}



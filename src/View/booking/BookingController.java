package View.booking;

import View.AlertBox;
import entity.Booking;
import entity.BookingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class BookingController implements Initializable {
    @FXML
    private AnchorPane anchorPane;


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


    private ModelManager modelManager;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
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


    @FXML
    private void onFilterPressed() {
        LocalDate dateFrom = dateFromFilter.getValue();
        LocalDate dateTo = dateToFilter.getValue();

        if (dateFrom == null || dateTo == null) {
            AlertBox.display("Both dates filters must be set");
            return;
        }
        if (dateTo.isBefore(dateFrom)) {
            AlertBox.display("Date to cannot be before dateFrom");
            return;
        }
        ArrayList<Booking> filteredBooking = modelManager.filterBookings(dateFrom, dateTo);
        allBookingsToShow.setAll(filteredBooking);
    }

    @FXML
    private void resetFilterPressed() {
        dateFromFilter.getEditor().clear();
        dateToFilter.getEditor().clear();
        fetchAllBookings();

    }

    @FXML
    private void onRemovePressed() {

        Booking booking = allBookingsTableView.getSelectionModel().getSelectedItem();
        if (booking == null) {
            AlertBox.display("Select a booking to remove");
            return;
        }

        boolean isRemoved = modelManager.deleteBooking(booking);
        if (isRemoved) allBookingsToShow.remove(booking);

    }



  public void onCheckedInPressed()
  {
      Booking booking=allBookingsTableView.getSelectionModel().getSelectedItem();
      modelManager.checkIn(booking);
  }
}



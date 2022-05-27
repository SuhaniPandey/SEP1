package View.booking;

import View.AlertBox;
import View.checkInAddGuests.CheckInAddGuests;
import entity.Booking;
import entity.BookingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;

import java.io.IOException;
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


    /**
     * The initialize function is called when the FXML file is loaded. It creates a
     * new ModelManager object and an ObservableList of Booking objects. It then
     * calls the initializeAllBookingTable function
     *
     * @param url The location used to resolve relative paths for the root object,
     * or null if the location is not known.
     * @param resourceBundle This is a resource bundle that is used to localize the
     * root object.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
        allBookingsToShow = FXCollections.observableArrayList();
        initializeAllBookingTable();
    }


    /**
     * value is set for every column in the table
     * It fetches all the bookings from the database and populates the table with
     * the data
     */
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


    /**
     * The function fetches all bookings from the model manager and sets the list
     * of bookings to show to the list of bookings fetched from the model manager.
     */
    private void fetchAllBookings() {
        BookingList bookingList = modelManager.getAllBookings();
        allBookingsToShow.setAll(bookingList.getBookings());
    }


    /**
     * If the dateFrom and dateTo filters are set, then filter the bookings and
     * display them in the table
     * if the dateTo and DateFrom are null then the alert box will be displayed.
     */
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

    /**
     * It clears the date fields and fetches all bookings
     */
    @FXML
    private void resetFilterPressed() {
        dateFromFilter.getEditor().clear();
        dateToFilter.getEditor().clear();
        fetchAllBookings();

    }

    /**
     * The function is called when the user presses the remove button. It checks if
     * the user has selected a booking to remove, if not it displays an error
     * message. If the user has selected a booking, it removes the booking from the
     * system and updates the table view
     */
    @FXML
    private void onRemovePressed() {

        Booking booking = allBookingsTableView.getSelectionModel().getSelectedItem();
        if (booking == null) {
            AlertBox.display("Select a booking to remove");
            return;
        }

        boolean isRemoved = modelManager.deleteBooking(booking);
        if (isRemoved){
            allBookingsToShow.remove(booking);
            AlertBox.display("Bookings have been deleted from the system permanently..");
        }

    }

    /**
     *  A function that is called when the user presses the check-in button. It
     *     checks if the user has selected a booking to check-in, if not it displays an
     *     error message. If the user has selected a booking, it checks if the booking
     *     is already checked in, if not it loads the check-in add guests screen and
     *      passes the selected booking to the check-in add guests screen.
     * @throws IOException
     */
    @FXML
    private void onCheckedInPressed() throws IOException {

        Booking selectedItem = allBookingsTableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Select a booking to check-in");
            return;
        }
        if (selectedItem.getIsCheckedIn()){
            AlertBox.display("Selected booking is already checked in");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../View/checkInAddGuests/CheckInAddguests.fxml"));
        Parent root = loader.load();
        CheckInAddGuests controller = loader.getController();
        controller.setSelectedBooking(selectedItem);
        controller.initialize();

        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(root);
    }
}



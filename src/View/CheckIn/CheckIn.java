package View.CheckIn;

import View.AlertBox;
import View.CheckIn.checkedInDetails.CheckedInDetails;
import View.checkInAddGuests.CheckInAddGuests;
import View.checkOut.CheckOut;
import entity.Booking;
import entity.BookingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckIn implements Initializable {


    @FXML
    private AnchorPane anchorPame;


    @FXML
    private TableView<Booking> tableView;
    @FXML
    private TableColumn<Booking, Integer> roomNumberColumn;
    @FXML
    private TableColumn<Booking, LocalDate> arrivalDateColumn;
    @FXML
    private TableColumn<Booking, LocalDate> departureDateColumn;
    @FXML
    private TableColumn<Booking, String> bookedBy;
    @FXML
    private TextField firstNameFilter,lastNameFilter,phoneNumberFilter;


    // Next tab


    private ObservableList<Booking> bookingsToDisplay;

    private ModelManager modelManager;


    /**
     * The initialize function is called when the FXML file is loaded. It creates a
     * new ModelManager object, which is used to get all the checked in bookings
     * from the database. The bookings are then added to an observable list, which
     * is used to populate the table
     *
     * @param url The location used to resolve relative paths for the root object,
     * or null if the location is not known.
     * @param resourceBundle This is a ResourceBundle object that contains the
     * resources for the controller.
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
        ArrayList<Booking> allCheckedIns = modelManager.getAllCheckedInbookings();
        bookingsToDisplay = FXCollections.observableArrayList(allCheckedIns);
        initializeTable();

    }


    /**
     * The function initializes the table by setting the cell value factory for
     * each column to the corresponding property in the Booking class
     */
    private void initializeTable() {
        roomNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));
        arrivalDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("arrival"));
        departureDateColumn.setCellValueFactory(
                new PropertyValueFactory<>("departure"));
        bookedBy.setCellValueFactory(new PropertyValueFactory<>("bookerName"));
        tableView.setItems(bookingsToDisplay);

    }


    /**
     * It takes the selected booking from the tableview, and then passes the
     * booking's check in and check out dates to the controller of the details
     * view, which then uses those dates to populate the tableview in the details
     * view
     */
    @FXML
    private void detailsPressed() throws IOException {
        Booking booking = tableView.getSelectionModel().getSelectedItem();
        if (booking == null) {
            AlertBox.display("Select a booking to see details");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("checkedInDetails/CheckedInDetails.fxml"));
        Parent root
                = loader.load();
        CheckedInDetails controller = loader.getController();
        controller.setCheckIn(booking.getArrival());
        controller.setCheckOut(booking.getDeparture());
        controller.initialize();

       controller.setGuestsToShow(FXCollections.observableArrayList(booking.getGuests()));
        controller.setBookerName(booking.getBookerName());



        // Switch view here..
        anchorPame.getChildren().clear();
        anchorPame.getChildren().setAll(root);
    }


    /**
     * It clears the text fields and then sets the list of bookings to display to
     * all the checked in bookings
     */
    @FXML
    private void onResetFilterPressed()
    {
        firstNameFilter.clear();
        lastNameFilter.clear();
        phoneNumberFilter.clear();
        ArrayList<Booking> bookings=modelManager.getAllCheckedInbookings();
        bookingsToDisplay.setAll(bookings);
    }

    /**
     * The function takes the text from the text fields and passes it to the model
     * manager, which returns an array list of bookings that match the filter. The
     * array list is then set as the items to display in the table view
     */
    @FXML
    private void onFilterPressed()
    {
  String firstName=firstNameFilter.getText();
  String lastName=lastNameFilter.getText();
  String phoneNo=phoneNumberFilter.getText();
  ArrayList<Booking> filterCheckIn=modelManager.filterCheckIn(firstName,lastName,phoneNo);
  bookingsToDisplay.setAll(filterCheckIn);

    }

    /**
     * When the check-out button is pressed, if a booking is selected, the
     * check-out screen is loaded and the selected booking is passed to the
     * check-out screen
     */
    @FXML
    private void checkOutPressed() throws IOException {
        Booking selectedItem = tableView.getSelectionModel().getSelectedItem();
        if (selectedItem == null) {
            AlertBox.display("Select a booking to check-out");
            return;
        }
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("../../View/checkOut/CheckOut.fxml"));
        Parent root = loader.load();
        CheckOut controller = loader.getController();

        controller.setBookingFromBefore(selectedItem);
        controller.initialize();

        anchorPame.getChildren().clear();
        anchorPame.getChildren().setAll(root);
    }
}

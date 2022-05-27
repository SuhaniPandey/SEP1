package View.checkOut;

import View.AlertBox;
import View.booking.SelectedRoomFromBooking;
import entity.Booking;
import entity.BookingList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;
import utils.ViewHandler;

import javax.swing.text.View;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class CheckOut {

    @FXML
    private AnchorPane anchorPane;
    @FXML
    private TextField firstName, lastName, phoneNumber;
    /*
    @FXML private TableView<Booking> tableView;
    @FXML private TableColumn<Booking, Integer> roomNumberColumn;
    @FXML private TableColumn<Booking,LocalDate> ArrivalDateColumn,departureDateColumn;

     */
    @FXML
    private DatePicker arrivalDate, departureDate;
    @FXML
    private Label nightStayed, smokingFee, discount, finalPrice;
    @FXML
    private Tab checkOutTab;
    @FXML
    private TabPane tabPane;
    private ModelManager modelManager;
    private ObservableList<Booking> checkInToDisplay;

    private Booking bookingFromBefore;
    private Booking selectedBooking;
    double finalCheckOutPrice;

    @FXML
    private CheckBox discountCheckBox, smokingCheckBox;

    //all checked in guest search
    @FXML
    private TableView<Booking> checkInAllTableView;
    @FXML
    private TableColumn<Booking, String> mainBooker;
    @FXML
    private TableColumn<Booking, Integer> roomNumber;
    @FXML
    private TableColumn<Booking, LocalDate> checkedInDate;
    @FXML
    private TableColumn<Booking, LocalDate> checkedOutDate;


    /**
     * It initializes the model manager, the check in to display list, the tabs,
     * the table, and the dates
     */
    public void initialize() {
        modelManager = new ModelManager();
        if (bookingFromBefore == null) {
            checkInToDisplay = FXCollections.observableArrayList();
        } else {
            checkInToDisplay = FXCollections.observableArrayList(bookingFromBefore);
        }
        initializeTabs();
        initializeTable();
        initializeDates();
    }

    /**
     * > The function disables the checkOutTab, but enables the content of the
     * checkOutTab
     */
    private void initializeTabs() {
        checkOutTab.setDisable(true);
        checkOutTab.getContent().setDisable(false);
    }

    /**
     * The function initializes the table view by setting the cell value factory
     * for each column to the corresponding property value factory
     */
    private void initializeTable() {
        mainBooker.setCellValueFactory(
                new PropertyValueFactory<>("bookerName"));

        roomNumber.setCellValueFactory(
                new PropertyValueFactory<>("roomNumber"));
        checkedInDate.setCellValueFactory(
                new PropertyValueFactory<>("arrival"));
        checkedOutDate.setCellValueFactory(
                new PropertyValueFactory<>("departure"));
        checkInAllTableView.setItems(checkInToDisplay);

    }

    /**
     * The function takes the text from the text fields and passes it to the model
     * manager, which then returns a list of bookings that match the search
     * criteria
     */
    @FXML
    private void onSearchPressed() {
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String phoneno = phoneNumber.getText();
       BookingList bookings = modelManager. searchCheckIn(firstname, lastname, phoneno);
        checkInToDisplay.setAll(bookings.getBookings());
    }


    /**
     * If the modelManager.deleteBooking(selectedBooking) returns true, then the
     * booking has been deleted from the system and the user is notified of this.
     * If it returns false, then the user is notified that the booking has not been
     * deleted
     */
    @FXML
    private void onCheckOutPressed() {
        if (modelManager.deleteBooking(selectedBooking)){
            AlertBox.display("Checked out successfully. \n The booking has now been deleted from the system permanently");
            anchorPane.getChildren().clear();
            anchorPane.getChildren().setAll(ViewHandler.getbooking());
            return;
        }
        AlertBox.display("Checkout failed... Something went wrong");
    }

    /**
     * This function sets the departure date to the current date, and sets the
     * arrival date to be uneditable and disabled
     */
    private void initializeDates() {
        departureDate.setValue(LocalDate.now());
        arrivalDate.setEditable(false);
        arrivalDate.setDisable(true);
        arrivalDate.setOpacity(1);
        departureDate.setOpacity(1);
        departureDate.setDisable(true);
        departureDate.setEditable(false);

    }

    /**
     * It gets the selected booking from the table view, checks if it's null, if
     * it's not null, it sets the selected booking to the booking that was
     * selected, selects the next tab, sets the arrival date to the arrival date of
     * the booking, sets the number of nights stayed to the number of nights
     * stayed, sets the final checkout price to the checkout price of the booking,
     * and updates the final price
     */
    public void onNextpressed() {
        Booking booking = checkInAllTableView.getSelectionModel().getSelectedItem();
        if (booking == null) {
            AlertBox.display("Select a booking first");
            return;
        }
        selectedBooking = booking;
        tabPane.getSelectionModel().select(1);
        arrivalDate.setValue(booking.getArrival());
        nightStayed.setText(ChronoUnit.DAYS.between(booking.getArrival(),LocalDate.now())+"");
        finalCheckOutPrice = booking.checkOutPrice();
        updateFinalPrice();
    }

    /**
     * This function updates the final price of the order
     */
    private void updateFinalPrice( ) {
        finalPrice.setText(finalCheckOutPrice + "");
    }

    /**
     * If the smoking checkbox is selected, add 100 to the final checkout price,
     * otherwise subtract 100 from the final checkout price
     */
    @FXML
    private void onSmokingPressed() {
        if (smokingCheckBox.isSelected()) {
            finalCheckOutPrice +=  100;
        }else {
            finalCheckOutPrice -= 100;
        }
        updateFinalPrice();

    }

    /**
     * If the discount checkbox is selected, then subtract 10% of the checkout
     * price from the final checkout price. Otherwise, add 10% of the checkout
     * price to the final checkout price
     */
    public void onDiscountPressed() {
        double discount = 0.1 * selectedBooking.checkOutPrice();
        if (discountCheckBox.isSelected()){
            finalCheckOutPrice -= discount;
        }
        else{
            finalCheckOutPrice += discount;
        }
        updateFinalPrice();
    }


    /**
     * This function sets the bookingFromBefore variable to the bookingFromBefore
     * parameter, and then sets the text of the firstName, lastName, and
     * phoneNumber TextViews to the bookerName, lastName, and phoneNumber of the
     * bookingFromBefore variable
     *
     * @param bookingFromBefore The booking object that is being edited.
     */
    public void setBookingFromBefore(Booking bookingFromBefore) {
        this.bookingFromBefore = bookingFromBefore;
        firstName.setText(bookingFromBefore.getBookerName());
        lastName.setText(bookingFromBefore.getMainBooker().getLastName());
        phoneNumber.setText(bookingFromBefore.getMainBooker().getPhoneNumber());
    }


}

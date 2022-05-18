package View.checkOut;

import View.AlertBox;
import entity.Booking;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.ModelManager;

import java.time.LocalDate;
import java.util.ArrayList;

public class CheckOut {
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

    private void initializeTabs() {
        checkOutTab.setDisable(true);
        checkOutTab.getContent().setDisable(false);
    }

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

    @FXML
    private void onSearchPressed() {
        String firstname = firstName.getText();
        String lastname = lastName.getText();
        String phoneno = phoneNumber.getText();
        ArrayList<Booking> bookings = modelManager.searchCheckIn(firstname, lastname, phoneno);
        checkInToDisplay.setAll(bookings);
    }


    @FXML
    private void onCheckOutPressed() {
    }

    private void initializeDates() {
        departureDate.setValue(LocalDate.now());
        arrivalDate.setEditable(false);
        arrivalDate.setDisable(true);
        arrivalDate.setOpacity(1);
        departureDate.setOpacity(1);
        departureDate.setDisable(true);
        departureDate.setEditable(false);

    }

    public void onNextpressed() {
        Booking booking = checkInAllTableView.getSelectionModel().getSelectedItem();
        if (booking == null) {
            AlertBox.display("Select a booking first");
            return;
        }
        selectedBooking = booking;
        tabPane.getSelectionModel().select(1);
        arrivalDate.setValue(booking.getArrival());
        finalCheckOutPrice = booking.checkOutPrice();
        updateFinalPrice();
    }

    private void updateFinalPrice( ) {
        finalPrice.setText(finalCheckOutPrice + "");
    }

    @FXML
    private void onSmokingPressed() {
        if (smokingCheckBox.isSelected()) {
            finalCheckOutPrice +=  100;
        }else {
            finalCheckOutPrice -= 100;
        }
        updateFinalPrice();

    }

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


    public void setBookingFromBefore(Booking bookingFromBefore) {
        this.bookingFromBefore = bookingFromBefore;
        firstName.setText(bookingFromBefore.getBookerName());
        lastName.setText(bookingFromBefore.getMainBooker().getLastName());
        phoneNumber.setText(bookingFromBefore.getMainBooker().getPhoneNumber());
    }


}

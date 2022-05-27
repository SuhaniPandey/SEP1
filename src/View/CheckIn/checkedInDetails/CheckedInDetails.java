package View.CheckIn.checkedInDetails;

import entity.Guest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ViewHandler;

import java.time.LocalDate;

public class CheckedInDetails {


    @FXML
    private AnchorPane anchorPane;
    @FXML
    private DatePicker checkInDate;

    @FXML
    private DatePicker checkOutDate;


    @FXML
    private Label bookedByName;


    @FXML
    private TableView<Guest> tableView;

    @FXML
    private TableColumn<Guest, String> firstNameColumn;

    @FXML
    private TableColumn<Guest, String> lastNameColumn, addressColumn, nationalityColumn, phoneNumberColumn;

    @FXML
    private TableColumn<Guest, LocalDate> dateOfBirthColumn;


    private ObservableList<Guest> guestsToShow ;


    /**
     * It initializes the table, and the date fields
     */
    public void initialize() {
        guestsToShow = FXCollections.observableArrayList();
        initializeDateFields();
        initializeTable();

    }

    /**
     * This function sets the check in and check out date fields to be uneditable,
     * and disables them
     */
    private void initializeDateFields() {
        checkOutDate.setEditable(false);
        checkInDate.setEditable(false);
        checkInDate.setOpacity(1);
        checkOutDate.setOpacity(1);
        checkOutDate.setDisable(true);
        checkInDate.setDisable(true);
    }

    /**
     * The function initializes the tableView with the data from the database
     */
    private void initializeTable() {

        firstNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("firstName"));
        lastNameColumn.setCellValueFactory(
                new PropertyValueFactory<>("lastName"));
        addressColumn.setCellValueFactory(
                new PropertyValueFactory<>("address"));

        nationalityColumn.setCellValueFactory(
                new PropertyValueFactory<>("nationality"));
        phoneNumberColumn.setCellValueFactory(
                new PropertyValueFactory<>("phoneNumber"));
        dateOfBirthColumn.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
        tableView.setItems(guestsToShow);
        guestsToShow.forEach(System.out::println);
    }


    /**
     * It clears the anchorPane and sets it to the checkIn view.
     */
    @FXML
    private void onBackPressed() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(ViewHandler.getcheckIn());

    }

    /**
     * This function sets the text of the TextView object called bookedByName to
     * the value of the String parameter called bookerName
     *
     * @param bookerName The name of the person who booked the appointment
     */
    public void setBookerName(String bookerName) {
        this.bookedByName.setText(bookerName);
    }

    /**
     * `setCheckOut` sets the value of the `checkOutDate` property to the value of
     * the `checkOut` parameter
     *
     * @param checkOut The date the user wants to check out.
     */
    public void setCheckOut(LocalDate checkOut) {
        this.checkOutDate.setValue(checkOut);
    }

    /**
     * This function sets the value of the checkInDate property to the value of the
     * checkIn parameter.
     *
     * @param checkIn The date the user wants to check in.
     */
    public void setCheckIn(LocalDate checkIn) {
        this.checkInDate.setValue(checkIn);
    }

    /**
     * This function sets the guestsToShow list to the list passed in as a
     * parameter.
     *
     * @param guestsToShow The list of guests to show in the table.
     */
    public void setGuestsToShow(ObservableList<Guest> guestsToShow) {
        this.guestsToShow.setAll(guestsToShow);
    }
}

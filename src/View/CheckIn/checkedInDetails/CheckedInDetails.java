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

    @FXML
    private ObservableList<Guest> guestsToShow = FXCollections.observableArrayList();


    public void initialize() {
        initializeDateFields();
        initializeTable();


    }

    private void initializeDateFields() {
        checkOutDate.setEditable(false);
        checkInDate.setEditable(false);
        checkInDate.setOpacity(1);
        checkOutDate.setOpacity(1);
        checkOutDate.setDisable(true);
        checkInDate.setDisable(true);
    }

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
        dateOfBirthColumn.setCellValueFactory(
                new PropertyValueFactory<>("dateOfBirth"));

        tableView.setItems(FXCollections.observableList(guestsToShow));
    }


    @FXML
    private void onBackPressed() {
        anchorPane.getChildren().clear();
        anchorPane.getChildren().setAll(ViewHandler.getcheckIn());

    }

    public void setBookerName(String bookerName) {
        this.bookedByName.setText(bookerName);
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOutDate.setValue(checkOut);
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkInDate.setValue(checkIn);
    }

    public void setGuestsToShow(ObservableList<Guest> guestsToShow) {

        this.guestsToShow = guestsToShow;
    }
}

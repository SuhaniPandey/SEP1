package View.CheckIn;

import View.AlertBox;
import View.CheckIn.checkedInDetails.CheckedInDetails;
import View.checkInAddGuests.CheckInAddGuests;
import View.checkOut.CheckOut;
import entity.Booking;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        modelManager = new ModelManager();
        ArrayList<Booking> allCheckedIns = modelManager.getAllCheckedInbookings();
        bookingsToDisplay = FXCollections.observableArrayList(allCheckedIns);
        initializeTable();

    }


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


    @FXML
    private void onResetFilterPressed(ActionEvent actionEvent) {
    }

    @FXML
    private void onFilterPressed()
    {
  String firstName=firstNameFilter.getText();
  String lastName=lastNameFilter.getText();
  String phoneNo=phoneNumberFilter.getText();
  ArrayList<Booking> filterCheckIn=modelManager.filterCheckIn(firstName,lastName,phoneNo);
  bookingsToDisplay.setAll(filterCheckIn);

    }

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

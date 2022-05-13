package View.CheckIn;

import View.AlertBox;
import entity.Booking;
import entity.Guest;
import entity.Room;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.ModelManager;

import java.awt.image.AreaAveragingScaleFilter;
import java.awt.print.Book;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class CheckIn implements Initializable
{

  @FXML private TextField firstName, lastName, phoneNumber;
  @FXML private TableView<Booking> tableView;
  @FXML private TableColumn<Booking, Integer> roomNumberColumn;
  @FXML private TableColumn<Booking, LocalDate> arrivalDateColumn;
  @FXML private TableColumn<Booking, LocalDate> departureDateColumn;
  @FXML private TabPane tabPane;
  @FXML private Label errorLabel;
  @FXML private Tab checkInTab;

  // Next tab
  @FXML private TextField firstNameCheckIn, lastNameCheckIn, address, phoneNo, nationality;
  @FXML private DatePicker dateOfBirth;
  @FXML private CheckBox extraBed;

  private ObservableList<Booking> bookingsToDisplay;
  private ArrayList<Guest> addedGuests;
  private Booking selectedBooking;
  private ModelManager modelManager;

  @Override public void initialize(URL url, ResourceBundle resourceBundle)
  {
    modelManager = new ModelManager();
    bookingsToDisplay = FXCollections.observableArrayList();
    addedGuests = new ArrayList<>();
    initializeTable();
    inititializeTabs();

  }

  private void inititializeTabs()
  {
    checkInTab.setDisable(true);
    checkInTab.getContent().setDisable(false);

  }

  @FXML private void onSearchPressed()
  {
    String firstname = firstName.getText();
    String lastname = lastName.getText();
    String phoneNumberText = phoneNumber.getText();

    ArrayList<Booking> bookings = modelManager.searchBooking(firstname,
        lastname, phoneNumberText);
    bookingsToDisplay.setAll(bookings);

  }

  @FXML private void addGuestPressed()
  {
    String firstname = firstNameCheckIn.getText().trim();
    String lastname = lastNameCheckIn.getText().trim();
    String addressTemp = address.getText().trim();
    String phone = phoneNo.getText().trim();
    String nationalityTemp = nationality.getText().trim();
    LocalDate dob = dateOfBirth.getValue();
    Guest guest = new Guest(firstname,lastname,phone,nationalityTemp,addressTemp,dob);
    addedGuests.add(guest);
    clearFields();

  }

  private void clearFields()
  {
    firstNameCheckIn.clear();
    lastNameCheckIn.clear();
    address.clear();
    phoneNo.clear();
    dateOfBirth.setValue(LocalDate.now());
    nationality.clear();
  }

  @FXML private void checkInPressed()
  {
    modelManager.addGuestsToBooking( selectedBooking, addedGuests);
    AlertBox.display("Checked in sucessfullly");
    clearFields();

  }

  private void initializeTable()
  {
    roomNumberColumn.setCellValueFactory(
        new PropertyValueFactory<>("roomNumber"));
    arrivalDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("arrival"));
    departureDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("departure"));
    tableView.setItems(bookingsToDisplay);

  }

  @FXML private void nextPressed()
  {
    Booking booking = tableView.getSelectionModel().getSelectedItem();
    if (booking == null)
    {
      errorLabel.setText("Select booking first");
      clearLabel();
      return;
    }
    selectedBooking = booking;
    tabPane.getSelectionModel().select(1);
  }

  private void clearLabel()
  {

    // clearing the label
    new Thread(() -> {
      try
      {
        Thread.sleep(2500);
        Platform.runLater(() -> {
          errorLabel.setText(null);
        });
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }).start();

  }
}

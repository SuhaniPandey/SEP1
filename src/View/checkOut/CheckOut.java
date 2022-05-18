package View.checkOut;

import View.booking.SelectedRoomFromBooking;
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

import java.net.URL;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CheckOut implements Initializable
{
  @FXML private TextField firstName, lastName, phoneNumber;
  @FXML private TableView<Booking> tableView;
  @FXML private TableColumn<Booking, Integer> roomNumberColumn;
  @FXML private TableColumn<Booking,LocalDate> ArrivalDateColumn,departureDateColumn;
  @FXML private DatePicker arrivalDate, departureDate;
  @FXML private Label nightStayed, smokingFee,discount,finalPrice;
  @FXML private Tab checkOutTab;
  @FXML private Label errorLabel;
  @FXML private TabPane tabPane;
  private Booking selectedCheckIn;
  private ModelManager modelManager;
  private ObservableList<Booking> checkInToDisplay;

  //all checked in guest search
  @FXML private TableView<Booking> checkInAllTableView;
  @FXML private TableColumn<Booking,String> mainBooker;
  @FXML private TableColumn<Booking,String> guest;
  @FXML private TableColumn<Booking,Integer> roomNumber;
  @FXML private TableColumn<Booking,LocalDate> checkedInDate;
  @FXML private TableColumn<Booking,LocalDate> checkedOutDate;


  @Override public void initialize(URL url, ResourceBundle resourceBundle)
  {
    modelManager=new ModelManager();
    checkInToDisplay= FXCollections.observableArrayList();
    initializeTabs();
    initializeTable();
    initializeDates();
  }
  private void initializeTabs()
  {
    checkOutTab.setDisable(true);
    checkOutTab.getContent().setDisable(false);
  }
  private void initializeTable()
  {
    roomNumberColumn.setCellValueFactory(
        new PropertyValueFactory<>("roomNumber"));
    ArrivalDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("arrival"));
    departureDateColumn.setCellValueFactory(
        new PropertyValueFactory<>("departure"));
    tableView.setItems(checkInToDisplay);
  }

  @FXML
  private void onSearchPressed()
  {
 String firstname=firstName.getText();
 String lastname=lastName.getText();
 String phoneno=phoneNumber.getText();
  ArrayList<Booking> bookings = modelManager.searchCheckIn(firstname,lastname, phoneno);
    checkInToDisplay.setAll(bookings);
  }


  @FXML
  private void onCheckOutPressed()
  {
  }
  private void initializeDates()
  {
    departureDate.setValue(LocalDate.now());
    arrivalDate.setEditable(false);
    arrivalDate.setDisable(true);
    arrivalDate.setOpacity(1);
    departureDate.setOpacity(1);
    departureDate.setDisable(true);
    departureDate.setEditable(false);

  }

  public void onNextpressed()
  {
    Booking booking = tableView.getSelectionModel().getSelectedItem();
    if (booking == null)
    {
      errorLabel.setText("Select Checked In guest first");
      clearLabel();
      return;
    }
    selectedCheckIn = booking;
    tabPane.getSelectionModel().select(1);
    arrivalDate.setValue(selectedCheckIn.getArrival());
    nightStayed.setText(String.valueOf(ChronoUnit.DAYS.between(selectedCheckIn.getArrival(),LocalDate.now())));
   finalPrice.setText(String.valueOf(selectedCheckIn.checkOutPrice()));
  }
  public void onSmokingPressed()
  {
    smokingFee.setText(String.valueOf(selectedCheckIn.smokingFee()));
  }

  public void onDiscountPressed()
  {
    discount.setText(String.valueOf(selectedCheckIn.discount()));
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

package View.rooms;

import View.AlertBox;
import View.booking.SelectedRoomFromBooking;
import entity.RoomList;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import utils.ModelManager;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import utils.ViewHandler;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class Rooms implements Initializable
{
  @FXML private AnchorPane anchorPane;
  @FXML private TableView roomTableView;
  @FXML private TableColumn<Room, String> roomTableType;
  @FXML private TableColumn<Room, Integer> roomTablePrice;
  @FXML private TableColumn<Room, Integer> roomTableNumber;
  @FXML private DatePicker arrivalDate, departureDate;
  @FXML private ChoiceBox<Room.RoomType> roomTypeChoice;

  @FXML private Label errorLabel;
  @FXML private Label errorDate;

  private ModelManager modelManager;

  private ObservableList<Room> roomsToDisplay;

  public void initialize(URL url, ResourceBundle resourceBundle)
  {
    modelManager = new ModelManager();
    roomsToDisplay = FXCollections.observableArrayList();

    initializeRoomType();
    initializeDatePickers();
    initializeTable();

  }

  @FXML private void searchPressed(ActionEvent actionEvent)
  {
    LocalDate arrival = arrivalDate.getValue();
    LocalDate departure = departureDate.getValue();
    Room.RoomType roomType = roomTypeChoice.getValue();

    if (departure.isBefore(arrival))
    {
      errorDate.setText("Departure date is before arrival date");
      clearLabel();
      return;
    }
    RoomList roomList = modelManager.searchRooms(arrival, departure, roomType);
    roomsToDisplay.setAll(roomList.getRooms());

  }

  @FXML private void bookPressed(ActionEvent actionEvent)
  {
    Room room = (Room) roomTableView.getSelectionModel().getSelectedItem();
    if (room == null)
    {
      errorLabel.setText("Please select a room to book");
      clearLabel();
      return;
    }

    SelectedRoomFromBooking.setSelectedRoom(room);
    SelectedRoomFromBooking.setArrivalDate(arrivalDate.getValue());
    SelectedRoomFromBooking.setDepartureDate(departureDate.getValue());
    anchorPane.getChildren().clear();
    anchorPane.getChildren().setAll(ViewHandler.getCreateBooking());
  }

  private void clearLabel()
  {


    // clearing the label
    new Thread(() -> {
      try
      {
        Thread.sleep(2000);
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

  private void initializeRoomType()
  {
    Room.RoomType[] allRoomTypes = Room.RoomType.class.getEnumConstants();
    ObservableList<Room.RoomType> observableList = FXCollections.observableArrayList(
        allRoomTypes);
    roomTypeChoice.setItems(observableList);
    roomTypeChoice.setValue(Room.RoomType.Single_Bedroom);
  }

  private void initializeDatePickers()
  {
    arrivalDate.setValue(LocalDate.now());
    departureDate.setValue(LocalDate.now().plusDays(1));

  }

  private void initializeTable()
  {
    roomTableType.setCellValueFactory(new PropertyValueFactory<>("roomType"));
    roomTablePrice.setCellValueFactory(new PropertyValueFactory<>("price"));
    roomTableNumber.setCellValueFactory(
        new PropertyValueFactory<>("roomNumber"));
    roomTableView.setItems(roomsToDisplay);

  }

}

package View.rooms;

import javafx.scene.control.*;
import utils.ModelManager;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.ResourceBundle;

public class Rooms implements Initializable
{
  @FXML private TableView roomTableView;
  @FXML private TableColumn<Room, String> roomTableName;
  @FXML private TableColumn<Room, Integer> roomTablePrice;
  @FXML private TableColumn<Room, Boolean> roomTableAvailability;
  @FXML private DatePicker arrivalDate, departureDate;
  @FXML private ChoiceBox<Room.RoomType> roomTypeChoice;
  @FXML private CheckBox smokingCheckBox;

  private ModelManager modelManager;

  private ObservableList<Room> rooms;

  public void initialize(URL url, ResourceBundle resourceBundle)
  {
    modelManager = new ModelManager();
    rooms = FXCollections.observableArrayList();

  }

  @FXML private void searchPressed(ActionEvent actionEvent)
  {

  }

  @FXML private void bookPressed(ActionEvent actionEvent)
  {
  }

}

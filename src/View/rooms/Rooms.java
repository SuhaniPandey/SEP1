package View.rooms;

import utils.ModelManager;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Rooms implements Initializable
{
  @FXML private TableView roomTableView;
  @FXML private TableColumn<Room, String> roomTableName;
  @FXML private TableColumn<Room, Integer> roomTablePrice;
  @FXML private TableColumn<Room, Boolean> roomTableAvailability;
  private ModelManager modelManager;

  ObservableList<Room> rooms;

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

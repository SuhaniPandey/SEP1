package View.rooms;

import entity.ModelManager;
import entity.Room;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;

import java.net.URL;
import java.util.ResourceBundle;

public class Rooms implements Initializable
{
  @FXML private TableView roomTableView;
  @FXML private TableRow<Room>roomTableName;
  @FXML private TableRow<Room> roomTablePrice;
  @FXML private TableRow<Room> roomTableAvailability;

  private ModelManager modelManager;
  ObservableList<Room> list= FXCollections.observableArrayList();
  public void initialize(URL url, ResourceBundle resourceBundle)
  {

  }
  private void loadDate()
  {
    list.removeAll(list);

  }

  @FXML private void searchPressed(ActionEvent actionEvent)
  {

  }

  @FXML private void bookPressed(ActionEvent actionEvent)
  {
  }


}

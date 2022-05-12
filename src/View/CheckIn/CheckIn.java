package View.CheckIn;

import entity.Booking;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckIn implements Initializable
{

  @FXML private TextField firstName, lastName, phoneNumber;
  @FXML private TableView<Booking> tableView;
  @FXML private TableColumn<Booking, Integer> roomNumberColumn;
  @FXML private TableColumn<Booking, LocalDate> arrivalDateColumn;
  @FXML private TableColumn<Booking, LocalDate> departureDateColumn;

  // Next tab
  @FXML private TextField firstNameCheckIn, lastNameCheckIn, address, phoneNo, nationality;
  @FXML private DatePicker dateOfBirth;
  @FXML private CheckBox extraBed;

  @Override public void initialize(URL url, ResourceBundle resourceBundle)
  {

  }

  @FXML private void onSearchPressed(){

  }

  @FXML private void addGuestPressed()
  {

  }

  @FXML private void checkInPressed()
  {

  }

}

package View.booking;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class Booking implements Initializable
{

  @FXML private TextField firstName, lastName, address, phoneNo, nationality;
  @FXML private DatePicker dateOfBirth, arrivalDate, departureDate;

// cancel
  @FXML private TextField firstNameCancel, lastNameCancel,  phoneNoCancel;







  @Override public void initialize(URL url, ResourceBundle resourceBundle)
  {
  }



  @FXML private void onSavePressed(){}

  @FXML private void onCancelPressed(){

  }
}



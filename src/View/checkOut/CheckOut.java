package View.checkOut;

import entity.Booking;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import utils.ModelManager;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CheckOut implements Initializable
{
  @FXML private TextField firstName, lastName, phoneNumber;
  @FXML private TableView<Booking> tableView;
  @FXML private TableColumn<Booking, Integer> roomNumberColumn;
  @FXML private TableColumn<Booking, LocalDate> arrivalDateColumn;
  @FXML private TableColumn<Booking, LocalDate> departureDateColumn;
  @FXML private DatePicker arrivalDate, departureDate;
  @FXML private Label nightStayed, smokingFee,discount,finalPrice;

  private ModelManager modelManager;

  @Override public void initialize(URL url, ResourceBundle resourceBundle)
  {
    modelManager=new ModelManager();


  }

  @FXML
  private void onSearchPressed(){



  }

  @FXML
  private void onCheckOutPressed(){

  }
}

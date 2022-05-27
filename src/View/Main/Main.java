package View.Main;

import utils.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Main
{
@FXML private AnchorPane anchorpaneSwap;



  /**
   * The function clears the anchorpaneSwap of all nodes, then adds the nodes from
   * the checkIn view to the anchorpaneSwap
   */
  public void checkInPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getcheckIn());
  }

  /**
   * It clears the anchorpaneSwap and adds the rooms view to it.
   */
  public void roomsPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getrooms());
  }

  /**
   * When the booking button is pressed, clear the anchorpaneSwap and add the
   * booking view to it.
   */
  public void bookingPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getbooking());
  }

  /**
   * The function is called when the user presses the "Check Out" button. The
   * function clears the AnchorPane and adds the CheckOutView to the AnchorPane
   */
  public void checkOutPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getcheckOut());
  }
}

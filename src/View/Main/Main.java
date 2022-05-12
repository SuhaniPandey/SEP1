package View.Main;

import utils.ViewHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class Main
{
@FXML private AnchorPane anchorpaneSwap;

public Main()
{

}

  public void checkInPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getcheckIn());
  }

  public void roomsPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getrooms());
  }

  public void bookingPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getbooking());
  }

  public void checkOutPressed()
  {
    this.anchorpaneSwap.getChildren().clear();
    this.anchorpaneSwap.getChildren().addAll(ViewHandler.getcheckOut());
  }
}

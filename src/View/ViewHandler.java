package View;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;

public class ViewHandler
{
public ViewHandler()
{

}
  private static Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ViewHandler.class.getResource(path));
    Parent root = (Parent)loader.load();
    return root;
  }

  public static Parent getcheckIn()
  {
    try
    {
      return loadFXML("../View/CheckIn/checkIn.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static Parent getrooms()
  {
    try
    {
      return loadFXML("../View/rooms/rooms.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }

  }

  public static Parent getbooking()
  {
    try
    {
      return loadFXML("../View/booking/booking.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }

  }

  public static Parent getcheckOut()
  {
    try
    {
      return loadFXML("../View/checkOut/checkOut.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }
}

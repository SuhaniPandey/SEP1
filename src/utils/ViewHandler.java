package utils;


import javafx.scene.Node;
import javafx.scene.Parent;

import java.io.IOException;
import javafx.fxml.FXMLLoader;

public class ViewHandler
{

   public static Parent getCheckIn()
   {
     try
     {
      return loadFXML("../View/CheckIn/CheckIn.fxml");
     }
     catch (IOException e)
     {
       e.printStackTrace();
       return null;
     }
   }
   public static Parent getCheckOut()
   {
     try
     {
       return loadFXML("../View/CheckOut/CheckOut.fxml");
     }
     catch (IOException e)
     {
       e.printStackTrace();
       return null;
     }

     }




  private static Parent loadFXML(String path) throws IOException
  {
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(ViewHandler.class.getResource(path));
    Parent root = loader.load();
    return root;
  }

  public static Parent getbooking()
  {
    try
    {
      return loadFXML("../View/Booking/Booking.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }
  }

  public static Parent getRooms()
  {
    try
    {
      return loadFXML("../View/Rooms/Rooms.fxml");
    }
    catch (IOException e)
    {
      e.printStackTrace();
      return null;
    }

  }
}

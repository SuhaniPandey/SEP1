package View;

import entity.*;
import utils.ModelManager;
import javafx.application.Application;

public class RunApp
{
  public static void main(String[] args)
  {


    // Testing .....
    /*
    ModelManager modelManager = new ModelManager();
    RoomList allRooms = modelManager.getAllRooms();
 //   System.out.println(allRooms);
    allRooms.getRooms().forEach(System.out::println);

     */
     Application.launch(GUI.class);

  }
}

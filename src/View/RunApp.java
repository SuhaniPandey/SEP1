package View;

import entity.*;
import utils.ModelManager;
import javafx.application.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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

    // Testing days in between
 /*   LocalDate localDate = LocalDate.now();
    LocalDate localDate1 = LocalDate.of(2002,7,22);

    System.out.println(ChronoUnit.DAYS.between(localDate1, localDate));

  */



    Application.launch(GUI.class);

  }
}

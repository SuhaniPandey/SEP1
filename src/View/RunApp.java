package View;

import entity.BookingList;
import entity.Room;
import entity.RoomList;
import javafx.application.Application;
import utils.ModelManager;

import java.awt.print.Book;
import java.io.File;

public class RunApp {
    public static void main(String[] args) {

      //  ModelManager.createXML();
      /*
        ModelManager modelManager = new ModelManager();
       modelManager.saveBookings(new BookingList());
       RoomList roomlist=new RoomList();
       roomlist.createList();
       modelManager.saveRoomList(roomlist);

       */

       Application.launch(GUI.class);

    }
}

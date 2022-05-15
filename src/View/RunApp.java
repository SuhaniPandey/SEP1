package View;

import View.booking.SelectedRoomFromBooking;
import entity.*;
import utils.ModelManager;
import javafx.application.Application;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;

public class RunApp
{
  public static void main(String[] args)
  {


/*
    ModelManager modelManager = new ModelManager();

    Guest guest = new Guest("asd","asd","<as","<s","ac",LocalDate.now());
   // Booking booking = new Booking(guest, new Room());
    RoomList rooms=new RoomList();
    modelManager.saveRoomList(rooms);

    /*booking.setArrival(LocalDate.now());
    booking.setDeparture(LocalDate.now().plusDays(3));
    modelManager.addBooking(booking);


    ModelManager modelManager=new ModelManager();
    RoomList roomList=new RoomList();
    roomList.createList();
    modelManager.saveRoomList(roomList);

 */












    Application.launch(GUI.class);

  }
}

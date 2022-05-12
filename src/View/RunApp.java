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


/*
    ModelManager modelManager = new ModelManager();

    Guest guest = new Guest("asd","asd","<as","<s","ac",LocalDate.now());
    Booking booking = new Booking(guest, new Room());
    booking.setArrival(LocalDate.now());
    booking.setDeparture(LocalDate.now().plusDays(3));
    modelManager.addBooking(booking);

 */





    Application.launch(GUI.class);

  }
}

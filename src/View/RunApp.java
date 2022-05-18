package View;

import entity.Booking;
import entity.Guest;
import entity.Room;
import entity.RoomList;
import javafx.application.Application;
import utils.ModelManager;

import java.time.LocalDate;

public class RunApp {
    public static void main(String[] args) {


 ModelManager modelManager = new ModelManager();

//    Guest guest = new Guest("asd","asd","<as","<s","ac", LocalDate.now());
//    Booking booking = new Booking(guest, new Room());
//    booking.addGuests(new Guest("Sachin", "Baral","(/(7788","sdf","asdfs",LocalDate.now()));
//    booking.setCheckedIn(true);
//    booking.setArrival(LocalDate.now());
//    booking.setDeparture(LocalDate.now().plusDays(3));
//
//    modelManager.addBooking(booking);








/*

        ModelManager modelManager=new ModelManager();
    RoomList roomList=new RoomList();
    roomList.createList();
    modelManager.saveRoomList(roomList);

 */





      Application.launch(GUI.class);

    }
}

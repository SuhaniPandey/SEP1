package View.booking;

import entity.Room;

import java.time.LocalDate;

public class SelectedRoomFromBooking
{
  private static Room selectedRoom;
  private static LocalDate arrivalDate, departureDate;


  public static void setSelectedRoom(Room room){
    selectedRoom = room;
  }
  public static void setArrivalDate(LocalDate arrivalDate)
  {
    SelectedRoomFromBooking.arrivalDate = arrivalDate;
  }

  public static void setDepartureDate(LocalDate departureDate)
  {
    SelectedRoomFromBooking.departureDate = departureDate;
  }

  public static LocalDate getArrivalDate()
  {
    return arrivalDate;
  }

  public static LocalDate getDepartureDate()
  {
    return departureDate;
  }

  public static Room getSelectedRoom()
  {
    return selectedRoom;
  }
}

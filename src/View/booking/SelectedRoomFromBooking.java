package View.booking;

import entity.Room;

import java.time.LocalDate;

public class SelectedRoomFromBooking
{
  private static Room selectedRoom;
  private static LocalDate arrivalDate, departureDate;


  /**
   * This function sets the selected room to the room that is passed in as a
   * parameter
   *
   * @param room The room to be selected.
   */
  public static void setSelectedRoom(Room room){
    selectedRoom = room;
  }
  /**
   * This function sets the arrival date of the selected room from the booking
   *
   * @param arrivalDate The date the user is arriving at the hotel.
   */
  public static void setArrivalDate(LocalDate arrivalDate)
  {
    SelectedRoomFromBooking.arrivalDate = arrivalDate;
  }

  /**
   * This function sets the departure date of the selected room from the booking
   *
   * @param departureDate The date the user is leaving the hotel.
   */
  public static void setDepartureDate(LocalDate departureDate)
  {
    SelectedRoomFromBooking.departureDate = departureDate;
  }

  /**
   * This function returns the arrival date of the flight
   *
   * @return The arrival date.
   */
  public static LocalDate getArrivalDate()
  {
    return arrivalDate;
  }

  /**
   * This function returns the departure date of the flight
   *
   * @return The departure date.
   */
  public static LocalDate getDepartureDate()
  {
    return departureDate;
  }

  /**
   * This function returns the selected room.
   *
   * @return The selected room.
   */
  public static Room getSelectedRoom()
  {
    return selectedRoom;
  }
}

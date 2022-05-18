package utils;

import View.CheckIn.CheckIn;
import entity.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * A class for accessing Booking objects and reading/writing to/from files
 *
 * @author
 * @version 1.0
 */
public class ModelManager implements Serializable
{
  private String BOOKING_FILENAME = "booking.bin";
  private String ROOM_FILENAME = "rooms.bin";

  /**
   * A no-argument constructor
   */
  public ModelManager()
  {
  }

  /**
   * Creates an array list BookingList of all Booking objects in the binary file
   *
   * @return array list of every booking object in the binary file
   */
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();

    try
    {
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(
          BOOKING_FILENAME);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error reading file");
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allBookings;
  }

  /**
   * Gets a Booking object which contains phoneNum from the list.
   *
   * @param phoneNum the phone number of the guest whose booking is needed
   * @return the Booking which contains phoneNum, else null
   */
  public BookingList getBookingByGuestPhoneNum(String phoneNum)
  {
    BookingList bookingByGuest = new BookingList();
    BookingList allBookings = getAllBookings();

    for (int i = 0; i < allBookings.size(); i++)
    {
      if ((allBookings.get(i).getMainBooker().getPhoneNumber()
          .equals(phoneNum)))
      {
        bookingByGuest.add(allBookings.get(i));

      }
    }
    return bookingByGuest;
  }

  // not sure
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();

    try
    {

      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(ROOM_FILENAME);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    catch (ClassNotFoundException e)
    {
      System.out.println("Class Not Found");
    }
    return allRooms;
  }

  /**
   * Creates array list only containing Room objects with a isAvailable()=True value.
   *
   * @return array list of isAvailable Room objects
   */
  public RoomList getAllAvailableRooms()
  {
    RoomList availableRooms = new RoomList();
    RoomList allRooms = getAllRooms();

    for (int i = 0; i < allRooms.size(); i++)
    {
      if (allRooms.get(i).IsAvailable())
      {
        availableRooms.add(allRooms.get(i));
      }
    }

    return availableRooms;
  }

  /**
   * Adds a Booking to the binary file.
   *
   * @param booking the Booking object which will be added to the binary file
   */
  public void addBooking(Booking booking)
  {
    BookingList allBookings = getAllBookings();
    allBookings.addBooking(booking);
    saveBookings(allBookings);
  }

  /**
   * Adds an array list of Booking objects to the binary file.
   *
   * @param bookings the BookingList array list which will be added to the binary file
   */
  public void addBookings(BookingList bookings)
  {
    BookingList allBookings = getAllBookings();
    allBookings.addAllBookings(bookings.getBookings());
    saveBookings(allBookings);

  }


  public void deleteBooking(String firstName,String lastName,String phoneNumber)
  {
    BookingList allBookings = getAllBookings();
    for (int i = 0; i < allBookings.size(); i++)
    {
      Booking booking=allBookings.get(i);
      if (booking.getMainBooker().getFirstName().equals(firstName) && booking.getMainBooker().getLastName().equals(lastName)
          && booking.getMainBooker().getPhoneNumber().equals(phoneNumber))
      {
        allBookings.removeBooking(booking);
      }
    }
    saveBookings(allBookings);

  }

  /**
   * Updates the binary file will newly added or removed Booking objects.
   *
   * @param bookings the array list which contains all Booking objects
   */
  public void saveBookings(BookingList bookings)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(BOOKING_FILENAME, bookings);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }
  }

  public void saveRoomList(RoomList roomList)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(ROOM_FILENAME, roomList);

    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO Error writing to file");
    }

    try
    {
      FileOutputStream fileOut = new FileOutputStream("rooms.xml");
      PrintWriter write = new PrintWriter(fileOut);
      int size = roomList.size();
      for (int i = 0; i < size; i++)
      {
        String str = roomList.get(i).toString();
        MyFileHandler.writeToTextFile("rooms.xml", str);
        /*write.print(str + ",");
        if(i < size-1)
        write.print(",");*/
      }


      write.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }

  public void addGuestsToBooking(Booking booking, ArrayList<Guest> guests)
  {
    BookingList allBookings = getAllBookings();
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).equals(booking))
      {
        allBookings.get(i).addAllGuests(guests);
        break;
      }
    }
    saveBookings(allBookings);
  }

  public void addAllRooms(ArrayList<Room> rooms)
  {
    RoomList allRooms = getAllRooms();
    allRooms.addAll(rooms);
    saveRoomList(allRooms);
  }

  // searching room
  public RoomList searchRooms(LocalDate arrival, LocalDate departure,
      Room.RoomType roomType)
  {
    // setting the temp list with all rooms
    RoomList allRooms = getAllRooms();
    BookingList allBookings = getAllBookings();
    RoomList roomList = new RoomList();

    for (Room room : allRooms.getRooms())
    {
      if (!(room.getRoomType().equals(roomType)))
        continue;  // do not add when roomtype dont match
      roomList.add(room);
    }

    for (Booking booking : allBookings.getBookings())
    {
      LocalDate arrivalDate = booking.getArrival();
      LocalDate departureDate = booking.getDeparture();

      // the room is not booked in either of the two given condition
      // so we remove the room if neither is satisfied

      if (!(arrival.isBefore(arrivalDate) && departure.isBefore(arrivalDate)))
      {
        if (!(departure.isAfter(departureDate) && arrival.isAfter(
            departureDate)))
        {
          roomList.getRooms().remove(booking.getRoom());
        }
      }
    }
    return roomList;
  }

  public ArrayList<Booking> searchBooking(String firstname, String lastname,
      String phoneNumberText)
  {
    BookingList allBookings = getAllBookings();
    ArrayList<Booking> bookings = new ArrayList<>();
    for (Booking booking : allBookings.getBookings())
    {
      Guest guest = booking.getMainBooker();
      if (!guest.getFirstName().equals(firstname))
        continue;
      if (!guest.getLastName().equals(lastname))
        continue;
      if (!guest.getPhoneNumber().equals(phoneNumberText))
        continue;
      bookings.add(booking);
    }
    return bookings;
  }

  public ArrayList<Booking> filterBookings(LocalDate dateFrom, LocalDate dateTo) {
    BookingList allBookings = getAllBookings();
    ArrayList<Booking> filteredBooking = new ArrayList<>();
    for (Booking booking : allBookings.getBookings()
    ) {
      boolean include = false;
      // following if-else checks if the given date includes the booked date..
      if ((booking.getDeparture().isBefore(dateTo) && (booking.getDeparture().isAfter(dateFrom))) || booking.getDeparture().isEqual(dateTo))
        include = true;
      else if ((booking.getArrival().isAfter(dateFrom) && (booking.getArrival().isBefore(dateTo))) || booking.getArrival().isEqual(dateFrom))
        include = true;
      if (include) {
        filteredBooking.add(booking);
      }
    }
    return filteredBooking;
  }

  public ArrayList<Booking> searchCheckIn(String firstname, String lastname, String phoneno)
  {
    BookingList allBookings = getAllBookings();
    ArrayList<Booking> allCheckIn = new ArrayList<>();
    for (Booking booking : allBookings.getBookings()) {
      Guest guest = booking.getMainBooker();
      if (!guest.getFirstName().equals(firstname))
        continue;
      if (!guest.getLastName().equals(lastname))
        continue;
      if (!guest.getPhoneNumber().equals(phoneno))
        continue;
      if (booking.getIsCheckedIn())
        allCheckIn.add(booking);
    }
    return allCheckIn;

  /*public void createFile(ArrayList<String> RoomList)
  {
    try
    {
      FileOutputStream fileOut = new FileOutputStream("rooms.xml");
      PrintWriter write = new PrintWriter(fileOut);
      int size = RoomList.size();
      for (int i = 0; i < size; i++)
      {
        String str = RoomList.get(i);
        MyFileHandler.writeToTextFile("rooms.xml", str);
        *//*write.print(str + ",");
        if(i < size-1)
        write.print(",");*//*
      }


      write.close();
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
  }*/

}
  public boolean deleteBooking(Booking booking) {
    BookingList allBookings = getAllBookings();
    boolean isRemoved = allBookings.removeBooking(booking);
    saveBookings(allBookings);
    return isRemoved;
  }

  public void checkIn(Booking selectedBooking)
  {
    BookingList allBookings = getAllBookings();
    for (Booking booking : allBookings.getBookings())
    {
      if (booking.equals(selectedBooking))
      {
        booking.setCheckedIn(true);
        break;
      }
    }
    saveBookings(allBookings);
  }
}

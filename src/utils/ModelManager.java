package utils;

import entity.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
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

  /**
   * Removes a Booking object from the binary file
   *
   * @param booking the Booking object which will be removed from the binary file
   */
  public void deleteBooking(Booking booking)
  {
    BookingList allBookings = getAllBookings();
    allBookings.removeBooking(booking);
    saveBookings(allBookings);

  }

  /**
   * Removes a Booking using a Guest object containing phoneNum.
   *
   * @param phoneNum the phone number which corresponds to a Guest object
   */
  public void deleteBookingByGuestPhoneNum(String phoneNum)
  {
    BookingList allBookings = getAllBookings();
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).getMainBooker().getPhoneNumber().equals(phoneNum))
      {
        allBookings.removeBooking(allBookings.get(i));
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
      int dubug =1;
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

  public void addGuestToBooking(Booking booking, Guest guest)
  {
    BookingList allBookings = getAllBookings();
    for (int i = 0; i < allBookings.size(); i++)
    {
      if (allBookings.get(i).equals(booking))
      {
        allBookings.get(i).addGuests(guest);
        break;
      }
    }
    saveBookings(allBookings);
  }

  public void addAllRooms(ArrayList<Room> rooms)
  {
    RoomList allRooms = getAllRooms();
    allRooms.addAll(rooms);
    System.out.println("Before saving .............");
    allRooms.getRooms().forEach(System.out::println);
    saveRoomList(allRooms);
  }
}

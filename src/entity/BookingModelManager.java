package entity;

import utils.MyFileHandler;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * A class for accessing Booking objects and reading/writing to/from files
 * @author
 * @version 1.0
 */
public class BookingModelManager
{
  private String fileName;

  /**
   * Initializes the fileName instance variable.
   * @param fileName the chosen name for the file
   */
  public BookingModelManager(String fileName)
  {
    this.fileName = fileName;
  }

  /**
   * Creates an array list BookingList of all Booking objects in the binary file
   * @return array list of every booking object in the binary file
   */
  public BookingList getAllBookings()
  {
    BookingList allBookings = new BookingList();

    try
    {
      allBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);
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
   * @param phoneNum the phone number of the guest whose booking is needed
   * @return the Booking which contains phoneNum, else null
   */
  public BookingList getBookingByGuest(String phoneNum)
  {
    BookingList bookingByGuest = new BookingList();
    BookingList allBookings = getAllBookings();

    for (int i = 0; i < allBookings.size(); i++)
    {
      if ((allBookings.get(i).getPhoneNumber().equals(phoneNum)))
      {
        bookingByGuest.add(allBookings.get(i));
        return bookingByGuest;
      }
    }
    return null;
  }

  // not sure
  public RoomList getAllRooms()
  {
    RoomList allRooms = new RoomList();

    try
    {
      allRooms = (RoomList) MyFileHandler.readFromBinaryFile(fileName);
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
    return allRooms;
  }

  /**
   * Creates array list only containing Room objects with a isAvailable()=True value.
   * @return array list of isAvailable Room objects
   */
  public RoomList getAllAvailableRooms()
  {
    RoomList availableRooms = new RoomList();
    RoomList allRooms = getAllRooms();

    for (int i = 0; i < allRooms.size(); i++)
    {
      if (allRooms.get(i).getIsAvailable())
      {
        availableRooms.add(allRooms.get(i));
      }
    }

    return availableRooms;
  }

  /**
   * Adds a Booking to the binary file.
   * @param booking the Booking object which will be added to the binary file
   */
  public void addBooking(Booking booking)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, booking);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error writing to file");
    }
  }

  /**
   * Adds an array list of Booking objects to the binary file.
   * @param bookings the BookingList array list which will be added to the binary file
   */
  public void addBookings(BookingList bookings)
  {
    for (int i = 0; i < bookings.size(); i++)
    {
      try
      {
        MyFileHandler.writeToBinaryFile(fileName, bookings.getBooking(i));
      }
      catch (FileNotFoundException e)
      {
        System.out.println("File not found");
      }
      catch (IOException e)
      {
        System.out.println("IO error writing to file");
      }
    }
  }

  /**
   * Removes a Booking object from the binary file
   * @param booking the Booking object which will be removed from the binary file
   */
  public void deleteBooking(Booking booking)
  {
    BookingList allBookings = getAllBookings();
    BookingList newBookings = new BookingList();
    try
    {
      for (int i = 0; i < allBookings.size(); i++)
      {
        if (!(allBookings.getBooking(i) == booking))
          newBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);

      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error reading file");
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("Class not found");
    }
    try
    {
      for (int i = 0; i < newBookings.size(); i++)
      {
        MyFileHandler.writeToBinaryFile(fileName, newBookings);
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error reading file");
    }
  }

  /**
   * Removes a Booking using a Guest object containing phoneNum.
   * @param phoneNum the phone number which corresponds to a Guest object
   */
  public void deleteByGuest(String phoneNum)
  {
    BookingList allBookings = getAllBookings();
    BookingList newBookings = new BookingList();
    try
    {
      for (int i = 0; i < allBookings.size(); i++)
      {
        if (!(allBookings.getBooking(i).getPhoneNumber().equals(phoneNum)))
          newBookings = (BookingList) MyFileHandler.readFromBinaryFile(fileName);

      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error reading file");
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("Class not found");
    }
    try
    {
      for (int i = 0; i < newBookings.size(); i++)
      {
        MyFileHandler.writeToBinaryFile(fileName, newBookings);
      }
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error reading file");
    }
  }

  /**
   * Updates the binary file will newly added or removed Booking objects.
   * @param bookings the array list which contains all Booking objects
   */
  public void saveBookings(BookingList bookings)
  {
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, bookings);
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
    try
    {
      MyFileHandler.writeToBinaryFile(fileName, booking);
    }
    catch (FileNotFoundException e)
    {
      System.out.println("File not found");
    }
    catch (IOException e)
    {
      System.out.println("IO error writing to file");
    }
  }

}

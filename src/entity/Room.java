package entity;

import java.io.Serializable;

/**
 * A Class Containing Room Objects
 *
 * @author Devlin Onichuk
 * @version 1.0
 */
public class Room implements Serializable
{
  private boolean isAvailable;
  private int roomNumber;
  private RoomType roomType;
  private double price;
  private int numberOfRooms;

  public Room(RoomType roomType)
  {
    /**
     * A Three Argument Constructor creating a new Room Object, Defaults extra bed to false and isAvailable to True
     */
    this.isAvailable = true;
    this.roomType = roomType;

  }

  /**
   * Method to calculate room price of given room type
   */
  private void calculateRoomPrice() {
    if (roomType == RoomType.Single_Bedroom)
      price = 129;
    else if (roomType == RoomType.Double_Bedroom)
      price = 169;
    else if (roomType == RoomType.Single_Bedroom_Suite)
      price = 259;
    else if (roomType == RoomType.Double_Bedroom)
      price = 339;
    else if (roomType == RoomType.Three_Bedroom_Suite)
      price = 399;
  }

  public Room()
  {
    /**
     * A no Argument Constructor, defaulting extraBed to false, isAvailable to true.
     */

    this.isAvailable = true;
    roomType = RoomType.Single_Bedroom; // initialized it with single room

  }
  /**
   * @return the type of room
   */
  public RoomType getRoomType()
  {
    return roomType;
  }

  /**
   * @return the room number
   */
  public int getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * sets the number of rooms based on the type of room
   *
   * @param roomType given in the constructor
   */
  public static int getNumberOfRooms(RoomType roomType)
  {
    switch (roomType)
    {
      case Single_Bedroom:
        return 10;

      case Double_Bedroom:
        return 27;

      case Single_Bedroom_Suite:
        return 3;

      case Two_Bedroom_Suite:
        return 1;

      case Three_Bedroom_Suite:
        return 1;

    }
    return -1;
  }

  /**
   * sets a new room number
   *
   * @param roomNumber sets room number by given int
   */
  public void setRoomNumber(int roomNumber)
  {
    this.roomNumber = roomNumber;
  }


  // enums are constant , and make our life easier later since we dont have to input strings
  // eg , if we mistype "double bedrom" , then later getAllDoubleBedroom will not take that room because
  // "double bedroom" is not equal to "double bedrom"

  /**
   * a method to set each room type to constants
   */
  public enum RoomType implements Serializable
  {
    Single_Bedroom, Double_Bedroom, Single_Bedroom_Suite, Two_Bedroom_Suite, Three_Bedroom_Suite
  }

  /**
   * takes a String and sets it to the roomType
   *
   * @param roomType set by given String
   */
  public void setRoomType(RoomType roomType)
  {
    this.roomType = roomType;
  }

  /**
   * sets the price of a room by the given double
   *
   * @param price set by double
   */
  public void setPrice(double price)
  {
    this.price = price;
  }

  /**
   * @return price
   */
  public double getPrice()
  {
    calculateRoomPrice();
    return price;
  }


  /**
   * combines all the room information and creates a string of all the information in the room
   *
   * @return all the room information in a string
   */
  public String toString()
  {
    return roomNumber + " Type: " + roomType + " Price: " + price
        + " Number of Rooms: " + numberOfRooms + " Availability: " + isAvailable;
  }

  // if Room number is equal then the room is same..

  /**
   * A method to check if the room numbers are equal
   * @param obj to be compared to Room
   * @return true if the object is a room object and the room number is the same
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Room))
      return false;
    Room other = (Room) obj;
    return this.roomNumber==other.getRoomNumber();
  }
}

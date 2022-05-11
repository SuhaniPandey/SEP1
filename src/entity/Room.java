package entity;

/**
 * A Class Containing Room Objects
 * @author Devlin Onichuk
 * @version 2.0
 */
public class Room
{
  private boolean extraBed;
  private boolean isAvailable;
  private int roomNumber;
  private String roomType;
  private double price;
  private int numberOfRooms;

  public Room(boolean extraBed, boolean isAvailable, String roomType)
  {
    /**
     * A Three Argument Constructor creating a new Room Object, Defaults extra bed to false and isAvailable to True
     * Sets the default room prices by type of room
     */
    this.extraBed = false;
    this.isAvailable = true;
    this.roomType = roomType;
    this.roomNumber = 0;
    if(roomType.equals("Single Bedroom"))
      price = 129;
    else if(roomType.equals("Double Bedroom"))
      price = 169;
    else if(roomType.equals("Single Bedroom Suite"))
      price = 259;
    else if(roomType.equals("Two Bedroom Sutie"))
      price = 339;
    else if (roomType.equals("Three Bedroom Suite"))
      price = 399;
  }
  /**
   * A no Argument Constructor, defaulting extraBed to false, isAvailable to true.
   */
  public Room()
  {
    this.extraBed = false;
    this.isAvailable = true;
    this.roomType = "";
    this.roomNumber = 0;
  }
  /**
   * A method that changes Room isAvailable to true/false.
   */
  public void setIsAvailable()
  {
    if(!(isAvailable))
      isAvailable = true;
    else
      isAvailable = false;
  }

  /**
   * A method that changes Changes extraBed to true/false
   */
  public void setExtraBed()
  {
    if(!(extraBed))
      extraBed = true;
    else
      extraBed = false;
  }

  /**
   * checks the availability of the room
   * @return the Availability of the room
   */
  public boolean getIsAvailable()
  {
    return isAvailable;
  }

  /**
   * A method to see if there is an Extra Bed needed
   * @return if the room needs an extra bed
   */
  public boolean isExtraBed()
  {
    return extraBed;
  }

  /**
   * A method to get room type
   * @return the type of room
   */
  public String getRoomType()
  {
    return roomType;
  }

  /**
   * A method to get room number
   * @return the room number
   */
  public int getRoomNumber()
  {
    return roomNumber;
  }

  /**
   * sets the number of rooms based on the type of room
   * @param roomType given in the constructor
   */
  public void setNumberOfRooms(String roomType)
  {
    if(roomType.equals("Single Bedroom"))
      numberOfRooms = 10;
    else if(roomType.equals("Double Bedroom"))
      numberOfRooms = 27;
    else if(roomType.equals("Single Bedroom Suite"))
      numberOfRooms = 3;
    if(roomType.equals("Two Bedroom Suite"))
      numberOfRooms = 1;
    if(roomType.equals("Three Bedroom Suite"))
      numberOfRooms = 1;
  }

  /**
   * sets a new room number
   * @param roomNumber sets room number by given int
   */
  public void setRoomNumber(int roomNumber)
  {
    this.roomNumber = roomNumber;
  }

  /**
   * takes a String and sets it to the roomType
   * @param roomType set by given String
   */
  public void setRoomType(String roomType)
  {
    this.roomType = roomType;
  }

  /**
   * sets the price of a room by the given double
   * @param price set by double
   */
  public void setPrice(double price)
  {
    this.price = price;
  }

  /**
   * A method to return the price
   * @return price
   */
  public double getPrice()
  {
    return price;
  }

  /**
   * combines all the room information and creates a string of all the information in the room
   * @return all the room information in a string
   */
  public String toString()
  {
    return roomNumber + " Type: " + roomType + " Price: " + price + " Number of Rooms: " +
        numberOfRooms + " Availability: " + isAvailable + " ExtraBed: " + extraBed;
  }

  /**
   * A method to see if an object is equal to Room object
   * @param obj takes in an Object
   * @return a Object of type Room
   */
  public boolean equals(Object obj)
  {
    if(!(obj instanceof Room))
      return false;

      Room other = (Room)obj;
      return (extraBed == other.extraBed && isAvailable == other.isAvailable && roomType.equals(other.roomType));
  }


}

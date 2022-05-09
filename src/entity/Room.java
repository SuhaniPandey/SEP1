package entity;

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
    this.extraBed = false;
    this.isAvailable = true;
    this.roomType = roomType;
    this.roomNumber = 0;
  }
  public Room()
  {
    this.extraBed = false;
    this.isAvailable = true;
    this.roomType = "";
    this.roomNumber = 0;
  }
  public void setIsAvailable(boolean isAvailable)
  {
    if(!(isAvailable))
      isAvailable = true;
    else
      isAvailable = false;
  }
  public void setExtraBed(boolean extraBed)
  {
    if(!(extraBed))
      extraBed = true;
    else
      extraBed = false;
  }
  public boolean getIsAvailable()
  {
    return isAvailable;
  }
  public boolean isExtraBed()
  {
    return extraBed;
  }
  public String getRoomType()
  {
    return roomType;
  }
  public int getRoomNumber()
  {
    return roomNumber;
  }
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
  public void setRoomNumber(int roomNumber)
  {
    this.roomNumber = roomNumber;
  }
  public void setRoomType(String roomType)
  {
    this.roomType = roomType;
  }
  public void setPrice(int price)
  {
    this.price = price;
  }
  public double getPrice()
  {
    return price;
  }
  public String toString()
  {
    return roomNumber + " Type: " + roomType + " Price: " + price + " Number of Rooms: " +
        numberOfRooms + " Availability: " + isAvailable + " ExtraBed: " + extraBed;
  }
}

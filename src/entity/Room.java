package entity;

public class Room
{
  private int roomNumber;
  private String roomType;
  private boolean isSmokingAllowed;

  public Room(int roomNumber, String roomType)
  {
    this.roomNumber = roomNumber;
    this.roomType = roomType;
    isSmokingAllowed= false;
  }

  public void setSmokingAllowed()
  {
    isSmokingAllowed=true;
  }

  public int getRoomNumber()
  {
    return roomNumber;
  }

  public String getRoomType()
  {
    return roomType;
  }

  @Override public String toString()
  {
    return "Room{" + "roomNumber=" + roomNumber + ", roomType='" + roomType
        + '\'' + ", isSmokingAllowed=" + isSmokingAllowed + '}';
  }
}
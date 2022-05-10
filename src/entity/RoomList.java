package entity;

import java.util.ArrayList;

/**
 * A public class that creates a list of Room objects
 * @author Devlin Onichuk
 */
public class RoomList
{
  private ArrayList<Room> rooms;
  private Room room;

  /**
   * An empty constructor initializing the ArrayList
   */
  public RoomList()
  {
    this.rooms = new ArrayList<>();
  }

  /**
   * A method that checks if the ArrayList contains the room of the given room number
   * @param roomNumber
   * @return the room by given room number
   */
  public Room getRoomByNumber(int roomNumber)
  {
    if(rooms.contains(room.getRoomNumber() == roomNumber))
      return room;
    else
      return null;
  }

  /**
   * A Method that adds all rooms to the arraylist, assign room type, and room number.
   * Assigns room Type based on the number of each room
   * Assigns room number in order of roomType
   */
  public void createList()
  {
    int roomNumber = 100;
    for (int i = 0; i <= 42; i++)
    {
      if (i <= 10)
      {
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType("Single Bedroom");
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(129);
      }
      else if (i > 10 && i <= 37)
      {
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType("Double Bedroom");
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(169);
      }
      else if (i > 37 && i <= 40)
      {
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType("Single Bedroom Suite");
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(259);
      }
      else if (i == 41)
      {
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType("Two Bedroom Suite");
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(339);
      }
      else if (i == 42)
      {
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType("Three Bedroom Suite");
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(399);
        break;
      }
    }
  }

  /**
   * A method to go through the arrayList by index and print room objects information in ascending order
   * @return All rooms inside the roomList
   */
  public String toString()
  {
    String string = "";
    for (int i = 0; i < rooms.size(); i++)
    {
      string += rooms.get(i) + "\n";
    }
    return string;
  }
}

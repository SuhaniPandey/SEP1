package entity;

import java.util.ArrayList;

public class RoomList
{
  private ArrayList<Room> rooms;
  private Room room;

  public RoomList()
  {
    this.rooms = new ArrayList<>();
  }
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

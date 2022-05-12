package entity;



import java.io.Serializable;
import java.util.ArrayList;

/**
 * A public class that creates a list of Room objects
 * @author Devlin Onichuk, Suhani Pandey
 */
public class RoomList implements Serializable
{
  private ArrayList<Room> rooms;

  /**
   * A no argument constructor initializing the ArrayList
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

    for (int i = 0; i < rooms.size(); i++)
    {
      if (rooms.get(i).getRoomNumber()==roomNumber){
        return rooms.get(i);
      }
    }
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
        Room room = new Room();
        room.setRoomType(Room.RoomType.Single_Bedroom);
        room.setRoomNumber(roomNumber++);
        room.setPrice(129);
        this.rooms.add(room);

        /**
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType(Room.RoomType.Single_Bedroom);
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(129);
         **/
      }
      else if (i > 10 && i <= 37)
      {Room room = new Room();
        room.setRoomType(Room.RoomType.Double_Bedroom);
        room.setRoomNumber(roomNumber++);
        room.setPrice(169);
        this.rooms.add(room);


        /*
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType(Room.RoomType.Double_Bedroom);
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(169);
         **/
      }
      else if (i > 37 && i <= 40)
      {
        Room room = new Room();
        room.setRoomType(Room.RoomType.Single_Bedroom_Suite);
        room.setRoomNumber(roomNumber++);
        room.setPrice(259);
        this.rooms.add(room);

        /*
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType(Room.RoomType.Single_Bedroom_Suite);
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(259);
         **/
      }
      else if (i == 41)
      {

        Room room = new Room();
        room.setRoomType(Room.RoomType.Two_Bedroom_Suite);
        room.setRoomNumber(roomNumber++);
        room.setPrice(339);
        this.rooms.add(room);
        /*
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType(Room.RoomType.Two_Bedroom_Suite);
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(339);

         */
      }
      else if (i == 42)
      {
        Room room = new Room();
        room.setRoomType(Room.RoomType.Three_Bedroom_Suite);
        room.setRoomNumber(roomNumber++);
        room.setPrice(399);
        this.rooms.add(room);
        /*
        this.rooms.add(new Room());
        this.rooms.get(i).setRoomType(Room.RoomType.Three_Bedroom_Suite);
        this.rooms.get(i).setRoomNumber(roomNumber += 1);
        this.rooms.get(i).setPrice(399);
        break;

         */
      }
    }
  }

  public ArrayList<Room> getRooms()
  {
    return rooms;
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

  public int size()
  {
    return rooms.size();
  }

  public Room get(int i)
  {
    if (i< rooms.size())
    {
      return rooms.get(i);
    }
    else {
      return null;
    }
  }
  public void add(Room room)
  {
    rooms.add(room);
  }
  public void set(Room room,int i)
  {
    rooms.set(i,room);
  }

  public void addAll(ArrayList<Room> rooms)
  {
    this.rooms.addAll(rooms);
  }
}

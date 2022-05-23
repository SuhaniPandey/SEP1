package entity;


import java.io.Serializable;
import java.util.ArrayList;

/**
 * A public class that creates a list of Room objects
 *
 * @author Devlin Onichuk
 * @version 1.0
 */
public class RoomList implements Serializable {
    private ArrayList<Room> rooms;

    /**
     * A no argument constructor initializing the ArrayList
     */
    public RoomList() {
        if (rooms == null) {
            createList();
        }
    }


    /**
     * A Method that adds all rooms to the arraylist, assign room type, and room number.
     * Assigns room Type based on the number of each room
     * Assigns room number in order of roomType
     */
    public void createList() {
        if (rooms != null) return;  // this makes sures that no one is creating these list of rooms again
        rooms = new ArrayList<>();
        int roomNumber = 100;
        for (int i = 0; i <= 42; i++) {
            if (i <= 10) {
                Room room = new Room();
                room.setRoomType(Room.RoomType.Single_Bedroom);
                room.setRoomNumber(roomNumber++);
                room.setPrice(129);
                this.rooms.add(room);
            } else if (i > 10 && i <= 37) {
                Room room = new Room();
                room.setRoomType(Room.RoomType.Double_Bedroom);
                room.setRoomNumber(roomNumber++);
                room.setPrice(169);
                this.rooms.add(room);
            } else if (i > 37 && i <= 40) {
                Room room = new Room();
                room.setRoomType(Room.RoomType.Single_Bedroom_Suite);
                room.setRoomNumber(roomNumber++);
                room.setPrice(259);
                this.rooms.add(room);
            } else if (i == 41) {

                Room room = new Room();
                room.setRoomType(Room.RoomType.Two_Bedroom_Suite);
                room.setRoomNumber(roomNumber++);
                room.setPrice(339);
                this.rooms.add(room);
            } else if (i == 42) {
                Room room = new Room();
                room.setRoomType(Room.RoomType.Three_Bedroom_Suite);
                room.setRoomNumber(roomNumber++);
                room.setPrice(399);
                this.rooms.add(room);
            }
        }
    }

    /**
     * A method to get Array list of room
     * @return room
     */

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    /**
     * A method to go through the arrayList by index and print room objects information in ascending order
     *
     * @return All rooms inside the roomList
     */
    public String toString() {
        String string = "";
        for (int i = 0; i < rooms.size(); i++) {
            string += rooms.get(i) + "\n";
        }
        return string;
    }

    /**
     * A method to get the size of the ArrayList
     *
     * @return the size of the ArrayList rooms
     */
    public int size() {
        return rooms.size();
    }

    /**
     * A method to get an object inside the ArrayList
     *
     * @param i is the index to get in the ArrayList
     * @return the object at index I
     */
    public Room get(int i) {
        if (i < rooms.size()) {
            return rooms.get(i);
        } else {
            return null;
        }
    }

    /**
     * A method to add a room object to the rooms ArrayList
     *
     * @param room is te object to be added to the ArrayList
     */
    public void add(Room room) {
        rooms.add(room);
    }


    /**
     * A method to add all rooms to the rooms ArrayList
     *
     * @param rooms is all the rooms to be added to the Array List
     */
    public void addAll(ArrayList<Room> rooms) {
        this.rooms.addAll(rooms);
    }
}

package utils;

import entity.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import java.io.File;

/**
 * A class for accessing Booking objects and reading/writing to/from files
 *Two files to read booking list and room list
 * @author Kristiyan, Suhani, Devlin
 * @version 1.0
 */
public class ModelManager implements Serializable {
    private String BOOKING_FILENAME = "booking.bin";
    private String ROOM_FILENAME = "rooms.bin";

    /**
     * A no-argument constructor
     */
    public ModelManager() {
    }

    /**
     * Creates an array list BookingList of all Booking objects in the binary file
     *
     * @return array list of every booking object in the binary file
     */
    public BookingList getAllBookings() {
        BookingList allBookings = new BookingList();

        try {
            allBookings = (BookingList) MyFileHandler.readFromBinaryFile(
                    BOOKING_FILENAME);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Error reading file");
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
        return allBookings;
    }


    /**
     * A method to get all rooms from room list
     * @return all rooms
     */
    public RoomList getAllRooms() {
        RoomList allRooms = new RoomList();

        try {

            allRooms = (RoomList) MyFileHandler.readFromBinaryFile(ROOM_FILENAME);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Class Not Found");
        }
        return allRooms;
    }



    /**
     * Adds a Booking to the binary file.
     *Bookings are added and every bookings are saved in the booking list at last
     * @param booking the Booking object which will be added to the binary file
     */
    public void addBooking(Booking booking) {
        BookingList allBookings = getAllBookings();
        allBookings.addBooking(booking);
        saveBookings(allBookings);
    }

    /**
     * Adds an array list of Booking objects to the binary file.
     *
     * @param bookings the BookingList array list which will be added to the binary file
     */
    public void addBookings(BookingList bookings) {
        BookingList allBookings = getAllBookings();
        allBookings.addAllBookings(bookings.getBookings());
        saveBookings(allBookings);

    }

    /**
     * Updates the binary file will newly added or removed Booking objects.
     *Saves the booking in binary files
     * @param bookings the array list which contains all Booking objects
     */
    public void saveBookings(BookingList bookings) {
        try {
            MyFileHandler.writeToBinaryFile(BOOKING_FILENAME, bookings);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Error writing to file");
        }
    }

    /**
     * A method to save roomList in binary files
     * @param roomList takes room list
     */

    public void saveRoomList(RoomList roomList) {
        try {
            MyFileHandler.writeToBinaryFile(ROOM_FILENAME, roomList);

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("IO Error writing to file");
        }
    }

    /**
     * A method to add guest to the booking
     * @param booking takes Booking
     * @param guests takes array list of guest
     *       First it takes all the booking from booking list
     *       if the booking is equal then it will add the arrayList of guest
     *
     */
    public void addGuestsToBooking(Booking booking, ArrayList<Guest> guests) {
        BookingList allBookings = getAllBookings();//happens 1 time
        for (Booking booking1 : allBookings.getBookings()//happens n times
        ) {
            if (booking1.equals(booking)){//Comparison happens 1 time per iteration
                booking1.addAllGuests(guests);//happens 1 time

                break;//breaks the loop only 1 time
            }

        }
        saveBookings(allBookings);//saves the booking to the booking list, happens 1 time
    // T(0)=1+n+1+1+1
        //removing all constants
        //O(n)
        //We choose this code to analyze because of its relative complexity
        //compared to other loops in this program
    }

    // searching room
    /**
     * > The function takes in a room type, arrival date and departure date and
     * returns a list of rooms that are available for the given dates
     *
     * @param arrival The date the guest wants to arrive
     * @param departure The date the guest is leaving the hotel.
     * @param roomType The type of room you want to search for.
     * @return A list of rooms that are available for the given dates and room
     * type.
     */
    public RoomList searchRooms(LocalDate arrival, LocalDate departure,
                                Room.RoomType roomType) {

        // setting the temp list with all rooms
        RoomList allRooms = getAllRooms();
        BookingList allBookings = getAllBookings();
        RoomList roomList = new RoomList();

        for (Room room : allRooms.getRooms()) {
            if ((room.getRoomType().equals(roomType))){
                roomList.add(room);
            }
        }


        for (Booking booking : allBookings.getBookings()) {
            LocalDate arrivalDate = booking.getArrival();
            LocalDate departureDate = booking.getDeparture();

            // the room is not booked in either of the two given condition
            // so we remove the room if neither is satisfied

            if (!(arrival.isBefore(arrivalDate) && departure.isBefore(arrivalDate))) {
                if (!(departure.isAfter(departureDate) && arrival.isAfter(
                        departureDate))) {
                    roomList.getRooms().remove(booking.getRoom());
                }
            }
        }
        return roomList;
    }

    /**
     * "Search for bookings by first name, last name, and phone number."
     *
     *
     *
     * @param firstname The first name of the guest
     * @param lastname The last name of the guest
     * @param phoneNumberText The phone number of the guest.
     * @return An ArrayList of Bookings
     */
    public ArrayList<Booking> searchBooking(String firstname, String lastname,
                                            String phoneNumberText) {
        BookingList allBookings = getAllBookings();
        ArrayList<Booking> bookings = new ArrayList<>();
        for (Booking booking : allBookings.getBookings()) {
            Guest guest = booking.getMainBooker();
            if (!guest.getFirstName().equals(firstname))
                continue;
            if (!guest.getLastName().equals(lastname))
                continue;
            if (!guest.getPhoneNumber().equals(phoneNumberText))
                continue;
            bookings.add(booking);
        }
        return bookings;
    }

    /**
     * It takes two dates as input and returns a list of bookings that are booked
     * between the two dates
     *
     * @param dateFrom The date from which the bookings are to be filtered.
     * @param dateTo The date to which the booking should be made.
     * @return An ArrayList of Booking objects.
     */
    public ArrayList<Booking> filterBookings(LocalDate dateFrom, LocalDate dateTo) {
        BookingList allBookings = getAllBookings();
        ArrayList<Booking> filteredBooking = new ArrayList<>();
        for (Booking booking : allBookings.getBookings()
        ) {
            boolean include = false;
            // following if-else checks if the given date includes the booked date..
            if ((booking.getDeparture().isBefore(dateTo) && (booking.getDeparture().isAfter(dateFrom))) || booking.getDeparture().isEqual(dateTo))
                include = true;
            else if ((booking.getArrival().isAfter(dateFrom) && (booking.getArrival().isBefore(dateTo))) || booking.getArrival().isEqual(dateFrom))
                include = true;
            if (include) {
                filteredBooking.add(booking);
            }
        }
        return filteredBooking;
    }

    /**
     * A method to search if the guest are checked in
     * @param firstname takes String
     * @param lastname takes String
     * @param phoneno takes String
     * @return all the checks in
     */

    public BookingList searchCheckIn(String firstname, String lastname, String phoneno) {
        BookingList allBookings = getAllBookings();
       BookingList allCheckIn=new BookingList();
        for (int i = 0; i < allBookings.size() ; i++)
        {
            Guest guest = allBookings.get(i).getMainBooker();
            if (!guest.getFirstName().equals(firstname))
                continue;
            if (!guest.getLastName().equals(lastname))
                continue;
            if (!guest.getPhoneNumber().equals(phoneno))
                continue;
            if (allBookings.get(i).getIsCheckedIn())
                allCheckIn.add(allBookings.get(i));
        }
        return allCheckIn;

    }

    /**
     * Delete a booking from the list of bookings and save the list.
     *
     * @param booking The booking to be deleted.
     * @return A boolean value.
     */
    public boolean deleteBooking(Booking booking) {
        BookingList allBookings = getAllBookings();
        boolean isRemoved = allBookings.removeBooking(booking);
        saveBookings(allBookings);
        return isRemoved;
    }


    /**
     * "If the selected booking is in the list of bookings, set its checked in
     * status to true."
     *
     * it has to load the list of bookings, find the
     * selected booking in the list, set its checked in status, and then save the
     * list of bookings
     *
     * @param selectedBooking The booking that the user has selected to check in.
     */
    public void checkIn(Booking selectedBooking) {
        BookingList allBookings = getAllBookings();
        for (Booking booking : allBookings.getBookings()) {
            if (booking.equals(selectedBooking)) {
                booking.setCheckedIn(true);
                break;
            }
        }
        saveBookings(allBookings);
    }


    /**
     * > This function returns an ArrayList of all the checked in bookings
     *
     * @return An ArrayList of Bookings that are checked in.
     */
    public ArrayList<Booking> getAllCheckedInbookings() {
        BookingList allBookings = getAllBookings();//happens 1 time
        ArrayList<Booking> checkedInsBookings = new ArrayList<>();//happens 1 time
        for (Booking booking : allBookings.getBookings()) {//happens n times
            if (booking.getIsCheckedIn()) checkedInsBookings.add(booking);//Comparison happens 1 time per iteration
        }
        return checkedInsBookings;//happens 1 time
    }// T(0)=1+1+n+1+1
    // removing all the constants
    // O(n)
    //Choose this code because of its complexity

    /**
     * > This function returns an ArrayList of Bookings that are checked in and
     * have the same first name, last name, and phone number as the parameters
     *
     * @param firstName The first name of the guest
     * @param lastName The last name of the guest
     * @param phoneNo The phone number of the guest
     * @return An ArrayList of Bookings
     */
    public ArrayList<Booking> filterCheckIn(String firstName, String lastName,
        String phoneNo)
    {
        BookingList allBookings = getAllBookings();
        ArrayList<Booking> filterCheckIn = new ArrayList<>();
        for (Booking checkIn : allBookings.getBookings()
        ) {
            Guest guest = checkIn.getMainBooker();
            if (!guest.getFirstName().equals(firstName))
                continue;
            if (!guest.getLastName().equals(lastName))
                continue;
            if (!guest.getPhoneNumber().equals(phoneNo))
                continue;
            if (checkIn.getIsCheckedIn())
                filterCheckIn.add(checkIn);
        }
        return filterCheckIn;
        }

    /**
     * A static method to create an XML File
     * to export room data to the Website
     */
    public static void createXML()
        {
            RoomList roomList = new RoomList();
            roomList.createList();

            try
            {
                DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
                DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
                Document doc = dBuilder.newDocument();

                Element rootElement = doc.createElement("Rooms");
                doc.appendChild(rootElement);
                for (int i = 0; i < roomList.size(); i++)
                {

                    // Element
                    Element room = doc.createElement("Room");
                    rootElement.appendChild(room);

                    // setting attribute to element
                    Attr attr = doc.createAttribute("Number");
                    attr.setValue(String.valueOf(roomList.get(i).getRoomNumber()));
                    room.setAttributeNode(attr);

                    // Element
                    Element roomType = doc.createElement("roomType");
                    roomType.appendChild(doc.createTextNode(String.valueOf(roomList.get(i).getRoomType())));
                    room.appendChild(roomType);

                    // Element
                    Element roomAvailability = doc.createElement("isAvailable");
                    roomAvailability.appendChild(doc.createTextNode("Available"));
                    room.appendChild(roomAvailability);

                    // Element
                    Element roomNumber= doc.createElement("roomNumber");
                    roomNumber.appendChild(doc.createTextNode(String.valueOf(roomList.get(i).getRoomNumber())));
                    room.appendChild(roomNumber);


                TransformerFactory transformerFactory = TransformerFactory.newInstance();
                Transformer transformer = transformerFactory.newTransformer();
                DOMSource source = new DOMSource(doc);
                StreamResult result = new StreamResult(new File("bookings.xml"));
                transformer.transform(source, result);
                }

            }
            catch (ParserConfigurationException e)
            {
                System.out.println("parser exception");
            }
            catch (TransformerConfigurationException e)
            {
                System.out.println("Config Exception");
            }
            catch (TransformerException e)
            {
                System.out.println("Transformer Exception");
            }

        }

    }


package entity;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * A Class that contains Arraylist of booking
 *
 * @author Suhani Pandey
 * @version 1.0
 */

public class BookingList implements Serializable {
    private ArrayList<Booking> bookings;

    /**
     * constructor
     */
    public BookingList() {
        bookings = new ArrayList<>();
    }

    public ArrayList<Booking> getBookings() {
        return bookings;
    }

    /**
     * @return size of the bookings
     */
    public int size() {
        return bookings.size();
    }

    /**
     * @param guest takes in Guest
     * @return guest of indicated index if it exist
     */

    public Booking bookingByGuest(Guest guest) {
        for (int i = 0; i < bookings.size(); i++) {
            if (bookings.get(i).getMainBooker().equals(guest)) {
                return bookings.get(i);
            }
        }
        return null;
    }

    /**
     * A method to add booking
     *
     * @param booking takes in Booking
     */

    public void addBooking(Booking booking) {
        bookings.add(booking);
    }

    /**
     * Adds a list of bookings to the bookinglist
     *
     * @param bookings
     */

    public void addAllBookings(ArrayList<Booking> bookings) {
        for (int i = 0; i < bookings.size(); i++) {
            add(bookings.get(i));
        }

    }

    /**
     * return booking
     */
    public String toString() {
        String returnstr = "";
        for (int i = 0; i < bookings.size(); i++) {
            returnstr += bookings.get(i) + "\n ";
        }
        return returnstr;
    }

    /**
     * @param i takes in int
     * @return booking of certain index if exist else return null
     */

    public Booking get(int i) {
        if (i < bookings.size()) {
            return bookings.get(i);
        } else {
            return null;
        }

    }

    /**
     * A method to add booking
     *
     * @param booking takes in Booking
     */
    public void add(Booking booking) {
        bookings.add(booking);
    }

    /**
     * A method to set booking
     *
     * @param booking takes in Booking
     * @param i takes in an int
     */
    public void set(Booking booking, int i) {
        bookings.set(i, booking);
    }

    /**
     * A method to remove booking
     * @param booking takes booking
     * @return removed booking
     */

    public boolean removeBooking(Booking booking)
    {
      return bookings.remove(booking);
    }

}

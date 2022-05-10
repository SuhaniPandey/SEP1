package entity;

import java.util.ArrayList;

public class BookingList
{
  private ArrayList<Booking> bookings;

  public BookingList(ArrayList<Booking> bookings)
  {
    this.bookings = bookings;
  }
  public int size()
  {
    return bookings.size();
  }
  /*
  public Booking bookingByGuest(Guest guest)
  {
    Booking bk=null;
   if (!(bookings.contains(guest))
    {

    }
  }

   */
  public void addBooking(Booking booking)
  {
     bookings.add(booking);
  }
}

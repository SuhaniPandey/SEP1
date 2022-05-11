package entity;

import java.io.Serializable;
import java.util.ArrayList;

public class BookingList implements Serializable
{
  private ArrayList<Booking> bookings;


  public BookingList()
  {
   bookings=new ArrayList<>();
  }
  public int size()
  {
    return bookings.size();
  }

  public Booking bookingByGuest(Guest guest)
  {
    for (int i = 0; i < bookings.size(); i++)
    {
      if (bookings.get(i).getGuest().equals(guest))
      {
        return bookings.get(i);
      }
    }
    return null;
  }


  public void addBooking(Booking booking)
  {
     bookings.add(booking);
  }
  public String toString()
  {
    String returnstr="";
    for(int i=0;i< bookings.size();i++)
    {
      returnstr+=bookings.get(i)+"\n ";
    }
    return returnstr;
  }

  public Booking get(int i)
  {
    if (i< bookings.size())
    {
      return bookings.get(i);
    }
    else {
      return null;
    }
  }
  public void add(Booking booking)
  {
    bookings.add(booking);
  }
  public void set(Booking booking,int i)
  {
    bookings.set(i,booking);
  }

  public Booking getBooking(int i)
  {
    return bookings.get(i);
  }
}

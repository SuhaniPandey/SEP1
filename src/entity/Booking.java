package entity;

import java.util.Date;

public class Booking
{
  private Guest guest;
  private Room rooms;
  private Date arrival;
  private Date departure;

  public Booking(Guest guest, Room rooms, Date arrival, Date departure)
  {
    this.guest = guest;
    this.rooms = rooms;
    this.arrival = arrival;
    this.departure = departure;
  }

  public Booking setGuest(Guest guest)
  {
    this.guest = guest;
    return this;
  }

  public Booking setRooms(Room rooms)
  {
    this.rooms = rooms;
    return this;
  }

  public Booking setArrival(Date arrival)
  {
    this.arrival = arrival;
    return this;
  }

  public Booking setDeparture(Date departure)
  {
    this.departure = departure;
    return this;
  }

  public Guest getGuest()
  {
    return guest;
  }

  public Room getRooms()
  {
    return rooms;
  }

  public Date getArrival()
  {
    return arrival;
  }

  public Date getDeparture()
  {
    return departure;
  }

  public double checkOutPrice(Room room,Date dateIntervals)
  {
    double daysstayed=0;
    double price=0;
   if (room.getPrice()== rooms.getPrice())
   {
      daysstayed= arrival.compareTo(departure);
      price=room.getPrice()*daysstayed;
   }
   return price;
  }
  /*
  public double discont(double percent)
  {
    return
  }

   */



}

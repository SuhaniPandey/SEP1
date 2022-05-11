package entity;


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

  /**
   * A method to set guest
   * @param guest takes in Guest
   */

  public void setGuest(Guest guest)
  {
    this.guest = guest;
  }

  /**
   * A method to set rooms
   * @param rooms takes in Room
   */

  public void setRooms(Room rooms)
  {
    this.rooms = rooms;
  }

  /**
   * A method to set arrival date takes in Date
   * @param arrival
   */

  public void setArrival(Date arrival)
  {
    this.arrival = arrival;
  }

  /**
   * A method to set departure date
   * @param departure
   */

  public void setDeparture(Date departure)
  {
    this.departure = departure;
  }

  /**
   * A method to get guest
   * @return guest
   */
  public Guest getGuest()
  {
    return guest;
  }

  /**
   * A method to get rooms
   * @return rooms
   */

  public Room getRooms()
  {
    return rooms;
  }

  /**
   * A method to get arrival
   * @return arrival
   */

  public Date getArrival()
  {
    return arrival;
  }

  /**
   * A method to get departure
   * @return departure
   */

  public Date getDeparture()
  {
    return departure;
  }

  /**
   * A method to get final check out price in certain date interval
   * @return price
   */

  public double checkOutPrice()
  {
    int daysstayed = getArrival().dateInterval();
    double price=0;
    if (getRooms().getPrice()== rooms.getPrice())
    {
      price=getRooms().getPrice()*daysstayed;
    }
    return price;
  }

  /**
   * A method to get discount
   * @param percent
   * @return final price after discount
   */
  public double discont(double percent)
  {
    return rooms.getPrice()-(rooms.getPrice()*0.5);
  }

  /**
   *
   * @return guest,rooms,arrival,departure
   */

   public String toString()
  {
    return "Booking{" + "guest=" + guest + ", rooms=" + rooms + ", arrival="
        + arrival + ", departure=" + departure + '}';
  }

  /**
   * A method to check if a given object is an instance of a Booking object
   * If obj is an instance of Booking,turn Object obj into a Booking object
   * @param obj obj a given object
   * @return true if the obj is an instance of Booking, else return false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Booking))
    {
      return false;
    }
    Booking other=(Booking) obj;
    return guest.equals(other.guest) && rooms.equals(other.rooms)
        && arrival.equals(other.arrival) && departure.equals(other.departure);
  }

  public String getPhoneNumber()
  {
    return guest.getPhoneNumber();
  }
}

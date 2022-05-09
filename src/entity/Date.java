package entity;
import java.time.Clock;
import java.time.LocalDate;

public class Date
{
  private int day;
  private int month;
  private int year;
  private int hour;

  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setMonth(int month)
  {
    this.month = month;
  }
  public void setDay(int day)
  {
    this.day = day;
  }
  public int getYear()
  {
    return year;
  }
  public int getMonth()
  {
    return month;
  }
  public int getDay()
  {
    return day;
  }
  public Date getLocalDate()
  {
    LocalDate currentDate = LocalDate.now();
    int currentDay = currentDate.getDayOfMonth();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    return new Date(currentDay, currentMonth, currentYear);
  }
  public boolean isLeapYear()
  {
    if ((year % 4 == 0 && year % 100 != 0) || (year % 4 == 0
        && year % 400 == 0))
    {
      return true;
    }
    else
    {
      return false;
    }
  }
  public int daysInMonth()
  {
    if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8
        || month == 10 || month == 12)
      return 31;

    else if (month == 4 || month == 6 || month == 9 || month == 11)
      return 30;

    else if (month == 2)
    {
      if (isLeapYear())
        return 29;

      else
        return 28;
    }

    else
      return -1;
  }
  public int dateInterval(Date checkIn, Date checkOut)
  {
    int dayInterval1 = 0;
    int dayInterval2 = 0;

    dayInterval1 += checkIn.getDay();
    dayInterval1 += checkIn.daysInMonth();

    dayInterval2 +=  checkOut.getDay();
    dayInterval2 += checkOut.daysInMonth();

    return dayInterval2 - dayInterval1;
  }
  public String toString()
  {
    return day + " " + month + " " + year;
  }
  public Date copy()
  {
    return new Date(getDay(), getMonth(), getYear());
  }
  public boolean equals(Object obj)
  {
    if (obj instanceof Date)
    {
      Date obj1 = (Date) obj;
      return true;
    } else
      return false;
  }


}

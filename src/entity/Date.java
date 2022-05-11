package entity;
import java.time.Clock;
import java.time.LocalDate;

/**
 * A Class that contains the day, month and year
 * @author Devlin Onichuk
 */
public class Date
{
  private int day;
  private int month;
  private int year;
  private int hour;

  /**
   * A three argument constructor to set the Date
   * @param day takes in a given integer
   * @param month takes in a given integer
   * @param year takes in a given integer
   */
  public Date(int day, int month, int year)
  {
    this.day = day;
    this.month = month;
    this.year = year;
  }

  /**
   * A method to set the year
   * @param year takes in an integer
   */
  public void setYear(int year)
  {
    this.year = year;
  }

  /**
   * A Method to set the month
   * @param month takes in an integer
   */
  public void setMonth(int month)
  {
    this.month = month;
  }

  /**
   * A Method to set the day
   * @param day takes in an integer
   */
  public void setDay(int day)
  {
    this.day = day;
  }

  /**
   * A method get the year
   * @return the set year
   */
  public int getYear()
  {
    return year;
  }

  /**
   * A method to get the month
   * @return the set month
   */
  public int getMonth()
  {
    return month;
  }

  /**
   * A method to return the day
   * @return the set day
   */
  public int getDay()
  {
    return day;
  }
  /**
   * A boolean method to check if a date is a leap year
   * @return true if it is a leap year, otherwise false
   */
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

  /**
   * A method to get the days in a given month
   * gets the proper days in february if it is a leap year
   * @return the days in the month
   */
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
  public static Date today()
  {
    LocalDate currentDate = LocalDate.now();
    int currentDay = currentDate.getDayOfMonth();
    int currentMonth = currentDate.getMonthValue();
    int currentYear = currentDate.getYear();
    return new Date(currentDay, currentMonth, currentYear);
  }

  /**
   * A method that takes in two date objects and returns the difference in days
   * @return
   */
  public int dateInterval()
  {
    Date currentDate = Date.today();

    int interval1 = this.getDay() + this.daysInMonth();
    int interval2 = currentDate.getDay() + currentDate.daysInMonth();

    return interval2 - interval1;
  }

  /**
   * A method to return the Date in day, month, year order
   * @return
   */
  public String toString()
  {
    return day + ", " + month + ", " + year;
  }

  /**
   * A method to copy a date object
   * @return a copy of a date object
   */
  public Date copy()
  {
    return new Date(getDay(), getMonth(), getYear());
  }

  /**
   * A method to check if a given object is an instance of a Date object
   * If obj is an instance of Date, turn Object obj into a Date object
   * @param obj a given object
   * @return true if the obj is an instance of Date, else return false
   */
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

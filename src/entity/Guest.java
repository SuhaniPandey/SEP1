package entity;

import java.io.Serializable;

public class Guest implements Serializable
{
  private String firstName;
  private String lastName;
  private String phoneNumber;
  private String nationality;
  private String address;
  private Date dateOfBirth;

  /**
   *
   * @param firstName which refers to firstname of the guest
   * @param lastName which refers to lastname of the guest
   * @param phoneNumber which refers to phonenumber of the guest
   * @param nationality which refers to nationality of the guest
   * @param address which refers to address of the guest
   * @param dateOfBirth which refers to date of birth of the guest
   */
  public Guest(String firstName, String lastName, String phoneNumber,
      String nationality, String address, Date dateOfBirth)
  {
    this.firstName = firstName;
    this.lastName = lastName;
    this.phoneNumber = phoneNumber;
    this.nationality = nationality;
    this.address = address;
    this.dateOfBirth=dateOfBirth;
  }

  /**
   * method to get date of birth
   * @return date of birth
   */

  public Date getDateOfBirth()
  {
    return dateOfBirth;
  }

  /**
   * method to set date of birth
   * @param dateOfBirth takes in an Date
   */
  public void setDateOfBirth(Date dateOfBirth)
  {
    this.dateOfBirth = dateOfBirth;
  }

  /**
   * A method to get firstname
   * @return the set firstname
   */

  public String getFirstName()
  {
    return firstName;
  }

  /**
   *  A method to set first name
   * @param firstName  takes in an String
   */

  public void setFirstName(String firstName)
  {
    this.firstName = firstName;
  }

  /**
   * A method to get LastName
   * @return lastname
   */

  public String getLastName()
  {
    return lastName;
  }

  /**
   * A method to set the lastname
   * @param lastName takes in String
   */

  public void setLastName(String lastName)
  {
    this.lastName = lastName;
  }

  /**
   * A method to get phonenumber
   * @return phonenumber
   */

  public String getPhoneNumber()
  {
    return phoneNumber;
  }

  /**
   * A method to set phone number
   * @param phoneNumber takes in an String
   */

  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }

  /**
   * A method to get nationality
   * @return nationality
   */

  public String getNationality()
  {
    return nationality;
  }

  /**
   * A method to set nationality
   * @param nationality takes in an String
   */

  public void setNationality(String nationality)
  {
    this.nationality = nationality;
  }

  /**
   * A method to get address
   * @return address
   */

  public String getAddress()
  {
    return address;
  }

  /**
   * A method to set address
   * @param address takes in String
   */

  public void setAddress(String address)
  {
    this.address = address;
  }

  /**
   *
   * @return firstname,lastname,phone number,nationality and date of birth
   */

  public String toString()
  {
    return "FirstName: "+firstName+" Lastname: "+lastName+"PhoneNumber: "+phoneNumber
        +"Nationality: "+nationality+"Date of birth: "+dateOfBirth;
  }

  /**
   * A method to check if a given object is an instance of a Guest object
   * If obj is an instance of Guest,turn Object obj into a Guest object
   * @param obj a given object
   * @return true if the obj is an instance of Guest, else return false
   */
  public boolean equals(Object obj)
  {
    if (!(obj instanceof Guest))
    {
      return false;
    }
    Guest other=(Guest) obj;
    return firstName== other.firstName &&
        lastName== other.lastName &&
        phoneNumber== other.phoneNumber &&
        nationality== other.nationality &&
        dateOfBirth==other.dateOfBirth;
  }
}

package entity;

import java.time.LocalDate;

public class Guest
{
  private String firsName;
  private String lastName;
  private LocalDate dateOfBirth;

  public Guest(String firsName, String lastName, LocalDate dateOfBirth)
  {
    this.firsName = firsName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
  }

  public String getFirsName()
  {
    return firsName;
  }

  public void setFirsName(String firsName)
  {
    this.firsName = firsName;
  }
}

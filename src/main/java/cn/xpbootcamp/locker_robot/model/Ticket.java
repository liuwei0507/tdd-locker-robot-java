package cn.xpbootcamp.locker_robot.model;

public class Ticket {

  private String lockerSerialNumber;
  private Integer number;

  public Ticket(Integer number) {
    this.number = number;
  }

  public Ticket(String lockerSerialNumber, Integer number) {
    this.lockerSerialNumber = lockerSerialNumber;
    this.number = number;
  }

  public Integer getNumber() {
    return number;
  }

  public String getLockerSerialNumber() {
    return lockerSerialNumber;
  }
}

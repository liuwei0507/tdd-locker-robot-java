package cn.xpbootcamp.locker_robot.model;

public class Ticket {

  private String lockerSerialNumber;
  private Integer lockerNumber;

  public Ticket(Integer lockerNumber) {
    this.lockerNumber = lockerNumber;
  }

  public Ticket(String lockerSerialNumber, Integer lockerNumber) {
    this.lockerSerialNumber = lockerSerialNumber;
    this.lockerNumber = lockerNumber;
  }

  public Integer getLockerNumber() {
    return lockerNumber;
  }

  public String getLockerSerialNumber() {
    return lockerSerialNumber;
  }
}

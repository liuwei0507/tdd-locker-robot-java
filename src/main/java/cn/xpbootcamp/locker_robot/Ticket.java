package cn.xpbootcamp.locker_robot;

public class Ticket {
    private Integer lockerNumber;

    public Ticket(Integer lockerNumber) {
        this.lockerNumber = lockerNumber;
    }

    public Integer getLockerNumber() {
        return lockerNumber;
    }

    public void setLockerNumber(Integer lockerNumber) {
        this.lockerNumber = lockerNumber;
    }
}

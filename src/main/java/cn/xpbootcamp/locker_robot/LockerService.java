package cn.xpbootcamp.locker_robot;

import java.util.Arrays;
import java.util.List;

public class LockerService {
    public static final List LOCKERS = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);

    public Ticket getTicket(List usedLockers) {
        for (int i = 0; i < LOCKERS.size(); i++) {
            Integer locker = (Integer) LOCKERS.get(i);
            if (!usedLockers.contains(locker)) {
                return new Ticket(locker);
            }
        }
        return null;
    }

    public List storePackage(List<Integer> usedLockers, Ticket ticket) {
        usedLockers.add(ticket.getLockerNumber());
        return usedLockers;
    }
}

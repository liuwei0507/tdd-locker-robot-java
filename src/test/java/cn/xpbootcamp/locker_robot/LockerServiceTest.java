package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LockerServiceTest {
    LockerService lockerService = new LockerService();

    @Test
    public void should_return_ticket_with_locker_number_4_when_get_ticket_given_already_used_locker_number_1_3() {
        // given
        List usedLockers = Arrays.asList(1,2,3);

        // when
        Ticket ticket = lockerService.getTicket(usedLockers);

        // then
        Assertions.assertEquals(ticket.getLockerNumber().intValue(), 4);
    }

    @Test
    public void should_return_null_when_get_ticket_given_already_used_locker_1_9() {
        // given
        List usedLockers = Arrays.asList(1,2,3,4,5,6,7,8,9);

        // when
        Ticket ticket = lockerService.getTicket(usedLockers);

        // then
        Assertions.assertEquals(ticket, null);
    }


    @Test
    public void should_return_used_locker_number_1_4_when_store_package_given_ticket_locker_number_4() {
        // given
        List usedLockers = new ArrayList();
        usedLockers.add(1);
        usedLockers.add(2);
        usedLockers.add(3);
        Ticket ticket = new Ticket(4);

        // when
        usedLockers = lockerService.storePackage(usedLockers, ticket);

        // then
        Assertions.assertEquals(usedLockers.get(3), 4);
    }

}

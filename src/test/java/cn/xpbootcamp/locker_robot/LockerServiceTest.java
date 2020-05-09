package cn.xpbootcamp.locker_robot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class LockerServiceTest {
    LockerService lockerService = new LockerService();

    @Test
    public void should_return_ticket_with_locker_number_4_when_store_package_given_already_used_locker_number_1_3() {
        // given
        List usedLockers = Arrays.asList(1,2,3);

        // when
        Ticket ticket = lockerService.getTicket(usedLockers);

        // then
        Assertions.assertEquals(ticket.getLockerNumber().intValue(), 4);
    }
}

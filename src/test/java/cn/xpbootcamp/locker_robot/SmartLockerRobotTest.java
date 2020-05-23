package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.STORE_SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class SmartLockerRobotTest {
    @Test
    void should_return_ticket_when_store_bag_given_smart_locker_robot_and_available_space() {
        // given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

        // when
        ResultDto<Ticket> actualResult = robot.store(bag);

        // then
        Ticket actualTicket = actualResult.getData();
        String actualMessage = actualResult.getMessage();

        assertNotNull(actualTicket);
        assertEquals(STORE_SUCCESS_MESSAGE, actualMessage);
    }
}

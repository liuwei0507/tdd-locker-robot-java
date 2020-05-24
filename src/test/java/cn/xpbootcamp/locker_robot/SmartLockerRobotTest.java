package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.STORE_SUCCESS_MESSAGE;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.TAKE_SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    void should_return_locker_is_full_message_when_store_bag_given_primary_locker_robot_and_no_space() {
        // Given
        Bag bag = new Bag();

        Locker firstLocker = new Locker(1);
        firstLocker.store(new Bag());
        Locker secondLocker = new Locker(1);
        secondLocker.store(new Bag());

        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

        // When
        ResultDto<Ticket> actualResult = robot.store(bag);

        // Then
        Ticket actualTicket = actualResult.getData();
        String actualMessage = actualResult.getMessage();

        assertNull(actualTicket);
        assertEquals(LOCKER_FULL, actualMessage);
    }

    @Test
    void should_store_in_locker_with_most_available_capacity_when_store_bag_given_smart_locker_robot_and_lockers_with_different_available_space() {
        // given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(5);
        Locker thirdLocker = new Locker(4);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker, thirdLocker));

        // when
        ResultDto<Ticket> storeResult = robot.store(bag);

        // then
        ResultDto<Bag> actualResult = secondLocker.take(storeResult.getData());
        Bag actualBag = actualResult.getData();

        assertEquals(actualBag, bag);
    }

    @Test
    void refactor_should_store_in_locker_with_most_available_capacity_when_store_bag_given_smart_locker_robot_and_lockers_with_different_available_space() {
        // given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(5);
        secondLocker.store(new Bag());
        secondLocker.store(new Bag());
        Locker thirdLocker = new Locker(4);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker, thirdLocker));

        // when
        ResultDto<Ticket> storeResult = robot.store(bag);

        // then
        ResultDto<Bag> actualResult = thirdLocker.take(storeResult.getData());
        Bag actualBag = actualResult.getData();

        assertEquals(actualBag, bag);
    }

    @Test
    void should_store_in_any_of_the_locker_when_store_bag_given_smart_locker_robot_and_lockers_with_the_same_available_space() {
        // given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(3);
        Locker thirdLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker, thirdLocker));

        // when
        ResultDto<Ticket> storeResult = robot.store(bag);

        // then
        ResultDto<Bag> actualResult = firstLocker.take(storeResult.getData());
        Bag actualBag = actualResult.getData();

        assertEquals(actualBag, bag);
    }

    @Test
    void should_return_right_bag_when_take_bag_given_valid_ticket() {
        // Given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

        ResultDto<Ticket> storeResult = robot.store(bag);

        // When
        ResultDto<Bag> actualResult = robot.take(storeResult.getData());

        // Then
        Bag actualBag = actualResult.getData();
        String actualMessage = actualResult.getMessage();

        assertEquals(bag, actualBag);
        assertEquals(TAKE_SUCCESS_MESSAGE, actualMessage);
    }

    @Test
    void should_return_invalid_ticket_message_when_take_bag_given_invalid_ticket() {
        // Given
        Bag bag = new Bag();
        Locker firstLocker = new Locker(3);
        Locker secondLocker = new Locker(3);
        SmartLockerRobot robot = new SmartLockerRobot();
        robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

        ResultDto<Ticket> storeResult = robot.store(bag);
        robot.take(storeResult.getData());

        // When
        ResultDto<Bag> actualResult = robot.take(storeResult.getData());

        // Then
        Bag actualBag = actualResult.getData();
        String actualMessage = actualResult.getMessage();

        assertNull(actualBag);
        assertEquals(INVALID_TICKET, actualMessage);
    }


}

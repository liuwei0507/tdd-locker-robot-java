package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.STORE_SUCCESS_MESSAGE;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.TAKE_SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class PrimaryLockerRobotTest {

  @Test
  void should_return_ticket_when_store_bag_given_primary_locker_robot_and_available_space() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);
    PrimaryLockerRobot robot = new PrimaryLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    // When
    ResultDto<Ticket> actual = robot.store(bag);

    // Then
    assertNotNull(actual.getData());
    assertEquals(actual.getMessage(), STORE_SUCCESS_MESSAGE);
  }

  @Test
  void should_return_locker_is_full_message_when_store_bag_given_primary_locker_robot_and_no_space() {
    // Given
    Bag bag = new Bag();

    Locker firstLocker = new Locker(1);
    firstLocker.store(new Bag());
    Locker secondLocker = new Locker(1);
    secondLocker.store(new Bag());

    PrimaryLockerRobot robot = new PrimaryLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    // When
    ResultDto<Ticket> actual = robot.store(bag);

    // Then
    assertNull(actual.getData());
    assertEquals(actual.getMessage(), LOCKER_FULL);
  }

  @Test
  void should_return_right_bag_when_take_bag_given_valid_ticket() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3, "A1");
    Locker secondLocker = new Locker(3, "A2");
    PrimaryLockerRobot robot = new PrimaryLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    ResultDto<Ticket> storeResult = robot.store(bag);

    // When
    ResultDto<Bag> actual = robot.take(storeResult.getData());

    // Then
    assertNotNull(actual.getData());
    assertEquals(actual.getMessage(), TAKE_SUCCESS_MESSAGE);
  }

  @Test
  void should_return_invalid_ticket_message_when_take_bag_given_invalid_ticket() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3, "A1");
    Locker secondLocker = new Locker(3, "A2");
    PrimaryLockerRobot robot = new PrimaryLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    ResultDto<Ticket> storeResult = robot.store(bag);
    robot.take(storeResult.getData());

    // When
    ResultDto<Bag> actual = robot.take(storeResult.getData());

    // Then
    assertNull(actual.getData());
    assertEquals(actual.getMessage(), INVALID_TICKET);
  }

  @Test
  void should_store_in_first_locker_and_return_ticket_when_store_bag_given_first_locker_available() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3, "A1");
    firstLocker.store(new Bag());
    firstLocker.store(new Bag());
    Locker secondLocker = new Locker(3, "A2");
    PrimaryLockerRobot robot = new PrimaryLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    int previousUsedCapacity = firstLocker.getUsedCapacity();

    // When
    ResultDto<Ticket> actual = robot.store(bag);

    // Then
    assertNotNull(actual.getData());
    assertEquals(previousUsedCapacity + 1, firstLocker.getUsedCapacity());
  }
}

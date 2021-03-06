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

public class SuperLockerRobotTest {

  @Test
  void should_return_ticket_when_store_bag_given_super_locker_robot_and_available_space() {
    // given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);
    SuperLockerRobot robot = new SuperLockerRobot();
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
  void should_return_locker_is_full_message_when_store_bag_given_super_locker_robot_and_no_space() {
    // Given
    Bag bag = new Bag();

    Locker firstLocker = new Locker(1);
    firstLocker.store(new Bag());
    Locker secondLocker = new Locker(1);
    secondLocker.store(new Bag());

    SuperLockerRobot robot = new SuperLockerRobot();
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
  void should_store_in_locker_with_max_vacancy_ratio_and_return_ticket_when_store_bag_given_two_lockers_with_different_vacancy_ratio() {
    // Given
    Bag bag = new Bag();

    Locker firstLocker = new Locker(5);
    firstLocker.store(new Bag());
    Locker secondLocker = new Locker(3);
    secondLocker.store(new Bag());

    SuperLockerRobot robot = new SuperLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    // When
    ResultDto<Ticket> storeResult = robot.store(bag);

    // Then
    ResultDto<Bag> actualResult = firstLocker.take(storeResult.getData());
    Bag actualBag = actualResult.getData();

    assertEquals(bag, actualBag);
  }

  @Test
  void should_store_in_any_locker_and_return_ticket_when_store_bag_given_two_lockers_with_same_vacancy_ratio() {
    // Given
    Bag bag = new Bag();

    Locker firstLocker = new Locker(5);
    firstLocker.store(new Bag());
    Locker secondLocker = new Locker(5);
    secondLocker.store(new Bag());

    SuperLockerRobot robot = new SuperLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));

    // When
    ResultDto<Ticket> storeResult = robot.store(bag);

    // Then
    ResultDto<Bag> actualResult = firstLocker.take(storeResult.getData());
    Bag actualBag = actualResult.getData();

    assertEquals(bag, actualBag);
  }

  @Test
  void should_return_right_bag_when_take_bag_given_super_robot_and_valid_ticket() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);

    SuperLockerRobot robot = new SuperLockerRobot();
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
  void should_return_invalid_ticket_message_when_take_bag_given_super_robot_and_fake_ticket() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);

    SuperLockerRobot robot = new SuperLockerRobot();
    robot.setOrderedLocker(Arrays.asList(firstLocker, secondLocker));
    robot.store(bag);

    Ticket fakeTicket = new Ticket();

    // When
    ResultDto<Bag> actualResult = robot.take(fakeTicket);

    // Then
    Bag actualBag = actualResult.getData();
    String actualMessage = actualResult.getMessage();

    assertNull(actualBag);
    assertEquals(INVALID_TICKET, actualMessage);
  }

  @Test
  void should_return_invalid_ticket_message_when_take_bag_given_super_robot_and_used_ticket() {
    // Given
    Bag bag = new Bag();
    Locker firstLocker = new Locker(3);
    Locker secondLocker = new Locker(3);

    SuperLockerRobot robot = new SuperLockerRobot();
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

package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.STORE_SUCCESS_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
}

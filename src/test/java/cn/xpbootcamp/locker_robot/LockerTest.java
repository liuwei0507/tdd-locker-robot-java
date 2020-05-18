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
import org.junit.jupiter.api.Test;

class LockerTest {

  @Test
  void should_return_ticket_when_store_bag_given_available_space_and_bag() {
    // Given
    Bag bag = new Bag();
    Locker locker = new Locker(3);

    // When
    ResultDto<Ticket> actualResult = locker.store(bag);

    // Then
    Ticket actualTicket = actualResult.getData();
    String actualMessage = actualResult.getMessage();

    assertNotNull(actualTicket);
    assertEquals(STORE_SUCCESS_MESSAGE, actualMessage);
  }

  @Test
  void should_failed_when_store_bag_given_no_space_and_bag() {
    // Given
    Bag bag = new Bag();
    Locker locker = new Locker(0);

    // When
    ResultDto<Ticket> actualResult = locker.store(bag);

    // Then
    Ticket actualTicket = actualResult.getData();
    String actualMessage = actualResult.getMessage();

    assertNull(actualTicket);
    assertEquals(LOCKER_FULL, actualMessage);
  }

  @Test
  void should_return_bag_when_take_bag_given_ticket() {
    // Given
    Bag bag = new Bag();
    Locker locker = new Locker(2);
    ResultDto<Ticket> result = locker.store(bag);

    // When
    ResultDto<Bag> actualResult = locker.take(result.getData());

    // Then
    Bag actualBag = actualResult.getData();
    String actualMessage = actualResult.getMessage();

    assertEquals(bag, actualBag);
    assertEquals(TAKE_SUCCESS_MESSAGE, actualMessage);
  }

  @Test
  void should_failed_when_take_bag_given_invalid_ticket() {
    // Given
    Locker locker = new Locker(2);
    Ticket invalidTicket = new Ticket();

    // When
    ResultDto<Bag> actualResult = locker.take(invalidTicket);

    // Then
    Bag actualBag = actualResult.getData();
    String actualMessage = actualResult.getMessage();

    assertNull(actualBag);
    assertEquals(INVALID_TICKET, actualMessage);
  }
}

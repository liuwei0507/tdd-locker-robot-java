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

class LockerServiceTest {

  @Test
  void should_return_ticket_when_store_bag_given_available_space_and_bag() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(3);
    ResultDto<Ticket> result = lockerService.store(bag);
    assertNotNull(result.getData());
    assertEquals(result.getMessage(), STORE_SUCCESS_MESSAGE);
  }

  @Test
  void should_failed_when_store_bag_given_no_space_and_bag() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(0);
    ResultDto<Ticket> result = lockerService.store(bag);
    assertNull(result.getData());
    assertEquals(result.getMessage(), LOCKER_FULL);
  }

  @Test
  void should_return_bag_when_take_bag_given_ticket() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(2);
    ResultDto<Ticket> result = lockerService.store(bag);

    ResultDto<Bag> takeResult = lockerService.take(result.getData());
    assertNotNull(takeResult.getData());
    assertEquals(takeResult.getData(), bag);
    assertEquals(takeResult.getMessage(), TAKE_SUCCESS_MESSAGE);
  }

  @Test
  void should_failed_when_take_bag_given_invalid_ticket() {
    LockerService lockerService = new LockerService(2);
    Ticket invalidTicket = new Ticket(10);

    ResultDto<Bag> takeResult = lockerService.take(invalidTicket);
    assertNull(takeResult.getData());
    assertEquals(takeResult.getMessage(), INVALID_TICKET);
  }
}

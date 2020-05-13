package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.CommonConstant;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class LockerServiceTest {

  @Test
  void should_return_ticket_when_store_bag_given_available_space_and_bag() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(3);
    ResultDto<Ticket> result = lockerService.store(bag);
    Assertions.assertNotNull(result.getData());
    Assertions.assertEquals(result.getMessage(), CommonConstant.STORE_SUCCESS_MESSAGE);
  }

  @Test
  void should_failed_when_store_bag_given_no_space_and_bag() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(0);
    ResultDto<Ticket> result = lockerService.store(bag);
    Assertions.assertNull(result.getData());
    Assertions.assertEquals(result.getMessage(), CommonConstant.STORE_FAILED_MESSAGE);
  }

  @Test
  void should_return_bag_when_take_bag_given_ticket() {
    Bag bag = new Bag();
    LockerService lockerService = new LockerService(2);
    ResultDto<Ticket> result = lockerService.store(bag);

    ResultDto<Bag> takeResult = lockerService.take(result.getData());
    Assertions.assertNotNull(takeResult.getData());
    Assertions.assertEquals(takeResult.getMessage(), CommonConstant.TAKE_SUCCESS_MESSAGE);
  }
}

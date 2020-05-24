package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static java.util.Objects.nonNull;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;

public class PrimaryLockerRobot extends LockerRobot{

  @Override
  public ResultDto<Ticket> store(Bag bag) {
    for (Locker locker : super.getOrderedLocker()) {
      ResultDto<Ticket> storeResult = locker.store(bag);
      if (nonNull(storeResult.getData())) {
        return storeResult;
      }
    }
    return new ResultDto<>(null, LOCKER_FULL);
  }
}

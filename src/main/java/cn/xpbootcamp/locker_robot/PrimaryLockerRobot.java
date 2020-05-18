package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static java.util.Objects.isNull;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.List;

public class PrimaryLockerRobot {

  private List<Locker> orderedLocker;

  public void setOrderedLocker(List<Locker> orderedLocker) {
    this.orderedLocker = orderedLocker;
  }

  public ResultDto<Ticket> store(Bag bag) {
    Locker targetLocker = orderedLocker.stream()
        .filter(locker -> !locker.isFull()).findFirst()
        .orElse(null);

    if (isNull(targetLocker)) {
      return new ResultDto<>(null, LOCKER_FULL);
    }

    return targetLocker.store(bag);
  }

  public ResultDto<Bag> take(Ticket ticket) {
    return orderedLocker.stream()
        .map(locker -> locker.take(ticket))
        .filter(result -> !isNull(result.getData()))
        .findFirst()
        .orElse(new ResultDto<>(null, INVALID_TICKET));
  }
}

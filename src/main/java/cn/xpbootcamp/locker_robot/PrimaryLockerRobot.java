package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.List;
import java.util.Objects;

public class PrimaryLockerRobot {

  private List<Locker> orderedLocker;

  public void setOrderedLocker(List<Locker> orderedLocker) {
    this.orderedLocker = orderedLocker;
  }

  public ResultDto<Ticket> store(Bag bag) {
    for (Locker locker : orderedLocker) {
      ResultDto<Ticket> storeResult = locker.store(bag);
      if (nonNull(storeResult.getData())) {
        return storeResult;
      }
    }
    return new ResultDto<>(null, LOCKER_FULL);
  }

  public ResultDto<Bag> take(Ticket ticket) {
    return orderedLocker.stream()
        .map(locker -> locker.take(ticket))
        .filter(result -> !isNull(result.getData()))
        .findFirst()
        .orElse(new ResultDto<>(null, INVALID_TICKET));
  }
}

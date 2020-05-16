package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;

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
    Locker targetLocker = orderedLocker.stream()
        .filter(locker -> !locker.isFull()).findFirst()
        .orElse(null);

    if (Objects.isNull(targetLocker)) {
      return new ResultDto<>(null, LOCKER_FULL);
    }

    return targetLocker.store(bag);
  }
}

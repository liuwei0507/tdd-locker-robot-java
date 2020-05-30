package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static java.util.Comparator.comparing;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.Optional;

public class SuperLockerRobot extends LockerRobot {

  @Override
  public ResultDto<Ticket> store(Bag bag) {
    Optional<Locker> optionalLocker = getOrderedLocker().stream()
        .filter(locker -> locker.getAvailableCapacity() > 0)
        .max(comparing(this::calculateVacancyRatio));

    if (optionalLocker.isPresent()) {
      return optionalLocker.get().store(bag);
    }

    return new ResultDto<>(null, LOCKER_FULL);
  }

  private double calculateVacancyRatio(Locker locker) {
    return locker.getAvailableCapacity() * 1.0 / locker.getCapacity();
  }
}

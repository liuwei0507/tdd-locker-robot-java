package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.Comparator;
import java.util.Optional;

public class SmartLockerRobot extends PrimaryLockerRobot{
    @Override
    public ResultDto<Ticket> store(Bag bag) {
        Optional<Locker> optionalLocker = getOrderedLocker().stream()
            .filter(locker -> locker.getAvailableCapacity() > 0)
            .max(Comparator.comparing(Locker::getAvailableCapacity));

        if (optionalLocker.isPresent()) {
            return optionalLocker.get().store(bag);
        }

        return new ResultDto<>(null, LOCKER_FULL);
    }
}

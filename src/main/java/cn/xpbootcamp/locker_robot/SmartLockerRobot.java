package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static java.util.Objects.nonNull;

public class SmartLockerRobot extends PrimaryLockerRobot{
    @Override
    public ResultDto<Ticket> store(Bag bag) {
        List<Locker> orderedLockers = getOrderedLocker().stream()
                .sorted(Comparator.comparing(Locker::getCapacity).reversed())
                .collect(Collectors.toList());
        for (Locker locker : orderedLockers) {
            ResultDto<Ticket> storeResult = locker.store(bag);
            if (nonNull(storeResult.getData())) {
                return storeResult;
            }
        }
        return new ResultDto<>(null, LOCKER_FULL);
    }
}

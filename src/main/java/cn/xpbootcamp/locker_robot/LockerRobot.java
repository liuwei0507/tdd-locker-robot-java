package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;

import java.util.List;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static java.util.Objects.isNull;

public abstract class LockerRobot {

    private List<Locker> orderedLocker;

    public void setOrderedLocker(List<Locker> orderedLocker) {
        this.orderedLocker = orderedLocker;
    }

    public List<Locker> getOrderedLocker() {
        return orderedLocker;
    }

    public abstract ResultDto<Ticket> store(Bag bag);

    public ResultDto<Bag> take(Ticket ticket) {
        return orderedLocker.stream()
                .map(locker -> locker.take(ticket))
                .filter(result -> !isNull(result.getData()))
                .findFirst()
                .orElse(new ResultDto<>(null, INVALID_TICKET));
    }
}

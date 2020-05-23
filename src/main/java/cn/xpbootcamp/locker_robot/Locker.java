package cn.xpbootcamp.locker_robot;

import static cn.xpbootcamp.locker_robot.commom.CommonConstant.INVALID_TICKET;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.LOCKER_FULL;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.STORE_SUCCESS_MESSAGE;
import static cn.xpbootcamp.locker_robot.commom.CommonConstant.TAKE_SUCCESS_MESSAGE;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.ResultDto;
import cn.xpbootcamp.locker_robot.model.Ticket;
import java.util.HashMap;
import java.util.Map;

public class Locker {

  private Map<Ticket, Object> storeMap;
  private int capacity;

  public int getAvailableCapacity() {
    return capacity - storeMap.size();
  }

  public Locker(int capacity) {
    this.capacity = capacity;
    this.storeMap = new HashMap<>(capacity);
  }

  public ResultDto<Ticket> store(Bag bag) {
    Ticket ticket = getTicket();
    if (ticket == null) {
      return new ResultDto<>(null, LOCKER_FULL);
    }
    storeMap.put(ticket, bag);
    return new ResultDto<>(ticket, STORE_SUCCESS_MESSAGE);
  }

  private Ticket getTicket() {
    if (storeMap.size() == capacity) {
      return null;
    }
    return new Ticket();
  }

  public ResultDto<Bag> take(Ticket ticket) {
    Bag bag;
    if (!storeMap.containsKey(ticket)) {
      return new ResultDto<>(null, INVALID_TICKET);
    }

    bag = (Bag) storeMap.get(ticket);
    storeMap.remove(ticket);

    return new ResultDto<>(bag, TAKE_SUCCESS_MESSAGE);
  }
}

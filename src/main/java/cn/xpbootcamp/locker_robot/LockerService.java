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

public class LockerService {

  private Map<String, Object> storeMap;
  private int number;

  public LockerService(int number) {
    this.number = number;
    this.storeMap = new HashMap<>(number);
  }


  public ResultDto<Ticket> store(Bag bag) {
    Ticket ticket = getTicket();
    if (ticket == null) {
      return new ResultDto<>(null, LOCKER_FULL);
    }
    storeMap.put(ticket.getLockerNumber().toString(), bag);
    return new ResultDto<>(ticket, STORE_SUCCESS_MESSAGE);
  }

  private Ticket getTicket() {
    if (storeMap.size() == number) {
      return null;
    }

    int index = 0;
    for (int i = 0; i < number; i++) {
      if (storeMap.get(i) == null) {
        index = i;
        break;
      }
    }
    return new Ticket(index);
  }

  public ResultDto<Bag> take(Ticket ticket) {
    String lockerIndex = ticket.getLockerNumber() + "";
    Bag bag;
    if (!storeMap.containsKey(lockerIndex)) {
      return new ResultDto<>(null, INVALID_TICKET);
    }

    bag = (Bag) storeMap.get(lockerIndex);
    storeMap.put(lockerIndex, null);
    return new ResultDto<>(bag, TAKE_SUCCESS_MESSAGE);
  }
}

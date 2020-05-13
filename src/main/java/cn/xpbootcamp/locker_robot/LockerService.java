package cn.xpbootcamp.locker_robot;

import cn.xpbootcamp.locker_robot.model.Bag;
import cn.xpbootcamp.locker_robot.model.CommonConstant;
import cn.xpbootcamp.locker_robot.model.ResultDto;
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
    storeMap.put(ticket.getLockerNumber().toString(), bag);
    return new ResultDto<>(ticket, CommonConstant.STORE_SUCCESS_MESSAGE);
  }

  private Ticket getTicket() {
    int index = 0;
    for (int i = 0; i < number; i++) {
      if (storeMap.get(i) == null) {
        index = i;
        break;
      }
    }
    return new Ticket(index);
  }
}

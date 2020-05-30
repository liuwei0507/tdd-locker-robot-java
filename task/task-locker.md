# 需求分析
存包：系统随机分配（存在有空的位置），返回ticket

取包：携带ticket，系统根据ticket信息取包

# Tasking
### store
- `Given` a bag and available space `When` store bag `Then` store successfully and return ticket
- `Given` a bag and no space `When` store bag `Then` store failed and return message

### take
- `Given` valid ticket `When` take bag `Then` take successfully and return bag
- `Given` invalid ticket `When` take bag `Then` take failed and return message

# primary locker robot
 - `Given` primary locker robot and available space `When` store bag `Then` store successfully and get a ticket
 - `Given` primary locker robot and no space `When` `Then` store bag `Then` get a message that locker is full

 - `Given` valid ticket `When` take bag `Then` take successfully and get right bag
 - `Given` invalid ticket `When` take bag `Then` take failed and get a message that ticket is invalid

 - `Given` primary locker robot with more than one locker and available space in first locker `When` store bag `Then` store in first locker successfully
 - `Given` primary locker robot with more than one locker and no space in first locker but available in second locker `When` store bag `Then` store in second locker successfully
 
 
 # smart locker robot
  - `Given` smart locker robot and available space `When` store bag `Then` store successfully and get a ticket
  - `Given` smart locker robot and no space `When` store bag `Then` get a message that locker is full
 
  - `Given` smart locker robot and lockers with different available space `When` store bag `Then` store in locker with most available capacity successfully
  - `Given` smart locker robot and lockers with the same available space `When` store bag `Then` store in any of the lockers successfully
  
  - `Given` valid ticket `When` take bag `Then` take successfully and get right bag
  - `Given` invalid ticket `When` take bag `Then` take failed and get a message that ticket is invalid


 # super locker robot
  - `Given` super locker robot and available space `When` store bag `Then` store successfully and get a ticket
  - `Given` super locker robot and no space `When` store bag `Then` get a message that locker is full
 
  - `Given` super locker robot and two lockers with available space and different vacancy ratio `When` store bag `Then` store in locker with max vacancy ratio successfully
  - `Given` super locker robot and two lockers with available space and same vacancy ratio `When` store bag `Then` store in any of the lockers successfully
  
  - `Given` valid ticket `When` take bag `Then` take successfully and get right bag
  - `Given` invalid ticket `When` take bag `Then` take failed and get a message that ticket is invalid
    - `Given` fake ticket `When` take bag `Then` take failed and get a message that ticket is invalid
    - `Given` used ticket `When` take bag `Then` take failed and get a message that ticket is invalid


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
# 需求分析
存包：系统随机分配（存在有空的位置），返回ticket

取包：携带ticket，系统根据ticket信息取包

# Tasking
获取ticket
1. 存在有空位置
Given locker的所有位置信息[1~9]，已经存包的位置[1~3] When 获取ticket Then 返回ticket, 位置为4

2. 没有空位置
Given locker的所有位置信息[1~9]，已经存包的位置[1~9] When 获取ticket Then 返回null

存包
Given ticket(lockerNumber 4) When 存包 Then 已存包位置更新为[1~4]

取包
Given lockerNumber（ticket 信息）When 取包 Then 返回包裹, 释放位置4，以存包位置更新为[1~3]

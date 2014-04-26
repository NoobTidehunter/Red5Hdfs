
#!/bin/bash  

for((i=1;i<120;i++));do
#系统分配的区总量  
mem_total=`free -m | grep Mem | awk '{print  $2}'`  
 
#当前已使用的used大小  
mem_used=`free -m | grep - | awk '{print  $3}'`  

echo "count mem"
echo "$mem_total -->- $mem_used" >> mem
sleep 0.5

done;

exit 0
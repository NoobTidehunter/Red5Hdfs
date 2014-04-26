#!/bin/bash
eth=eth0
for((i=0;i<120;i++));do
RXpre=$(ifconfig ${eth} | grep bytes | awk  '{print $2}'| awk -F":" '{print $2}')
TXpre=$(ifconfig ${eth} | grep bytes | awk '{print $6}' | awk -F":" '{print $2}')
sleep 0.5
RXnext=$(ifconfig ${eth} | grep bytes | awk  '{print $2}'| awk -F":" '{print $2}')
TXnext=$(ifconfig ${eth} | grep bytes | awk '{print $6}' | awk -F":" '{print $2}')

#RX一秒中的接受量，TX一秒中的发送量
echo RX ----- TX
echo "$(((${RXnext}-${RXpre})/1024))KB/s   $(((${TXnext}-${TXpre})/1024))KB/s" >> net.temp
#echo "$(((${RXnext})/1024))KB/s"
done;
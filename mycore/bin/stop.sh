#!/bin/sh

if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'order|assign|price|engine|money'"
  exit 0
fi
cmd=$1
cls='LogisticsOrderPlatform'
if [ "$cmd" = "order" ] ; then
    cls='LogisticsOrderPlatform'
elif [ "$cmd" = "assign" ] ; then
    cls='AssignApp'
elif [ "$cmd" = "assign-debug" ] ; then
    cls='AssignAppDebug'
elif [ "$cmd" = "engine" ] ; then
    cls='LogisticsEnginePlatform'
elif [ "$cmd" = "price" ] ; then
    cls='LogisticsPricePlatform'
elif [ "$cmd" = "money" ] ; then
        cls='LogisticsMoneyPlatform'
else
	echo "No application to stop ."
	exit 1
fi	

jps | grep "$cls" | grep -v grep | awk '{print $1}'| sed -e 's/^/kill -9 /g' | sh -
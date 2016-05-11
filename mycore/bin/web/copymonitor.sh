#!/bin/sh

#if [ $# -lt 1 ]; then
#    echo "need type model parameters like [ order | assign | engine | shipper | carrier | money | user | man ]"
#    exit 1
#fi

modelx='assign'
suffix='web'

if [ $# -eq 0 ]; then
   echo "need type logistics module ( order | assign | engine | shipper | driver | money | user | man )"
   exit 1
elif [ $# -gt 1 ]; then
   suffix=$2
fi

userPt='dili'
modelx=$1

# define function for copy file
function copyLog()  
{
   hostx=$1
   model=$2

   lfile="/home/$userPt/yibu56/logs/$model/monitor/$hostx"

   if [ ! -d $lfile ]; then
      echo "create directory for $lfile" 
      mkdir -p $lfile
   fi

   echo "copy log from $hostx -> $lfile"   
   echo "scp -r $userPt@$hostx:/home/$userPt/logs/logistics-$model/monitor/* $lfile/"

   scp -r $userPt@$hostx:/home/$userPt/logs/logistics-$model/monitor/* $lfile/
}

if [ "$modelx" = "assign" ] || [ "$modelx" = "order" ] ; then
  copyLog "192.168.29.37" "$modelx"
  copyLog "192.168.29.68" "$modelx"
elif [ "$modelx" = "engine" ] ; then
  copyLog "192.168.29.108" "$modelx"
  copyLog "192.168.29.38" "$modelx"
elif [ "$modelx" = "man" ] || [ "$modelx" = "user" ] || [ "$modelx" = "money" ]; then
  copyLog "192.168.29.69" "$modelx-$suffix"
  copyLog "192.168.29.48" "$modelx-$suffix"
elif [ "$modelx" = "shipper" ] ; then
  copyLog "192.168.29.108" "$modelx"
  copyLog "192.168.29.38" "$modelx"
elif [ "$modelx" = "driver" ] ; then
  copyLog "192.168.29.111" "$modelx-$suffix"
  copyLog "192.168.29.12" "$modelx-$suffix" 
else
  echo "nothing to copy"
fi

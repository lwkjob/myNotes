#!/bin/sh

#if [ $# -lt 1 ]; then
#    echo "need type model parameters like [ order | assign | engine | shipper | carrier | money | user | man ]"
#    exit 1
#fi

sufix=web
if [ $# -eq 0 ]; then
  echo "need type logistics module ( order | assign | engine | shipper | carrier | money | user | man )"
  exit 1 
elif [ $# -gt 1 ]; then
  sufix=$2  
fi

export packPath=/home/dili/yibu56
userPt='dili'
modelx=$1

if [ "$modelx" = "assign" ] ; then
    ssh $userPt@192.168.29.37 "/home/$userPt/yibu56/install-platform.sh assign debug"
    ssh $userPt@192.168.29.68 "/home/$userPt/yibu56/install-platform.sh assign debug"
elif [ "$modelx" = "order" ] ; then
    ssh $userPt@192.168.29.37 "/home/$userPt/yibu56/install-platform.sh order"
    ssh $userPt@192.168.29.68 "/home/$userPt/yibu56/install-platform.sh order"
elif [ "$modelx" = "engine" ] ; then
    ssh $userPt@192.168.29.108 "/home/$userPt/yibu56/install-platform.sh egine"
    ssh $userPt@192.168.29.38 "/home/$userPt/yibu56/install-platform.sh egine"
elif [ "$modelx" = "man" ] ; then
    ssh $userPt@192.168.29.69 "/home/$userPt/yibu56/install-web.sh man"
    ssh $userPt@192.168.29.48 "/home/$userPt/yibu56/install-web.sh man"
elif [ "$modelx" = "user" ] ; then
    if [ "$sufix" = "app" ] ; then
       ssh $userPt@192.168.29.69 "/home/$userPt/yibu56/install-web.sh user app"
       ssh $userPt@192.168.29.48 "/home/$userPt/yibu56/install-web.sh user app"
    else
       ssh $userPt@192.168.29.69 "/home/$userPt/yibu56/install-web.sh user"
       ssh $userPt@192.168.29.48 "/home/$userPt/yibu56/install-web.sh user"
    fi
elif [ "$modelx" = "shipper" ] ; then
    ssh $userPt@192.168.29.108 "/home/$userPt/yibu56/install-web.sh shipper"
    ssh $userPt@192.168.29.38 "/home/$userPt/yibu56/install-web.sh shipper"
elif [ "$modelx" = "carrier" ] ; then
    if [ "$sufix" = "wap" ] ; then
       ssh $userPt@192.168.29.111 "/home/$userPt/yibu56/install-web.sh carrier wap"
       ssh $userPt@192.168.29.12 "/home/$userPt/yibu56/install-web.sh carrier wap"
    else
       ssh $userPt@192.168.29.111 "/home/$userPt/yibu56/install-web.sh carrier"
       ssh $userPt@192.168.29.12 "/home/$userPt/yibu56/install-web.sh carrier"
    fi
elif [ "$modelx" = "money" ] ; then
    ssh $userPt@192.168.29.69 "/home/$userPt/yibu56/install-web.sh money"
    ssh $userPt@192.168.29.48 "/home/$userPt/yibu56/install-web.sh money"
fi


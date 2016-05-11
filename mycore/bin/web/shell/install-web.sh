#!/bin/sh

websuf=web

if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'user|shipper|man|money'"
  exit 0
elif [ $# -eq 2 ]; then
  websuf=$2
fi

webBase='/home/dili/apache-tomcat-6.0.43/sites'
warDir='/home/dili/yibu56/war-dir'

webapp=logistics-$1-$websuf

echo 'webapp='$webapp

echo "kill tomcat process"

cd "$webBase/$webapp"
./catalina.sh stop

#cd ~/app

echo "remove $webapp"
echo "$webBase/$webapp/webapps/ROOT/*"

rm -rf $webBase/$webapp/webapps/ROOT/*
echo "copy $webapp"
cp $warDir/$webapp-2.0.0.war $webBase/$webapp/webapps/ROOT

echo "unzip $webapp"
cd $webBase/$webapp/webapps/ROOT
unzip -q ./$webapp-2.0.0.war

echo "start $webapp /  tomcat"
cd $webBase/$webapp/
./catalina.sh start

#cd /home/dili/app/
#./backup-version.sh

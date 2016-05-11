#!/bin/sh

webapp='logistics-web'
if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'user|shipper|man|driverm'"
  exit 0
fi

webapp=logistics-$1-web
echo 'webapp='$webapp

echo "kill tomcat process"

cd "/home/searcher/apache-tomcat-6.0.43/sites/$webapp"
./catalina.sh stop

#cd ~/app

echo "remove $webapp"
echo "../apache-tomcat-6.0.43/sites/$webapp/webapps/ROOT/*"

rm -rf /home/searcher/apache-tomcat-6.0.43/sites/$webapp/webapps/ROOT/*
echo "copy $webapp"
cp /home/searcher/yibu56/war-dir/$webapp-2.0.0.war /home/searcher/apache-tomcat-6.0.43/sites/$webapp/webapps/ROOT

echo "unzip $webapp"
cd /home/searcher/apache-tomcat-6.0.43/sites/$webapp/webapps/ROOT
unzip -q ./$webapp-2.0.0.war

echo "start $webapp /  tomcat"
cd /home/searcher/apache-tomcat-6.0.43/sites/$webapp/
./catalina.sh start

#cd /home/dili/app/
#./backup-version.sh

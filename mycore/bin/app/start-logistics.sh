#!/bin/sh

webapp='acps-web'
tomcatdir=/usr/local/serverdir/apache-tomcat-6.0.43
if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'acps'"
  exit 0
fi

webapp=$1-web
echo 'webapp='$webapp

echo "kill tomcat process"

cd "$tomcatdir/sites/$webapp"
./catalina.sh stop
#kill -9 `ps -ef|grep acps-web|awk '{print $2}'`
pid=`ps aux | grep tomcat | grep -v grep|grep -v catalina.out|awk '{print $2}'`

echo "kill $pid"
if [ -n "$pid" ]
then
{
   echo ===========shutdown================
   ps aux | grep tomcat | grep -v grep|grep -v catalina.out|awk '{print $2}'|xargs kill -9
}
fi

#cd ~/app

echo "remove $tomcatdir/sites/$webapp/webapps/ROOT/*"
echo "remove $tomcatdir/sites/$webapp/work/*"

rm -rf $tomcatdir/sites/$webapp/webapps/ROOT/*
rm -rf $tomcatdir/sites/$webapp/work/*
echo "copy /usr/local/serverdir/acps-webapps/war-dir/$webapp-1.0.0.war $tomcatdir/sites/$webapp/webapps/ROOT"
cp /usr/local/serverdir/acps-webapps/war-dir/$webapp-1.0.0.war $tomcatdir/sites/$webapp/webapps/ROOT

echo "unzip $webapp"
cd $tomcatdir/sites/$webapp/webapps/ROOT
unzip -q ./$webapp-1.0.0.war

echo "start $webapp /  tomcat"
cd $tomcatdir/sites/$webapp/
./catalina.sh start

#cd /home/dili/app/
#./backup-version.sh

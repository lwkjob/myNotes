#!/bin/sh

webapp='logistics-web'
if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'user|shipper|man|money'"
  exit 0
fi

webapp=logistics-$1-web
echo 'webapp='$webapp

echo "kill tomcat process"

cd "/home/searcher/apache-tomcat-6.0.43/sites/$webapp"
./catalina.sh stop

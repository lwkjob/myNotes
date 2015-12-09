#!/bin/sh
#export bak_date=`date +%Y-%m-%d`
#mkdir $bak_date
#echo "bak old version for dir : $bak_date"
#mv logistics-mobile-1.0.0.war $bak_date
#mv logistics-platform-1.0.0-bin.tar.gz $bak_date

app='assign-platform'
if [ $# -eq 0 ]; then
  echo "you must type a parameter of 'assgin|order|engine|money'"
  exit 0
fi

shellPath='/home/dili/yibu56/app'

mdl=$1
app=$mdl'-platform'
pltfm='Logistics'$mdl'Platform'
echo 'app='$app

echo "kill $app "
#jps | grep $pltfm | grep -v grep | awk '{print $1}'| sed -e 's/^/kill -9 /g' | sh -
cd $shellPath/logistics-$mdl-platform/
./bin/stop.sh $mdl $2
echo "remove $mdl-platform dir"
rm -rf $shellPath/logistics-$mdl-platform/*

echo "tar $mdl-platform app"
tar -zxvf $shellPath/$mdl-platform-2.0.0-bin.tar.gz -C $shellPath/
echo "start $mdl-platform shell"
cd $shellPath/logistics-$mdl-platform
./bin/start-daemon.sh $mdl $2

#echo "bak old version for dir : $bak_date"
#mv logistics-mobile-1.0.0.war $bak_date

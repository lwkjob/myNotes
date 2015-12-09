#!/bin/sh

export bak_date=`date +%Y-%m-%d`

mkdir ./war-dir/$bak_date

echo "bak old version for dir : $bak_date"
cp ./war-dir/logistics-user-web-2.0.0.war ./war-dir/$bak_date
cp ./war-dir/logistics-man-web-2.0.0.war ./war-dir/$bak_date
cp ./war-dir/logistics-shipper-web-2.0.0.war ./war-dir/$bak_date

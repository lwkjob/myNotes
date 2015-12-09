#!/bin/sh

#if [ $# -lt 1 ]; then
#    echo "need type model parameters like [ order | assign | engine | shipper | carrier | money | user | man ]"
#    exit 1
#fi

arrays=$@

if [ $# -eq 0 ]; then
  arrays=("192.168.29.111" "192.168.29.12" "192.168.29.108" "192.168.29.38" "192.168.29.69" "192.168.29.48" "192.168.29.37" "192.168.29.68" )
fi

export way=$(cd "$(dirname "${0}")";cd .;pwd)
userPt='dili'

# define function for copy file
function copySetting()  
{
   hostx=$1
   echo "clear jdk temp file  -> $hostx"
   ssh $userPt@$hostx "rm -rf /home/$userPt/yibu56/jdk-7u60-linux-x64.tar.gz"
   ssh $userPt@$hostx "rm -rf /home/$userPt/yibu56/jdk1.7.0_60"
}

for arg in ${arrays[@]};
do
    echo " --#####################-clear jdk temp file for $arg #################################### "
    copySetting $arg
done


#!/bin/sh

#if [ $# -lt 1 ]; then
#    echo "need type model parameters like [ order | assign | engine | shipper | carrier | money | user | man ]"
#    exit 1
#fi

arrays=$@

if [ $# -eq 0 ]; then
  arrays=("192.168.29.111" "192.168.29.12" "192.168.29.108" "192.168.29.38" "192.168.29.69" "192.168.29.48" "192.168.29.37" "192.168.29.68" )
fi

userPt='dili'

export way=$(cd "$(dirname "${0}")";cd .;pwd)
sshKey="/home/$userPt/.ssh/id_rsa.pub"

#generate ssh key rsa
if [ ! -f $sshKey ]; then
  echo "generate ssh key 4 rsa" 
  ssh-keygen -t rsa
fi

#make ssh access
function makeSshAccess()  
{
   host=$1
   echo "copy id_rsa.pub -> $host"
   scp /home/$userPt/.ssh/id_rsa.pub $userPt@$host:/home/$userPt/.ssh/authorized_keys
   ssh $userPt@$host "mkdir /home/$userPt/yibu56 /home/$userPt/yibu56/app /home/$userPt/yibu56/war-dir" 
}

# define function for copy file
function copyShell()  
{
   hostx=$1
   echo "copy install shell and start shell -> $hostx"
   scp $way/install-platform.sh $userPt@$hostx:/home/$userPt/yibu56/
   scp $way/install-web.sh $userPt@$hostx:/home/$userPt/yibu56/   
}  

for arg in ${arrays[@]};
do
    echo " --#####################- make env for $arg #################################### "
    makeSshAccess $arg
    copyShell $arg
done


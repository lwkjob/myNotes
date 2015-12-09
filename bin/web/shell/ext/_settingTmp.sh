 # .bashrc

 # Source global definitions
 if [ -f /etc/bashrc ]; then
         . /etc/bashrc
 fi

 # User specific aliases and functions

 JAVA_HOME=/usr/local/jdk1.7.0_60
 CLASSPATH=.:$JAVA_HOME/lib.tools.jar
 PATH=$JAVA_HOME/bin:$PATH
 export JAVA_HOME CLASSPATH PATH

 export TOMCAT_HOME=/home/dili/apache-tomcat-6.0.43
 export CATALINA_HOME=$TOMCAT_HOME
 export PATH=$TOMCAT_HOME/bin:$PATH
LANG=zh_CN.gbk
export LANG

ORACLE_HOME=/opt/o9ias/apache-tomcat-web
export ORACLE_HOME

OC4J_HOME=$ORACLE_HOME/webapps/default-web-app/WEB-INF/classes
cd $OC4J_HOME

CLASSPATH=$ORACLE_HOME/lib/mail.jar:$ORACLE_HOME/lib/activation.jar:$ORACLE_HOME/lib/ojdbc6.jar:$ORACLE_HOME/lib/orai18n.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-beanutils-1.6.1.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-codec-1.3.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-collections-3.1.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-configuration-1.2.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-dbutils-1.0.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-httpclient-3.0.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-lang-2.1.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-logging-api.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-logging.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/commons-pool-1.2.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/datedFileAppender-1.0.2.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/jspsmart.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/junit-4.1.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/log4j-1.2.13.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/log4j-1.2.8.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/quartz-1.5.1.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/stinger.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/xom-1.0d21.jar
CLASSPATH=$CLASSPATH:/opt/o9ias/ack/lib/BouncyCastle_s.jar
CLASSPATH=$CLASSPATH:.
export CLASSPATH

JAVA_HOME=/opt/o9ias/jdk1.6/bin
export JAVA_HOME

$JAVA_HOME/java com.capinfo.bcdl.abc.JobAbcMonitor 

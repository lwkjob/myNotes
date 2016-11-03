
package com.lwk.log4j;

import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * Created by Administrator on 2016/8/4.
 */
public class LwkTestLog4j {

      static    Logger logger= LoggerFactory.getLogger(LwkTestLog4j.class);

    public static void main(String[] args) throws Exception{

      DOMConfigurator.configure(LwkTestLog4j.class.getClassLoader().getResource("log4jlwk.xml"));
//        PropertyConfigurator.configure(LwkTestLog4j.class.getClassLoader().getResource("log4jlwk.properties"));
//

        long i=1;
        while(i<5){
            i++;
            logger.info("lwk------------- "+i+" lwk request");
//            Thread.sleep(3);
        }

        TimeUnit.SECONDS.sleep(1);
    }


}

/**
 *	@author lwk
 *	@2013-7-5上午9:43:21
 *	@client.java
 */
package pattern.factory.abstracts;

import pattern.factory.simple.Sender;

/**
 * @author lwk
 * 
 *  抽象工厂有问题
 *      
 */
public class Client {
    public static void main(String[] args) {
    	//搞出个工厂
        Provider mailFactory = new SendMailFactory();
        Provider phoneFactory = new SendPhoneFactory();
        
        //生产对象
        Sender sd = mailFactory.getSender();

        Sender sd2 = phoneFactory.getSender();
        
        //使用对象
        sd.send();
        sd2.send();
    }
}

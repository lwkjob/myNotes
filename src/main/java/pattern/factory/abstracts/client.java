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
 *      抽象工厂有问题，没搞懂
 *      
 */
public class client {
    public static void main(String[] args) {
        Provider mailFactory = new SendMailFactory();
        Provider phoneFactory = new SendPhoneFactory();

        Sender sd = mailFactory.getSender();

        Sender sd2 = phoneFactory.getSender();

        sd.send();
        sd2.send();
    }
}

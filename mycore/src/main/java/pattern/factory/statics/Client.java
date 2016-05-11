/**
 *	@author lwk
 *	@2013-7-5上午9:43:21
 *	@client.java
 */
package pattern.factory.statics;

public class Client {
    
    public static void main(String[] args) {
        
        StaticSenderFactory.getMailSender().send();
        StaticSenderFactory.getPhoneSender().send();
    }
}

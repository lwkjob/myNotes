/**
 *	@author lwk
 *	@2013-7-5上午9:43:21
 *	@client.java
 */
package pattern.factory.method;

public class client {
    public static void main(String[] args) {
        MethodSenderFactory factory = new MethodSenderFactory();
        factory.getMailSender().send();
        factory.getPhoneSender().send();
    }
}

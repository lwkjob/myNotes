/**
 *	@author lwk
 *	@2013-7-5上午9:43:14
 *	@SendFactory.java
 */
package pattern.factory.simple;

public class SendFactory {
    public Sender getSender(String senderName) {
        if (senderName.equals("mail")) {
            return new MailSender();
        } else if (senderName.equals("phone")) {
            return new PhoneSender();
        } else {
            return null;
        }
    }
}

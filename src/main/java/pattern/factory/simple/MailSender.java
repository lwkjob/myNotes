/**
*	@author lwk
*	@2013-7-5上午9:41:45
*	@MailSender.java
*/
package pattern.factory.simple;
public class MailSender implements Sender {

    @Override
    public void send() {
        System.out.println("mail send");
    }
}

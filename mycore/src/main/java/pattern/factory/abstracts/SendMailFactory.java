/**
*	@author lwk
*	@2013-7-5上午10:14:31
*	@SendMailFactory.java
*/
package pattern.factory.abstracts;

import pattern.factory.simple.MailSender;
import pattern.factory.simple.Sender;

/**
 * 发邮件的
 * @author lwkjob
 *
 */
public class SendMailFactory implements Provider{

    @Override
    public Sender getSender() {
        return new MailSender();
    }

}

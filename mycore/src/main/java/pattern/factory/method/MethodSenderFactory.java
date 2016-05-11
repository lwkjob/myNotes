/**
 *	@author lwk
 *	@2013-7-5上午10:03:44
 *	@MethodSenderFactory.java
 */
package pattern.factory.method;

import pattern.factory.simple.MailSender;
import pattern.factory.simple.PhoneSender;
import pattern.factory.simple.Sender;

public  class MethodSenderFactory {
    
    public Sender getMailSender(){
        return new MailSender();
    }
    public Sender getPhoneSender(){
        return new PhoneSender();
    }
}

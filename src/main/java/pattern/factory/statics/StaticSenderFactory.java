
package pattern.factory.statics;

import pattern.factory.simple.MailSender;
import pattern.factory.simple.PhoneSender;
import pattern.factory.simple.Sender;
/**
 *	@author lwk
 *	@2013-7-5上午10:03:44
 */
public  class StaticSenderFactory {
    
    public static Sender getMailSender(){
        return new MailSender();
    }
    public static Sender getPhoneSender(){
        return new PhoneSender();
    }
}

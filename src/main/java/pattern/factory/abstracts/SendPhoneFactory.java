/**
*	@author lwk
*	@2013-7-5上午10:15:48
*	@SendPhoneFactory.java
*/
package pattern.factory.abstracts;

import pattern.factory.simple.PhoneSender;
import pattern.factory.simple.Sender;

public class SendPhoneFactory implements Provider{

    @Override
    public Sender getSender() {
        return new PhoneSender();
    }

}

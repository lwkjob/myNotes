/**
 *	@author lwk
 *	@2013-7-5上午10:12:25
 *	@Provider.java
 */
package pattern.factory.abstracts;

import pattern.factory.simple.Sender;

public interface Provider {
    Sender getSender();
}

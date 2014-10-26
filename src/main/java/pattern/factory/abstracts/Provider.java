/**
 *	@author lwk
 *	@2013-7-5上午10:12:25
 *	@Provider.java
 */
package pattern.factory.abstracts;

import pattern.factory.simple.Sender;

/**
 * 
 * 供应商
 * @author lwkjob
 *
 */
public interface Provider {
    Sender getSender();
}

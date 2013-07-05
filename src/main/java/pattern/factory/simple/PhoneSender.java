/**
 *	@author lwk
 *	@2013-7-5上午9:42:22
 *	@PhoneSender.java
 */
package pattern.factory.simple;

public class PhoneSender implements Sender {

    @Override
    public void send() {
        System.out.println("phone send");
    }

}

/**
*	@author lwk
*	@2013-7-5上午9:43:21
*	@client.java
*/
package pattern.factory.simple;
public class client {
    public static void main(String[] args) {
        SendFactory factory=new SendFactory();
        factory.getSender("mail").send();
        factory.getSender("phone").send();
    }
}

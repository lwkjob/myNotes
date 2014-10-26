/**
 *	@author lwk
 *	@2013-7-5上午9:43:21
 *	@client.java
 */
package pattern.factory.method;

/**
 * 大工厂 根据不同的方法创建不同的对象
 * @author lwkjob
 *
 */
public class Client {
    public static void main(String[] args) {
        MethodSenderFactory factory = new MethodSenderFactory();
        factory.getMailSender().send();
        factory.getPhoneSender().send();
    }
}

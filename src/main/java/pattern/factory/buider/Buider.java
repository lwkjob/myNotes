package pattern.factory.buider;

import java.util.ArrayList;
import java.util.List;

import pattern.factory.simple.MailSender;
import pattern.factory.simple.PhoneSender;
import pattern.factory.simple.Sender;

/**
 * @author lwk
 * 构建者模式 真心不知道 有什么作用
 */
public class Buider {
	private List<Sender> sendList = new ArrayList<Sender>();

	public void produceMailSender(int count) {
		for (int i = 0; i < count; i++) {
			sendList.add(new MailSender());
		}
	}

	public void producePhoneSender(int count) {
		for (int i = 0; i < count; i++) {
			sendList.add(new PhoneSender());
		}
	}
}

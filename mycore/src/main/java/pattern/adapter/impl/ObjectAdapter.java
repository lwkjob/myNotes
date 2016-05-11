package pattern.adapter.impl;

import pattern.adapter.Source;
import pattern.adapter.Targetable;

/**
 * @author lwk 对象适配器
 */
public class ObjectAdapter implements Targetable {

	// 被适配者
	private Source source;
	public ObjectAdapter(Source source) {
		this.source=source;
	}

	@Override
	public void method1() {
		source.method1();
	}

	@Override
	public void method2() {
		System.out.println("自己的实现");
	}

}

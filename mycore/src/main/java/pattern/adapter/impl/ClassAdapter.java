package pattern.adapter.impl;

import pattern.adapter.Source;
import pattern.adapter.Targetable;

/**
 * @author lwk 类适配器
 */
public class ClassAdapter extends Source implements Targetable {

	@Override
	public void method2() {
		System.out.println("适配器的方法");
	}
}

package pattern.adapter;

import pattern.adapter.impl.ClassAdapter;
import pattern.adapter.impl.ObjectAdapter;
import pattern.adapter.impl.SourceSub1;
import pattern.adapter.impl.SourceSub2;

public class Client {
	public static void main(String[] args) {
		/**
		 * 类适配(利用继承)
		 * */
		Targetable classAdapter = new ClassAdapter();
		// 这里调用的是继承自被适配者(Source)的方法
		classAdapter.method1();
		/**
		 * 对象适配(利用组合)
		 * */
		Source source=	new Source();
		Targetable objectAdapter=new ObjectAdapter(source);
		// 这里调用的是被适配者(source对象)的方法
		objectAdapter.method1();
		
		/**
		 * 接口适配器(继承抽象类实现空方法)
		 * */
		Sourceable sourceSub1=new SourceSub1();
		Sourceable sourceSub2=new SourceSub2();
		sourceSub1.method1();
		sourceSub1.method2();//没实现为空
		sourceSub2.method1();//没实现为空
		sourceSub2.method2();
	}
}

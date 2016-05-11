package pattern.proxy;

public class Proxy implements Target{
	private Target target;
	
	/**
	 * 代理类  内部构建了 被代理对象
	 */
	public Proxy() {
		this.target=new TargetImpl();
	}
	private void after(){
		System.out.println("之后");
	}
	private void before(){
		System.out.println("之前");
	}
	
	
	@Override
	public void targetMethod() {
		after();
		target.targetMethod();
		before();
	}

}

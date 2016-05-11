package pattern.proxy;

public class TargetImpl implements Target{

	@Override
	public void targetMethod() {
			System.out.println("original 方法");	
	}

}

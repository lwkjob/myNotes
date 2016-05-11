package pattern.decorator;

public class DecoratorSource implements DecoratorSourceable {

	@Override
	public void method() {
		System.out.println("original method");
	}
}

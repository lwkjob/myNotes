package pattern.composite;

public abstract class Market {
	
	String name;//组合着的名字

	public abstract void add(Market m);

	public abstract void remove(Market m);

	public abstract void PayByCard();
}
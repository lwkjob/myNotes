package pattern.composite;

public abstract class Market {
	String name;

	public abstract void add(Market m);

	public abstract void remove(Market m);

	public abstract void PayByCard();
}
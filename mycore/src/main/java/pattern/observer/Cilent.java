package pattern.observer;

/**
 * @author lwk
 * 这个只实现 推送的形式
 * 还可以又观察者自己拉取的方式(待实现.....)
 */ 
public class Cilent {
	public static void main(String[] args) {
		Subject subject=new SubjectImpl("12", "23424");
		Observer ob1=new Observer1();
		Observer ob2=new Observer2();
		subject.add(ob1);
		subject.add(ob2);
		subject.notifyObserver();
	}
}

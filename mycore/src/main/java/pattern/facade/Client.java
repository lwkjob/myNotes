package pattern.facade;

/**
 * @author lwk 外观模式就是个大的组合 把所有要一直执行的动作封装到一起(貌似还带顺序)
 */
public class Client {
	
	public static void main(String[] args) {
		Conputer pc = new Conputer();
		pc.starup();
		System.out.println("\r\n------我开始使用电脑----\r\n");
		pc.shutdown();
	}
}

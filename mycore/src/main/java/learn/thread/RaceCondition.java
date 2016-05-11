package learn.thread;

/**
 * 竞争状态   可见性
 * @author lwkjob
 * volatile 保障可见性
 *
 */
public class RaceCondition {
	
	private static volatile boolean  done=true;
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(){
			public void run() {
				int i=0;
				while(done){
					i++;
				}
				System.out.println("搞完了"+i);
			};
		}.start();
		System.out.println("os "+System.getProperty("os.name"));
		Thread.sleep(2000);
		done=false;
		System.out.println("设置完成");
	}
}

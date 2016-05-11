package pattern.facade;

public class Conputer {
	private CPU cpu;
	private Memory memory;
	private Disk disk;

	public Conputer() {
		this.cpu = new CPU();
		this.memory = new Memory();
		this.disk = new Disk();
	}

	public void starup() {
		System.out.println("开电脑");
		cpu.startup();
		memory.startup();
		disk.startup();
		System.out.println("电脑成功开启");
	}

	public void shutdown() {
		System.out.println("关闭电脑");
		cpu.shutdow();
		memory.shutdow();
		disk.shutdow();
		System.out.println("关闭电脑完成");
	}
}

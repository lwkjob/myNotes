package pattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author lwk
 * 原型模式
 */
public class Prototype implements Cloneable ,Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
	private User obj;  
	
	/*浅复制*/
	@Override
	protected Object clone() throws CloneNotSupportedException {
		Prototype proto=(Prototype) super.clone();//native
		return proto;
	}
	public Object deepClone() throws IOException, ClassNotFoundException {
		
		/*写入当前对象二进制流*/
		ByteArrayOutputStream bos=new ByteArrayOutputStream();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		/*读取二进制流产生新的对象*/
		ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
		ObjectInputStream ois=new ObjectInputStream(bis);
		
		return ois.readObject();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public User getObj() {
		return obj;
	}
	public void setObj(User obj) {
		this.obj = obj;
	}
	
}

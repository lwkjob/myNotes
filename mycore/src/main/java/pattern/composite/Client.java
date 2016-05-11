package pattern.composite;

/**
 * 组合模式(部分-整体) 
 * 当发现需求中是体现部分与整体层次结构时，
 * 以及你希望用户可以忽略组合对象与单个对象的不同，
 * 统一地使用组合结构中的所有对象时，就应该考虑组合模式了
 *  
 * @author lwkjob
 * 
 */
public class Client {

	public static void main(String[] args) {
		MarketBranch rootBranch = new MarketBranch("总店");
		MarketBranch fd = new MarketBranch("分店1");
		MarketJoin fdjm1 = new MarketJoin("分店1加盟店1");
		MarketJoin fdjm2 = new MarketJoin("分店1加盟店2");
		fd.add(fdjm1);
		fd.add(fdjm2);
		rootBranch.add(fd);
		rootBranch.PayByCard();
	}
}
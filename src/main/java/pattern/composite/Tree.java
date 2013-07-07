package pattern.composite;

/**
 * @author lwk
 * 组合模式 (部分-整体模式)
 * 这个模式 彻底没明白
 */
public class Tree {
	TreeNode root = null;

	public Tree(String name) {
		root = new TreeNode(name);
	}
	public static void main(String[] args) {
		Tree tree=new Tree("A");
		TreeNode nodeB=new TreeNode("B");
		TreeNode nodec=new TreeNode("C");
		nodeB.add(nodec);
		tree.root.add(nodeB);
		System.out.println("构建树完成");
		System.out.println(tree.root.getChildren().nextElement());
	}
}

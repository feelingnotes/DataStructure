
import sort.sort;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		//¶þ²æÊ÷²âÊÔ
		int[] is = { 5, 2, 1, 6, 3, 4 };
//		Tree tree = BinaryTree.createTree(is);
//		BinaryTree.deleteNode(tree, 3);
//		BinaryTree.PrintInorder(tree.root);
//		BinaryTree.PrintInorder(tree);
//		BinaryTree.PrintPostorder(tree);
//		BinaryTree.PrintPreOrder(tree);
		sort.quickSort(is, 0, is.length - 1);
		for(int i = 0; i< is.length; i++){
			System.out.println(is[i]);
		}
	}

	
}

package binarytree;

public class BinaryTree3 {

	// 二叉树转换为链表辅助类
	static class ListValue {
		Node header;
		Node tail;
	}

	// 插入结点
	static void insertNode(Tree tree, int i) {
		if (tree == null) {
			return;
		}
		Node tNode = tree.root;
		Node pNode = null;
		Node nNode = new Node();
		nNode.value = i;
		while (tNode != null) {
			pNode = tNode;
			if (tNode.value <= i) {
				tNode = tNode.right;
			} else {
				tNode = tNode.left;
			}
		}

		if (pNode == null) {
			tree.root = nNode;
		} else if (pNode.value > i) {
			pNode.left = nNode;
		} else {
			pNode.right = nNode;
		}
		tree.size++;
	}

	// 创建二叉树
	static Tree createTree(int[] is) {
		Tree tree = new Tree();
		tree.size = 0;
		for (int i = 0; i < is.length; i++) {
			insertNode(tree, is[i]);
		}
		return tree;
	}

	// 二叉树中序遍历
	public static void PrintInorder(Node node) {
		if (node == null) {
			return;
		}
		PrintInorder(node.left);
		System.out.print(node.value);
		PrintInorder(node.right);
	}

	// 二叉树转换为链表
	public static ListValue treeToList(Node node) {
		ListValue listValue = new ListValue();
		if (node == null) {
			return null;
		}
		listValue.header = node;
		listValue.tail = node;
		ListValue left = treeToList(node.left);
		ListValue right = treeToList(node.right);
		if (left != null) {
			left.tail.right = node;
			listValue.header = left.header;
			node.left = left.tail;
		}
		if (right != null) {
			right.header.left = node;
			listValue.tail = right.tail;
			node.right = right.header;
		}
		return listValue;
	}

	// 打印链表（后序输出）
	public static void printList(ListValue listValue) {
		if (listValue == null) {
			return;
		}
		while (listValue.tail != null) {
			System.out.println(listValue.tail.value);
			listValue.tail = listValue.tail.left;
		}
	}

	// 判断是否为后序遍历的二叉查找树
	public static boolean isPostOrderOfTree(int p[], int startIndex, int endIndex) {
		if (startIndex == endIndex) {
			return true;
		}
		int root = p[endIndex];
		int current = startIndex;
		while (current < endIndex && p[current] <= root) {
			current++;
		}
		for (int i = current; i < endIndex; i++) {
			if (p[current] < root) {
				return false;
			}
		}
		if (current == endIndex || current == startIndex) {
			return isPostOrderOfTree(p, startIndex, endIndex - 1);
		} else {
			boolean left = isPostOrderOfTree(p, startIndex, current - 1);
			if (left) {
				return isPostOrderOfTree(p, current, endIndex - 1);

			}
		}
		return false;
	}

	// 判断是否为子树
	public static boolean isSubTree(Node node, Node subNode) {
		if (node == null || subNode == null) {
			return false;
		}
		if (node.value == subNode.value) {
			return isTreeEquals(node, subNode);
		} else if (node.left != null) {
			return isTreeEquals(node.left, subNode);
		} else {
			return isTreeEquals(node.right, subNode);
		}
	}

	// 判断树是否相等
	public static boolean isTreeEquals(Node node1, Node node2) {
		if (node1 == null && node2 == null) {
			return true;
		}
		if (node1 == null || node2 == null) {
			return false;
		}
		if (node1.value == node2.value) {
			return isTreeEquals(node1.left, node2.left) && isTreeEquals(node1.right, node2.right);
		} else {
			return false;
		}
	}

	// 根据先序、中序遍历重建二叉树
	public static Node createTree(int preOrder[], int preStart, int preEnd, int inOrder[], int inStart, int inEnd) {
		Node root = new Node();
		int first = preOrder[preStart];
		root.value = first;
		if (preStart == preEnd) {
			return root;
		}
		int rootIndex = inStart;
		while (rootIndex < inEnd && inOrder[rootIndex] != first) {
			rootIndex++;
		}
		int leftPreOrderEnd = preStart + (rootIndex - inStart);
		if (rootIndex != inStart) {
			root.left = createTree(preOrder, preStart + 1, leftPreOrderEnd, inOrder, inStart, rootIndex - 1);
		}
		if (rootIndex != inEnd) {
			root.right = createTree(preOrder, leftPreOrderEnd + 1, preEnd, inOrder, rootIndex + 1, inEnd);
		}
		return root;
	}
}

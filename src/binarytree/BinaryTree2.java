package binarytree;

import java.util.ArrayList;

public class BinaryTree2 {
	
	//计算二叉树距离辅助类
	public static class Value {
		int depth;
		int distance;
	}

	//判断平衡二叉树辅助类
	public static class BalanceValue {
		boolean isBalance;
		int treeHeight;
	}

	//插入结点
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

	//生成树
	static Tree createTree(int[] is) {
		Tree tree = new Tree();
		tree.size = 0;
		for (int i = 0; i < is.length; i++) {
			insertNode(tree, is[i]);
		}
		return tree;
	}

	//中序遍历
	public static void PrintInorder(Node node) {
		if (node == null) {
			return;
		}
		PrintInorder(node.left);
		System.out.print(node.value);
		PrintInorder(node.right);
	}

	//镜像二叉树
	public static void swapTree(Node node) {
		if (node == null) {
			return;
		}
		Node temp = node.left;
		node.left = node.right;
		node.right = temp;
		swapTree(node.left);
		swapTree(node.right);
	}

	//判断二叉树中元素存在
	public static boolean isInTree(Node node, int n) {
		if (node == null) {
			return false;
		}
		if (node.value == n) {
			return true;
		}
		boolean is = isInTree(node.left, n);
		if (!is) {
			is = isInTree(node.right, n);
		}
		return is;
	}

	//二叉树中和为s的路径
	public static void printSum(ArrayList<Node> nodes, Node node, int sum) {
		if (node == null) {
			return;
		}
		nodes.add(node);
		boolean isLeaf = node.left == null && node.right == null;
		if (node.value == sum && isLeaf) {
			for (int i = 0; i < nodes.size(); i++) {
				System.out.println(nodes.get(i).value);
			}
		}
		printSum(nodes, node.left, sum - node.value);
		printSum(nodes, node.right, sum - node.value);

		nodes.remove(nodes.size() - 1);
	}

	//二叉树最大距离
	public static Value calculateMaxTreeDistance(Node node) {
		Value value = new Value();
		if (node == null) {
			return null;
		}
		if (node.left == null && node.right == null) {
			value.depth = 0;
			value.distance = 0;
			return value;
		}
		int leftDepth = -1;
		int leftDistance = 0;
		if (node.left != null) {
			Value left = calculateMaxTreeDistance(node.left);
			leftDepth = left.depth;
			leftDistance = left.distance;
		}
		int rightDepth = -1;
		int rightDistance = 0;
		if (node.right != null) {
			Value right = calculateMaxTreeDistance(node.right);
			rightDepth = right.depth;
			rightDistance = right.distance;
		}

		if (leftDepth > rightDepth) {
			value.depth = leftDepth + 1;
		} else {
			value.depth = rightDepth + 1;
		}

		int through = leftDepth + rightDepth + 2;
		int notThrough;
		if (leftDistance > rightDistance) {
			notThrough = leftDistance;
		} else {
			notThrough = rightDistance;
		}
		if (through > notThrough) {
			value.distance = through;
		} else {
			value.distance = notThrough;
		}
		return value;
	}

	//判断是否二叉树
	public static BalanceValue isTreeBalance(Node node) {
		BalanceValue bValue = new BalanceValue();
		if (node == null) {
			bValue.isBalance = true;
			bValue.treeHeight = -1;
			return bValue;
		}
		BalanceValue left = isTreeBalance(node.left);
		if (left.isBalance) {
			BalanceValue right = isTreeBalance(node.right);
			if (right.isBalance) {
				if (left.treeHeight - right.treeHeight == 0 || left.treeHeight - right.treeHeight == -1) {
					bValue.isBalance = true;
					bValue.treeHeight = right.treeHeight + 1;
				} else if (left.treeHeight - right.treeHeight == 1) {
					bValue.isBalance = true;
					bValue.treeHeight = left.treeHeight + 1;
				}
				return bValue;
			}
		}
		bValue.isBalance = false;
		return bValue;
	}
}

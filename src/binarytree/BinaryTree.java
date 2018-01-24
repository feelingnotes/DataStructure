package binarytree;

public class BinaryTree {
	// 插入结点
		public static void insertNode(Tree tree, int i) {
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

		//新建树
		public static Tree createTree(int[] is) {
			Tree tree = new Tree();
			tree.size = 0;
			for (int i = 0; i < is.length; i++) {
				insertNode(tree, is[i]);
			}
			return tree;
		}

		//删除结点
		public static void deleteNode(Tree tree, int i) {
			if (tree == null) {
				return;
			}
			// Node pNode = findNode(tree, i);
			// Node Node = findNode(tree, i);
			Node fNode = tree.root;
			Node pNode = tree.root;
			while (pNode != null) {
				if (pNode.value == i) {
					break;
				} else if (pNode.value > i) {
					fNode = pNode;
					pNode = pNode.left;
				} else {
					fNode = pNode;
					pNode = pNode.right;
				}
			}
			if (pNode.left == null && pNode.right == null) {
				if (pNode == tree.root) {
					tree = null;
				} else {
					pNode = null;
				}
			} else if (pNode.left == null) {
				fNode.right = pNode.right;
			} else if (pNode.right == null) {
				fNode.left = pNode.left;
			} else {
				Node t = pNode;
				Node s = pNode.left;
				while (s.right != null) {
					t = s;
					s = s.right;
				}
				if (t == pNode) {
					t.left = s.left;
				} else {
					t.right = s.left;
				}
			}
			return;
		}

		//非递归先序遍历
		public static void PrintPreOrder(Tree tree) {
			if (tree == null) {
				return;
			}
			Node[] stack = new Node[tree.size];
			// System.out.println(tree.size);
			int index = -1;
			Node node = tree.root;
			while (node != null || index >= 0) {
				while (node != null) {
					System.out.print(node.value + " ");
					index++;
					stack[index] = node;
					node = node.left;
				}
				if (index >= 0) {
					node = stack[index];
					stack[index] = null;
					index--;
				}
				node = node.right;
			}

		}

		//非递归中序遍历
		public static void PrintInorder(Tree tree) {
			if (tree == null) {
				return;
			}
			Node[] stack = new Node[tree.size];
			Node node = tree.root;
			int index = -1;
			while (node != null || index >= 0) {
				while (node != null) {
					index++;
					stack[index] = node;
					node = node.left;
				}
				if (index >= 0) {
					node = stack[index];
					System.out.println(node.value);
					stack[index] = null;
					index--;
				}
				node = node.right;
			}

		}

		//非递归后序遍历
		public static void PrintPostorder(Tree tree) {
			if (tree == null) {
				return;
			}
			Node[] stack = new Node[tree.size];
			Node node = tree.root;
			Node preNode = null;
			int index = 0;
			stack[index] = node;
			while (index >= 0) {
				node = stack[index];
				// 无子结点
				boolean hasNoChild = node.left == null && node.right == null;
				// 被访问
				boolean NoVisit = preNode == node.left || preNode == node.right;
				if (hasNoChild || NoVisit) {
					System.out.println(node.value);
					stack[index] = null;
					index--;
					preNode = node;
				} else {
					if (node.right != null) {
						index++;
						stack[index] = node.right;

					}
					if (node.left != null) {
						index++;
						stack[index] = node.left;
					}
				}
			}

		}

		//递归先序遍历
		public static void PrintPreorder(Node node) {
			if (node == null) {
				return;
			}
			System.out.println(node.value);
			PrintInorder(node.left);
			PrintInorder(node.right);
		}
		
		//递归中序遍历
		public static void PrintInorder(Node node) {
			if (node == null) {
				return;
			}
			PrintInorder(node.left);
			System.out.println(node.value);
			PrintInorder(node.right);
		}
		
		//递归后序遍历
		public static void PrintPostorder(Node node) {
			if (node == null) {
				return;
			}
			PrintInorder(node.left);
			System.out.println(node.value);
			PrintInorder(node.right);
		}
}

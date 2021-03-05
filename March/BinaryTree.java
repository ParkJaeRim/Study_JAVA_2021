import java.util.Scanner;

public class BinaryTree {
	static Node rootNode = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (true) {
			int N = sc.nextInt();
			if (N == 0) {
				break;
			}
			add(N);
		}
//		preorder(rootNode);
//		inorder(rootNode);
//		postorder(rootNode);
		find(52, 1, rootNode);
	}

	public static void add(int N) {
		if (rootNode == null) {
			rootNode = new Node(N);
		} else {
			Node now = rootNode;
			Node addThing = new Node(N);
			while (true) {
				if (N < now.value) {
					if (now.leftChild == null) {
						now.leftChild = addThing;
						return;
					} else {
						now = now.leftChild;
					}
				} else {
					if (now.rightChild == null) {
						now.rightChild = addThing;
						return;
					}
					now = now.rightChild;
				}
			}
		}
	}

	public static void find(int finding, int cnt, Node now) {
		if (now.value == finding) {
			System.out.println(cnt);
			return;
		} else if (now.value < finding) {
			find(finding, cnt + 1, now.rightChild);
		} else if (now.value > finding) {
			find(finding, cnt + 1, now.leftChild);
		}
	}

	public static void preorder(Node now) {
		if (now != null) {
			System.out.println(now.value);
			preorder(now.leftChild);
			preorder(now.rightChild);
		}
	}

	public static void inorder(Node now) {
		if (now != null) {
			inorder(now.leftChild);
			System.out.println(now.value);
			inorder(now.rightChild);
		}
	}

	public static void postorder(Node now) {
		if (now != null) {
			postorder(now.leftChild);
			postorder(now.rightChild);
			System.out.println(now.value);
		}
	}

	static class Node {
		int value;
		Node leftChild;
		Node rightChild;

		public Node(int value) {
			this.value = value;
			this.leftChild = null;
			this.rightChild = null;

		}
	}
}

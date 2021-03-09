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
		inorder(rootNode);
		System.out.println(rootNode.toString());
		delete(3, rootNode);
		System.out.println("===결과===");
		System.out.println(rootNode.toString());
//		inorder(rootNode);
//		postorder(rootNode);
//		find(52, 1, rootNode);

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

	public static void delete(int N, Node findNode) {
		System.out.println("============= : " + N + " " + findNode.value);
		System.out.println(findNode.toString());
		if (rootNode.value == N) {
			rootNode = null;

		} else if (findNode.leftChild != null && findNode.leftChild.value == N) {
			findNode.leftChild = null;

		} else if (findNode.leftChild != null && findNode.rightChild.value == N) {
			findNode.rightChild = null;
		} else if (findNode.rightChild.value != N && findNode.rightChild.value != N) {
			if (findNode.value < N) {
				System.out.println("#1");
				delete(N, findNode.rightChild);
			} else if (findNode.value > N) {
				System.out.println("#2");
				delete(N, findNode.leftChild);
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

		@Override
		public String toString() {
			return "Node [value=" + value + ", leftChild=" + leftChild + ", rightChild=" + rightChild + "]";
		}
	}
}

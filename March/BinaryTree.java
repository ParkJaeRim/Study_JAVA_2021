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
		print(rootNode);

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

	public static void print(Node start) {
		System.out.println(start.toString());
		if (start.leftChild != null) {
			print(start.leftChild);
		}
		if (start.rightChild != null) {
			print(start.rightChild);
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

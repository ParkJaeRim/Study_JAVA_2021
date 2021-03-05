import java.util.Scanner;

public class boj_5639_이진검색트리 {
	static Node rootNode = null;

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		while (sc.hasNext()) {
			int N = sc.nextInt();
			add(N);
		}
		postorder(rootNode);
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
import java.util.HashMap;

public class Trie {
	static Node root;

	public static void main(String[] args) {

		root = new Node();
		insert("abc", root);
		insert("abd", root);

		System.out.println("====insert End====");
		boolean ans1 = isExist("ab");
		System.out.println(ans1);
		boolean ans2 = isExist("abc");
		System.out.println(ans2);
		ans2 = isExist("abd");
		System.out.println(ans2);
		ans2 = isExist("bd");
		System.out.println(ans2);
		ans2 = isExist("a");
		System.out.println(ans2);

	}

	static class Node {
		boolean isLast;
		HashMap<Character, Node> childNode = new HashMap<>();

		public Node() {
			this.isLast = false;
			this.childNode = new HashMap<>();
		}

	}

	public static void insert(String word, Node startNode) {
		Node currentNode = startNode;
		for (int i = 0; i < word.length(); i++) {

			char w = word.charAt(i);

			if (!currentNode.childNode.containsKey(w)) {
				currentNode.childNode.put(w, new Node());
				currentNode = currentNode.childNode.get(w);
			} else {
				currentNode = currentNode.childNode.get(w);
			}
			if (i == word.length() - 1) {
				currentNode.isLast = true;
			}
		}
	}

	public static boolean isExist(String findword) {
		Node currentNode = root;
		for (int i = 0; i < findword.length(); i++) {
			char f = findword.charAt(i);
//			System.out.println();
//			System.out.println("NOW: " + f);
//			Iterator<Character> it = currentNode.childNode.keySet().iterator();
//			while (it.hasNext()) {
//				char x = it.next();
//				System.out.println(x + " " + currentNode.childNode.get(x).isLast);
//			}
//			System.out.println(currentNode.childNode.keySet());
			if (currentNode.childNode.containsKey(f)) {
				currentNode = currentNode.childNode.get(f);
			} else if (!currentNode.childNode.containsKey(f)) {
				return false;
			}
			if (i == findword.length() - 1 && currentNode.isLast) {
				return true;
			}
		}
		return false;
	}
}

import java.util.HashMap;
import java.util.Set;

public class kakao_2020_가사검색2_fail {
	static Node root, reverseRoot;
	static int cnt;

	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?", "?????" };
		solution(words, queries);
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		root = new Node(0);
		reverseRoot = new Node(0);
		for (String word : words) {
			insert(word, root);
			String reverseWord = "";
			for (int j = word.length() - 1; j >= 0; j--) {
				reverseWord += word.charAt(j);
			}
			insert(reverseWord, reverseRoot);
		}
		int idx = 0;

		for (String query : queries) {
			cnt = 0;
			if (query.charAt(0) != '?') {
				prefixFind(query, root);
			} else {
				String reverseQuery = "";
				for (int j = query.length() - 1; j >= 0; j--) {
					reverseQuery += query.charAt(j);
				}
				prefixFind(reverseQuery, reverseRoot);
			}
			answer[idx++] = cnt;
		}
		return answer;
	}

	public static void insert(String word, Node startNode) {
		Node currentNode = startNode;
		for (int i = 0; i < word.length(); i++) {
			char w = word.charAt(i);
			if (!currentNode.child.containsKey(w)) {
				currentNode.child.put(w, new Node(currentNode.depth + 1));
				currentNode = currentNode.child.get(w);
			} else {
				currentNode = currentNode.child.get(w);
			}
			if (i == word.length() - 1) {
				currentNode.isLast = true;
			}
		}
	}

	public static void prefixFind(String query, Node startNode) {
		Node currentNode = startNode;
		int queryLength = query.length();

		for (int i = 0; i < query.length(); i++) {
			char q = query.charAt(i);
			if (q == '?') {
				preDepth(currentNode, currentNode.depth, queryLength);
				return;
			} else if (currentNode.child.containsKey(q)) {
				currentNode = currentNode.child.get(q);
			} else {
				cnt = 0;
				return;
			}

		}
	}

	public static void preDepth(Node currentNode, int depth, int len) {
		if (len == depth) {
			if (currentNode.isLast) {
				cnt++;
			}
			return;
		}
		Set<Character> keySet = currentNode.child.keySet();
		for (char key : keySet) {
			preDepth(currentNode.child.get(key), depth + 1, len);
		}
	}

	static class Node {
		boolean isLast;
		int depth;
		HashMap<Character, Node> child = new HashMap<>();

		public Node(int depth) {
			this.isLast = false;
			this.depth = depth;
			this.child = new HashMap<>();
		}

		@Override
		public String toString() {
			return "Node [isLast=" + isLast + ", depth=" + depth + ", child=" + child + "]";
		}
	}
}

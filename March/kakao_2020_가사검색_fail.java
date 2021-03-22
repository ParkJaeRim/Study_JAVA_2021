import java.util.HashMap;
import java.util.Set;

public class kakao_2020_가사검색_fail {
	static Node root;
	static int cnt;

	public static void main(String[] args) {
		String[] words = { "frodo", "front", "frost", "frozen", "frame", "kakao" };
		String[] queries = { "fro??", "????o", "fr???", "fro???", "pro?" };
		solution(words, queries);
	}

	public static int[] solution(String[] words, String[] queries) {
		int[] answer = new int[queries.length];
		root = new Node(0);
		for (int i = 0; i < words.length; i++) {
			insert(words[i], root);
		}

		for (int i = 0; i < queries.length; i++) {
			cnt = 0;

			if (queries[i].charAt(0) != '?') {
				prefixFind(queries[i], root);
			} else {
				suffixFind(queries[i], root);
			}
			answer[i] = cnt;
		}

		for (int i = 0; i < answer.length; i++) {
			System.out.print(answer[i] + " ");
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

	public static void suffixFind(String query, Node startNode) {
		Node currentNode = startNode;
		for (int i = 0; i < query.length(); i++) {
			char q = query.charAt(i);
			if (q != '?') {
				sufDepth(currentNode, 0, i, query);
				return;
			}

		}
	}

	public static void againPreFind(Node currentNode, String query) {
		int queryLength = query.length();

		for (int i = 0; i < query.length(); i++) {
			char q = query.charAt(i);
			if (currentNode.child.containsKey(q)) {
				if (currentNode.child.get(q).isLast && i == query.length() - 1) {
					cnt++;
					return;
				}
				currentNode = currentNode.child.get(q);
			} else {
				return;
			}

		}
	}

	public static void sufDepth(Node currentNode, int depth, int len, String query) {
		if (len == depth) {
			String newQuery = query.substring(depth, query.length());
			againPreFind(currentNode, newQuery);
			return;
		}
		Set<Character> keySet = currentNode.child.keySet();
		for (char key : keySet) {
			sufDepth(currentNode.child.get(key), depth + 1, len, query);
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

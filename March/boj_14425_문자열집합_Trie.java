import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_14425_문자열집합_Trie {

	static Node rootNode;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		rootNode = new Node();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			add(word, rootNode);
		}

		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String word = st.nextToken();
			if (isExist(word)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

	public static boolean isExist(String findword) {
		Node currentNode = rootNode;
		for (int i = 0; i < findword.length(); i++) {
			char f = findword.charAt(i);
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

	public static void add(String word, Node node) {
		Node currentNode = node;
		for (int i = 0; i < word.length(); i++) {
			char w = word.charAt(i);
			if (currentNode.childNode.containsKey(w)) {
				currentNode = currentNode.childNode.get(w);
			} else {
				currentNode.childNode.put(w, new Node());
				currentNode = currentNode.childNode.get(w);
			}

			if (i == word.length() - 1) {
				currentNode.isLast = true;
			}

		}
	}

	static class Node {
		boolean isLast;
		HashMap<Character, Node> childNode = new HashMap<>();

		public Node() {
			this.isLast = false;
			this.childNode = new HashMap<>();
		}
	}
}

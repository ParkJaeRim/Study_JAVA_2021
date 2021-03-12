import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_5052_전화번호목록 {
	static Node rootNode;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int i = 0; i < tc; i++) {
			rootNode = new Node();
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			String[] num = new String[N];
			for (int j = 0; j < N; j++) {
				st = new StringTokenizer(br.readLine());
				String x = st.nextToken();
				num[j] = x;
//				add(x, rootNode);
			}

			Arrays.sort(num, new Comparator<String>() {
				@Override
				public int compare(String c1, String c2) {
					return c1.length() - c2.length();
				}
			});

			flag = false;
			for (String s : num) {
				add(s, rootNode);
			}
			if (flag) {
				System.out.println("NO");

			} else {
				System.out.println("YES");
			}
		}

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

			if (currentNode.isLast == true && i != word.length() - 1) {
				flag = true;
				return;
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

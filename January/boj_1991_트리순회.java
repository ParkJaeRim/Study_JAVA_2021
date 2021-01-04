import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1991_트리순회 {
	static Node[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		map = new Node[N];
		for (int i = 0; i < map.length; i++) {
			map[i] = new Node(0, 0, 0);
		}

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				int parent = st.nextToken().charAt(0) - 'A';
				int left = st.nextToken().charAt(0) - 'A';
				int right = st.nextToken().charAt(0) - 'A';
//				System.out.println(parent + " " + left + " " + right);

				map[parent] = new Node(map[parent].parent, left, right);
				if (left != -19) {
					map[left] = new Node(parent, map[left].left, map[left].right);

				}
				if (right != -19) {
					map[right] = new Node(parent, map[right].left, map[right].right);

				}
			}
		}

        PreOrder(0);
        System.out.println();
        InOrder(0);
        System.out.println();
		PostOrder(0);
		

	}

	public static void PreOrder(int now) {
		System.out.print((char) (now + 'A'));
		if (map[now].left != -19) {
			PreOrder(map[now].left);
		}
		if (map[now].right != -19) {
			PreOrder(map[now].right);
		}
	}

	public static void InOrder(int now) {
		if (map[now].left != -19) {
			InOrder(map[now].left);
		}
		System.out.print((char) (now + 'A'));
		if (map[now].right != -19) {
			InOrder(map[now].right);
		}
	}

	public static void PostOrder(int now) {
		if (map[now].left != -19) {
			PostOrder(map[now].left);
		}
		if (map[now].right != -19) {
			PostOrder(map[now].right);
		}
		System.out.print((char) (now + 'A'));

	}

	static class Node {
		int parent;
		int left;
		int right;

		public Node(int parent, int left, int right) {
			this.parent = parent;
			this.left = left;
			this.right = right;
		}

	}
}

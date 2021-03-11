import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_1967_트리의지름 {
	static int N;
	static int maxV;
	static int maxIdx;
	static ArrayList<Node>[] tree;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		tree = new ArrayList[N + 1];

		for (int i = 0; i < N + 1; i++) {
			tree[i] = new ArrayList<>();
		}

		for (int i = 1; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int parent = Integer.parseInt(st.nextToken());
			int child = Integer.parseInt(st.nextToken());
			int weight = Integer.parseInt(st.nextToken());
			tree[parent].add(new Node(child, weight));
			tree[child].add(new Node(parent, weight));
		}
		boolean[] visited = new boolean[N + 1];
		int result = 0;
		visited[1] = true;
		maxV = Integer.MIN_VALUE;
		dfs(1, 0, visited);

		maxV = Integer.MIN_VALUE;
		visited = new boolean[N + 1];
		visited[maxIdx] = true;
		dfs(maxIdx, 0, visited);
		System.out.println(maxV);
	}

	private static void dfs(int cur, int sum, boolean[] visited) {
		if (sum > maxV) {
			maxV = sum;
			maxIdx = cur;
		}

		for (int i = 0; i < tree[cur].size(); i++) {
			int next = tree[cur].get(i).friend;
			int value = tree[cur].get(i).value;
			if (visited[next]) {
				continue;
			}
			visited[next] = true;
			dfs(next, sum + value, visited);
			visited[next] = false;
		}
	}

	static class Node {
		int friend;
		int value;

		Node(int friend, int value) {
			this.friend = friend;
			this.value = value;
		}
	}
}

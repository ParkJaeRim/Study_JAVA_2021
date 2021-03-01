import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_1197_최소스패닝트리 {
	static PriorityQueue<Point> pq;
	static int[] parent;
	static int ans;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());
		pq = new PriorityQueue<>();
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			pq.add(new Point(start - 1, end - 1, value));
		}

		parent = new int[V];
		for (int i = 0; i < parent.length; i++) {
			parent[i] = i;
		}
		ans = 0;

		while (!pq.isEmpty()) {
			Point p = pq.poll();
			if (findRoot(p.start) == findRoot(p.end)) {
				continue;
			} else {
				union(p.start, p.end);
				ans += p.value;
			}
		}
		System.out.println(ans);
	}

	public static void union(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		parent[aRoot] = bRoot;
	}

	public static int findRoot(int x) {
		if (x == parent[x]) {
			return x;
		}
		parent[x] = findRoot(parent[x]);
		return parent[x];
	}

	static class Point implements Comparable<Point> {
		int start;
		int end;
		int value;

		public Point(int start, int end, int value) {
			this.start = start;
			this.end = end;
			this.value = value;
		}

		@Override
		public int compareTo(Point o) {
			return this.value - o.value;
		}

	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class boj_1753_최단경로 {
	static int[] cost;
	static int[][] map;
	static int V;
	static boolean[] visited;
	static ArrayList<Edge>[] al;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		V = Integer.parseInt(st.nextToken());
		int E = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int K = Integer.parseInt(st.nextToken());

		visited = new boolean[V + 1];
		cost = new int[V + 1];
		al = new ArrayList[V + 1];

		for (int i = 0; i < al.length; i++) {
			al[i] = new ArrayList<Edge>();
		}
		for (int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int start = Integer.parseInt(st.nextToken());
			int end = Integer.parseInt(st.nextToken());
			int value = Integer.parseInt(st.nextToken());
			al[start].add(new Edge(end, value));
		}

		for (int i = 0; i < al.length; i++) {
			Collections.sort(al[i]);
		}

		int INF = 987654321;
		Arrays.fill(cost, INF);
		cost[K] = 0;
		go(K);

		for (int i = 1; i < cost.length; i++) {
			if (cost[i] == INF) {
				System.out.println("INF");
			} else {
				System.out.println(cost[i] + " ");
			}
		}
	}

	public static void go(int startNode) {
		visited[startNode] = true;
		for (int j = 0; j < al[startNode].size(); j++) {
			if (al[startNode].get(j).v + cost[startNode] < cost[al[startNode].get(j).x]) {
				cost[al[startNode].get(j).x] = al[startNode].get(j).v + cost[startNode];
			}
		}

		int minV = Integer.MAX_VALUE;
		int minIdx = -1;
		for (int i = 1; i < cost.length; i++) {
			if (cost[i] < minV && !visited[i]) {
				minV = cost[i];
				minIdx = i;
			}
		}
		if (minIdx != -1) {
			go(minIdx);
		} else {
			return;
		}
	}

	static class Edge implements Comparable<Edge> {
		int x;
		int v;

		public Edge(int x, int v) {
			this.x = x;
			this.v = v;
		}

		@Override
		public int compareTo(Edge e) {
			if (this.x == e.x) {
				return this.v - e.v;
			}
			return this.x - e.x;
		}

	}
}

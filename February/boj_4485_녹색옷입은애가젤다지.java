import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_4485_녹색옷입은애가젤다지 {

	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };
	static int[][] map, cost;
	static int N;
	static int INF = 987654321;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 1;

		while (true) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			if (N == 0) {
				break;
			}
			map = new int[N][N];
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				for (int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			cost = new int[N][N];
			for (int i = 0; i < cost.length; i++) {
				Arrays.fill(cost[i], INF);
			}
			cost[0][0] = map[0][0];
			int ans = go(0, 0);
			System.out.println("Problem " + cnt + ": " + ans);
			cnt++;
		}
	}

	public static int go(int y, int x) {
		Queue<Node> que = new LinkedList<>();
		que.offer(new Node(y, x));

		while (!que.isEmpty()) {
			Node n = que.poll();
			int cy = n.y;
			int cx = n.x;
			for (int d = 0; d < 4; d++) {
				if (isSafe(cy + dy[d], cx + dx[d])) {
					if (cost[cy + dy[d]][cx + dx[d]] != 0
							&& map[cy + dy[d]][cx + dx[d]] + cost[cy][cx] < cost[cy + dy[d]][cx + dx[d]]) {
						que.offer(new Node(cy + dy[d], cx + dx[d]));
						cost[cy + dy[d]][cx + dx[d]] = map[cy + dy[d]][cx + dx[d]] + cost[cy][cx];
					}
				}
			}
		}
		return cost[N - 1][N - 1];
	}

	static class Node {
		int y;
		int x;

		public Node(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

	}

	public static boolean isSafe(int y, int x) {
		if (y < N && x < N && y >= 0 && x >= 0) {
			return true;
		}
		return false;
	}
}

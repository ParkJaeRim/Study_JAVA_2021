import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_16197_두동전_bfs {
	static int N, M;
	static Queue<Coin> que;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, -1, 0, 1 };
	static char[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new char[N][M];
		que = new LinkedList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			map[i] = st.nextToken().toCharArray();
			for (int j = 0; j < map[i].length; j++) {
				if (map[i][j] == 'o')
					que.offer(new Coin(i, j, 0));
			}
		}
		go();
	}

	private static void go() {
		while (!que.isEmpty()) {
			Coin first = que.poll();
			Coin second = que.poll();
			if (first.cnt >= 10) {
				System.out.println(-1);
				return;
			}

			for (int d = 0; d < 4; d++) {
				int fy = first.y + dy[d];
				int fx = first.x + dx[d];
				int sy = second.y + dy[d];
				int sx = second.x + dx[d];

				if (!isSafe(fy, fx) && !isSafe(sy, sx)) {
					continue;
				} else if (!isSafe(fy, fx) || !isSafe(sy, sx)) {
					System.out.println(first.cnt + 1);
					return;
				} else {
					if (map[fy][fx] == '#') {
						fy = first.y;
						fx = first.x;
					}
					if (map[sy][sx] == '#') {
						sy = second.y;
						sx = second.x;
					}
					que.offer(new Coin(fy, fx, first.cnt + 1));
					que.offer(new Coin(sy, sx, first.cnt + 1));
				}

			}
		}

	}

	public static boolean isSafe(int y, int x) {
		if (y < N && y >= 0 && x < M && x >= 0) {
			return true;
		}
		return false;
	}

	static class Coin {
		int y;
		int x;
		int cnt;

		public Coin(int y, int x, int cnt) {
			this.y = y;
			this.x = x;
			this.cnt = cnt;
		}

	}

	public static void print(char[][] m) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}
}

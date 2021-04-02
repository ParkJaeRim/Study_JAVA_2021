import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_20058_마법사상어와파이어스톰 {
	static int[][] map;
	static boolean[][] visited;
	static int N, mapLen, dunguri, tot;
	static int[] dy = { 1, 0, -1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int Q = Integer.parseInt(st.nextToken());
		mapLen = (int) Math.pow(2, N);
		map = new int[mapLen][mapLen];
		for (int i = 0; i < mapLen; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < mapLen; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < Q; i++) {
			int L = Integer.parseInt(st.nextToken());
			L = (int) Math.pow(2, L);
			pick(L);
			melt();
		}
		
		int maxV = Integer.MIN_VALUE;

		tot = 0;
		visited = new boolean[mapLen][mapLen];
		for (int i = 0; i < mapLen; i++) {
			for (int j = 0; j < mapLen; j++) {
				dunguri = 0;
				if (map[i][j] > 0 && !visited[i][j]) {
					calc(i, j);
				}
				maxV = Math.max(dunguri, maxV);
			}
		}
		System.out.println(tot);
		System.out.println(maxV);
	}

	public static void pick(int len) {
		for (int i = 0; i < mapLen / len; i++) {
			for (int j = 0; j < mapLen / len; j++) {
				rotate((i * len), (j * len), len);
			}
		}
	}

	static void rotate(int y, int x, int len) {
		int[][] rotate = new int[len][len];

		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate[i].length; j++) {
				rotate[i][j] = map[len - 1 - j + y][i + x];
			}
		}
		for (int i = 0; i < rotate.length; i++) {
			for (int j = 0; j < rotate[i].length; j++) {
				map[i + y][j + x] = rotate[i][j];
			}
		}
	}

	public static void melt() {
		Queue<Point> que = new LinkedList<>();
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				int cnt = 0;
				for (int d = 0; d < 4; d++) {
					if (isBoundary(i + dy[d], j + dx[d]) && map[i + dy[d]][j + dx[d]] > 0) {
						cnt++;
					}
				}
				if (cnt < 3) {
					que.offer(new Point(i, j));
				}
			}
		}

		while (!que.isEmpty()) {
			Point p = que.poll();
			map[p.y][p.x]--;
		}
	}

	public static void calc(int i, int j) {
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(i, j));
		visited[i][j] = true;
		dunguri++;
		tot += map[i][j];
		while (!que.isEmpty()) {
			Point p = que.poll();
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];

				if (isBoundary(ny, nx) && map[ny][nx] > 0 && !visited[ny][nx]) {
					visited[ny][nx] = true;
					tot += map[ny][nx];
					que.offer(new Point(ny, nx));
					dunguri++;
				}
			}
		}

	}

	public static boolean isBoundary(int y, int x) {
		if (y < mapLen && x < mapLen && y >= 0 && x >= 0) {
			return true;
		}
		return false;
	}

	static class Point {
		int y;
		int x;

		public Point(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}

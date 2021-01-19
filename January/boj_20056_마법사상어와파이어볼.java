import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_20056_마법사상어와파이어볼 {
	static int[] dy = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int[] dx = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static LinkedList<Ball>[][] map;
	static LinkedList<Ball>[][] next;
	// 0:북 , 1:북동, 2:동, 3:동남, 4:남, 5:남서, 6:서, 7:북서
	static int N, M, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new LinkedList[N][N];
		next = new LinkedList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new LinkedList<Ball>();
				next[i][j] = new LinkedList<Ball>();
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			map[r][c].add(new Ball(w, v, d));
		}

		for (int i = 0; i < K; i++) {
			System.err.println(i);
			print(map);
			move();
			cal();
			System.out.println("===================");
			print(map);
		}

		int result = 0;
		for (int i = 0; i < map.length; i++) {S
			for (int j = 0; j < map[i].length; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					result += map[i][j].get(k).w;
				}
			}
		}
		System.out.println(result);
	}

	public static void move() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() != 0) {
					Ball b = map[i][j].get(0);
					map[i][j].remove();
					int nx = i + dx[b.d] * (b.v % N);
					int ny = j + dy[b.d] * (b.v % N);
					nx = checkBoundary(nx);
					ny = checkBoundary(ny);

					next[ny][nx].add(new Ball(b.w, b.v, b.d));
				}
			}
		}
	}

	private static int checkBoundary(int i) {
		if (i < 0)
			i += N;
		if (i >= N)
			i -= N;
		return i;
	}

	public static void cal() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (next[i][j].size() >= 2) {
					int totSize = 0;
					int totVel = 0;
					int totDir = 0;
					int tmpSize = next[i][j].size();
					for (int k = 0; k < next[i][j].size(); k++) {
						totSize += next[i][j].get(k).w;
						totVel += next[i][j].get(k).v;
						totDir += next[i][j].get(k).d;
					}
					if (totSize / 5 != 0) {
						for (int k = 0; k < 4; k++) {
							if (totDir % 2 == 0) {
								map[i][j].add(new Ball(totSize / 5, totVel / tmpSize, 2 * k));
							} else {
								map[i][j].add(new Ball(totSize / 5, totVel / tmpSize, 2 * k + 1));
							}
						}
					}
				} else if (next[i][j].size() == 1) {
					map[i][j].add(next[i][j].get(0));
				}
				next[i][j].clear();
			}
		}
	}

	static class Ball {
		int w;
		int v;
		int d;

		public Ball(int w, int v, int d) {
			this.w = w;
			this.v = v;
			this.d = d;
		}
	}

	public static void print(LinkedList<Ball>[][] map) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (map[i][j].size() == 0) {
					System.out.print("  X   ");
				} else {
					for (int k = 0; k < map[i][j].size(); k++) {
						System.out.print(map[i][j].get(k).v + ", " + map[i][j].get(k).d + " ");
					}
				}
			}
			System.out.println();

		}
	}

}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_파이어볼 {
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };
	static int N, M, K;
	static ArrayList<Ball>[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new ArrayList[N][N];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				map[i][j] = new ArrayList<Ball>();
			}
		}
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken()) - 1;
			int c = Integer.parseInt(st.nextToken()) - 1;
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());

			map[r][c].add(new Ball(m, s, d));
		}

		while (K != 0) {
			move();
			K--;
		}

		int result = 0;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					result += map[i][j].get(k).m;
				}
			}
		}
		System.out.println(result);

	}

	public static void move() {
		ArrayList<Ball>[][] newMap = new ArrayList[N][N];
		for (int i = 0; i < newMap.length; i++) {
			for (int j = 0; j < newMap[i].length; j++) {
				newMap[i][j] = new ArrayList<Ball>();
			}
		}
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < map[i][j].size(); k++) {
					int size = map[i][j].get(k).m;
					int vel = map[i][j].get(k).s;
					int dir = map[i][j].get(k).d;

					int ny = i + (dy[dir] * vel);
					int nx = j + (dx[dir] * vel);

					if (N % 2 == 0) {
						if (ny < 0 && dy[dir] != 0) {
							ny = Math.abs(ny + N);
						} else if (ny == N && dy[dir] != 0) {
							ny = 0;
						} else if (ny > N && dy[dir] != 0) {
							ny = (ny + 1) % N;
						}

						if (nx < 0 && dx[dir] != 0) {
							nx = Math.abs(nx + N);
						} else if (nx == N && dx[dir] != 0) {
							nx = 0;
						} else if (nx > N && dx[dir] != 0) {
							nx = (nx + 1) % N;
						}
					}

					else {
						if (ny < 0 && dy[dir] != 0) {
							ny = Math.abs(ny + N);
						} else if (ny == N && dy[dir] != 0) {
							ny = 0;
						} else if (ny > N && dy[dir] != 0) {
							ny = (ny + 1) % N - 1;
						}

						if (nx < 0 && dx[dir] != 0) {
							nx = Math.abs(nx + N);
						} else if (nx == N && dx[dir] != 0) {
							nx = 0;
						} else if (nx > N && dx[dir] != 0) {
							nx = (nx + 1) % N - 1;
						}
					}

					newMap[ny][nx].add(new Ball(map[i][j].get(k).m, map[i][j].get(k).s, map[i][j].get(k).d));

				}
				map[i][j].clear();
			}
		}
		calculate(newMap);
	}

	public static void calculate(ArrayList<Ball>[][] lst) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (lst[i][j].size() >= 2) {
					int totSize = 0;
					int totVel = 0;
					int totDir = 0;
					int tmpSize = lst[i][j].size();
					for (int k = 0; k < lst[i][j].size(); k++) {
						totSize += lst[i][j].get(k).m;
						totVel += lst[i][j].get(k).s;
						totDir += lst[i][j].get(k).d;
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
				} else if (lst[i][j].size() == 1) {
					map[i][j].add(lst[i][j].get(0));
				}
				lst[i][j].clear();
			}
		}
//		print(map)
	}

	static class Ball {
		int m;
		int s;
		int d;

		public Ball(int m, int s, int d) {
			this.m = m;
			this.s = s;
			this.d = d;
		}
	}

	public static void print(ArrayList<Ball>[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				for (int k = 0; k < m[i][j].size(); k++) {
					System.out.print(m[i][j].get(k).m + " " + m[i][j].get(k).s + " " + m[i][j].get(k).d + ", ");

				}
				System.out.print(" ");
				if (m[i][j].size() == 0) {
					System.out.print("^^^^^ ");
				}
			}
			System.out.println();
		}
		System.out.println();
	}
}

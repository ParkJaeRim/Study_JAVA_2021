import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_15683_감시 {
	static int[] dy = { -1, 0, 1, 0 };
	static int[] dx = { 0, 1, 0, -1 };

	// 상 우 하 좌
	static int R, C;
	static int ans;
	static LinkedList<CCTV> lst;
	static int[][] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new int[R][C];

		for (int i = 0; i < R; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < C; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		ans = Integer.MAX_VALUE;
		lst = new LinkedList<>();

		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (1 <= map[i][j] && map[i][j] < 6) {
					lst.add(new CCTV(i, j, map[i][j]));

				}
			}
		}
		cctv(0, map);

		System.out.println(ans);
	}

	public static void cctv(int idx, int[][] originMap) {
		int[][] tmp = new int[R][C];
		if (idx == lst.size()) {
			print(originMap);
			System.out.println();
			int cnt = 0;
			for (int i = 0; i < originMap.length; i++) {
				for (int j = 0; j < originMap[i].length; j++) {
					if (originMap[i][j] == 0) {
						cnt++;
					}
				}
			}
			ans = Math.min(cnt, ans);
			return;
		}

		int y = lst.get(idx).y;
		int x = lst.get(idx).x;
		int num = lst.get(idx).num;
		if (num == 1) {
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < R; i++) {
					tmp[i] = Arrays.copyOf(originMap[i], C);
				}

				cover(y, x, d, tmp);
				cctv(idx + 1, tmp);
			}
		} else if (num == 2) {
			for (int d = 0; d < 2; d++) {
				for (int i = 0; i < R; i++) {
					tmp[i] = Arrays.copyOf(originMap[i], C);
				}
				cover(y, x, d, tmp);
				cover(y, x, (d + 2) % 4, tmp);
				cctv(idx + 1, tmp);

			}
		} else if (num == 3) {
			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < R; i++) {
					tmp[i] = Arrays.copyOf(originMap[i], C);
				}
				cover(y, x, d, tmp);
				cover(y, x, (d + 1) % 4, tmp);
				cctv(idx + 1, tmp);

			}
		} else if (num == 4) {

			for (int d = 0; d < 4; d++) {
				for (int i = 0; i < R; i++) {
					tmp[i] = Arrays.copyOf(originMap[i], C);
				}
				cover(y, x, d, tmp);
				cover(y, x, (d + 1) % 4, tmp);
				cover(y, x, (d + 2) % 4, tmp);
				cctv(idx + 1, tmp);

			}
		} else {
			for (int i = 0; i < R; i++) {
				tmp[i] = Arrays.copyOf(originMap[i], C);
			}
			cover(y, x, 0, tmp);
			cover(y, x, 1, tmp);
			cover(y, x, 2, tmp);
			cover(y, x, 3, tmp);
			cctv(idx + 1, tmp);

		}

	}


	public static void cover(int y, int x, int d, int[][] m) {
		int ny = -1;
		int nx = -1;
		if (d == 0 || d == 2) {
			for (int i = 0; i < R; i++) {
				ny = y + (i * dy[d]);
				nx = x;
				if (safe(ny, nx)) {
					if (map[ny][nx] != 6) {
						m[ny][nx] = 7;
					} else if (map[ny][nx] == 6) {
						return;
					}
				}
			}
		} else {
			for (int i = 0; i < C; i++) {
				nx = x + (i * dx[d]);
				ny = y;
				if (safe(ny, nx)) {
					if (map[ny][nx] != 6) {
						m[ny][nx] = 7;
					} else if (map[ny][nx] == 6) {
						return;
					}
				}
			}
		}

	}

	public static boolean safe(int y, int x) {
		if (y < R && x < C && y >= 0 && x >= 0) {
			return true;
		}
		return false;
	}

	static class CCTV {
		int y;
		int x;
		int num;

		public CCTV(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}

	}

	public static void print(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}

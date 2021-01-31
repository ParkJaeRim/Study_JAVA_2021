import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_2382_미생물격리 {
	static int[] dy = { 0, -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 0, -1, 1 };
	static int N;

// 약 1시간 30분 소요
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			// 0이거나 N-1 경계 취급
			int[][] map = new int[N][N];
			ArrayList<Dust> al = new ArrayList<>();
			for (int i = 0; i < K; i++) {
				st = new StringTokenizer(br.readLine());
				int r = Integer.parseInt(st.nextToken());
				int c = Integer.parseInt(st.nextToken());
				int size = Integer.parseInt(st.nextToken());
				int dir = Integer.parseInt(st.nextToken());
				al.add(new Dust(r, c, size, dir));
				map[r][c]++;
			}

			while (M != 0) {
				int[][] newCell = new int[N][N];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < N; j++) {
						if (map[i][j] == 1) {
							for (int k = 0; k < al.size(); k++) {
								if (al.get(k).y == i && al.get(k).x == j) {
									int dir = al.get(k).dir;
									int ny = i + dy[dir];
									int nx = j + dx[dir];
									if (isSafe(ny, nx)) {
										newCell[ny][nx]++;
										al.add(new Dust(ny, nx, al.get(k).size, dir));
									} else if (ny == 0 || ny == N - 1 || nx == 0 || nx == N - 1) {
										newCell[ny][nx]++;
										if (dir == 1) {
											dir = 2;
										} else if (dir == 2) {
											dir = 1;
										} else if (dir == 3) {
											dir = 4;
										} else if (dir == 4) {
											dir = 3;
										}
										al.add(new Dust(ny, nx, al.get(k).size / 2, dir));
									}
									map[i][j]--;
									al.remove(k);
									break;
								}
							}
						}
					}
				}
				for (int i = 0; i < N; i++) {
					map[i] = Arrays.copyOf(newCell[i], N);
				}
				ArrayList<Dust> newAl = new ArrayList<>();

				Queue<Dust> pq = new LinkedList<>();

				for (int i = 0; i < map.length; i++) {
					for (int j = 0; j < map[i].length; j++) {
						if (map[i][j] >= 2) {
							for (int k = 0; k < al.size(); k++) {
								if (al.get(k).y == i && al.get(k).x == j) {
									pq.add(al.get(k));
								}
							}
							long size = 0;
							long maxV = Integer.MIN_VALUE;
							int dir = -1;
							while (!pq.isEmpty()) {
								Dust d = pq.poll();
								size += d.size;
								if (maxV < d.size) {
									dir = d.dir;
									maxV = d.size;
								}
							}
							map[i][j] = 1;
							newAl.add(new Dust(i, j, size, dir));
						} else if (map[i][j] == 1) {
							for (int k = 0; k < al.size(); k++) {
								if (al.get(k).y == i && al.get(k).x == j) {
									newAl.add(al.get(k));
								}
							}
						}
					}
				}
				al.clear();
				al.addAll(newAl);
				M--;
			}
			int answer = 0;
			for (int i = 0; i < al.size(); i++) {
				answer += al.get(i).size;
			}
			System.out.println("#" + t + " " + answer);

		}

	}

	public static boolean isSafe(int y, int x) {
		if (y > 0 && y < N - 1 && x > 0 && x < N - 1) {
			return true;
		}
		return false;
	}

	static class Dust {
		int y;
		int x;

		long size;
		int dir;

		public Dust(int y, int x, long size, int dir) {
			this.y = y;
			this.x = x;
			this.size = size;
			this.dir = dir;
		}

		public Dust(long size, int dir) {
			this.size = size;
			this.dir = dir;
		}

	}
}

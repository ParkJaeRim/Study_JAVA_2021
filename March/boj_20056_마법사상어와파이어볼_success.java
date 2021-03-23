import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public interface boj_20056_마법사상어와파이어볼_success {
	static int[] dy = { -1, -1, 0, 1, 1, 1, 0, -1 };
	static int[] dx = { 0, 1, 1, 1, 0, -1, -1, -1 };

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		int K = Integer.parseInt(st.nextToken());

		LinkedList<Ball>[][][] ball = new LinkedList[N][N][K * 2 + 1];
		for (int i = 0; i < ball.length; i++) {
			for (int j = 0; j < ball[i].length; j++) {
				for (int k = 0; k < ball[i][j].length; k++) {
					ball[i][j][k] = new LinkedList<>();
				}
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int r = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			int m = Integer.parseInt(st.nextToken());
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			ball[r - 1][c - 1][0].add(new Ball(m, s, d));
		}

		int k = 0;
		while (k != K * 2) {
			for (int i = 0; i < ball.length; i++) {
				for (int j = 0; j < ball[i].length; j++) {
					while (!ball[i][j][k].isEmpty()) {
						Ball tmpball = ball[i][j][k].remove(0);
						int y = i;
						int x = j;
						int dir = tmpball.d;
						int speed = tmpball.s;
						int ny = y + dy[dir] * (speed % N);
						int nx = x + dx[dir] * (speed % N);
						nx = checkBoundary(nx, N);
						ny = checkBoundary(ny, N);
						ball[ny][nx][k + 1].add(new Ball(tmpball.m, speed, tmpball.d));
					}
				}
			}
			k++;
			for (int i = 0; i < ball.length; i++) {
				for (int j = 0; j < ball[i].length; j++) {
					int newM = 0;
					int newS = 0;
					int newDir = 0;
					boolean odd = false, even = false;
					if (ball[i][j][k].size() >= 2) {
						int storeSize = ball[i][j][k].size();
						while (!ball[i][j][k].isEmpty()) {
							Ball tmpball = ball[i][j][k].remove(0);
							newM += tmpball.m;
							newS += tmpball.s;
							if ((tmpball.d % 2) == 1) {
								odd = true;
							} else {
								even = true;
							}
						}
						newM /= 5;
						newS = newS / storeSize;
						if (newM > 0 && (odd && even)) {
							ball[i][j][k + 1].add(new Ball(newM, newS, 1));
							ball[i][j][k + 1].add(new Ball(newM, newS, 3));
							ball[i][j][k + 1].add(new Ball(newM, newS, 5));
							ball[i][j][k + 1].add(new Ball(newM, newS, 7));
						} else if (newM > 0) {
							ball[i][j][k + 1].add(new Ball(newM, newS, 0));
							ball[i][j][k + 1].add(new Ball(newM, newS, 2));
							ball[i][j][k + 1].add(new Ball(newM, newS, 4));
							ball[i][j][k + 1].add(new Ball(newM, newS, 6));
						}
					} else if (ball[i][j][k].size() == 1) {
						Ball tmp = ball[i][j][k].remove(0);
						ball[i][j][k + 1].add(new Ball(tmp.m, tmp.s, tmp.d));
					}

				}
			}
			k++;
		}

		int ans = 0;
		for (int i = 0; i < ball.length; i++) {
			for (int j = 0; j < ball[i].length; j++) {
				for (int s = 0; s < ball[i][j][k].size(); s++) {
					ans += ball[i][j][k].get(s).m;
				}
			}
		}
		System.out.println(ans);
	}

	public static int checkBoundary(int i, int N) {
		if (i < 0)
			i += N;
		if (i >= N)
			i -= N;
		return i;
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
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class boj_2580_스도쿠 {
	static int N;
	static StringTokenizer st;
	static int[][] map;
	static ArrayList<Point> lst;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = 9;
		map = new int[N][N];
		lst = new ArrayList<>();

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if (map[i][j] == 0) {
					lst.add(new Point(i, j));
				}
			}
		}

		game(0);

	}

	public static void game(int idx) {
		if (idx == lst.size()) {
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[i].length; j++) {
					System.out.print(map[i][j] + " ");
				}
				System.out.println();
			}
			System.exit(0);
		}
		Point p = lst.get(idx);
		for (int i = 1; i <= 9; i++) {
			if (check(p.y, p.x, i)) {
				map[p.y][p.x] = i;
				game(idx + 1);
				map[p.y][p.x] = 0;
			}
		}
	}

	public static boolean check(int y, int x, int guess_num) {
		for (int i = 0; i < N; i++) {
			if (map[y][i] == guess_num) {
				return false;
			}
		}
		for (int i = 0; i < N; i++) {
			if (map[i][x] == guess_num) {
				return false;
			}
		}

		int square_y = y / 3;
		int square_x = x / 3;

		for (int i = square_y * 3; i < (square_y + 1) * 3; i++) {
			for (int j = square_x * 3; j < (square_x + 1) * 3; j++) {
				if (map[i][j] == guess_num) {
					return false;
				}
			}
		}
		return true;
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

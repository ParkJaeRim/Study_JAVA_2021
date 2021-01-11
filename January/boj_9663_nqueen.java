import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_9663_nqueen {
	static int N, cnt;
	static int[] map;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());

		cnt = 0;
		map = new int[N + 1];

		for (int i = 1; i <= N; i++) {
			map[1] = i;
			backtracking(1);
		}
		System.out.println(cnt);
	}

	public static void backtracking(int row) {
		if (row == N) {
			cnt++;
			return;
		}
		for (int i = 1; i <= N; i++) {
			map[row + 1] = i;
			if (check(row + 1)) {
				backtracking(row + 1);
			} else {
				map[row + 1] = 0;
			}
		}
	}

	public static boolean check(int row) {
		for (int i = 1; i < row; i++) {
			if (map[row] == map[i]) {
				return false;
			} else if (Math.abs(map[i] - map[row]) == Math.abs(i - row)) {
				return false;
			}

		}
		return true;
	}
}

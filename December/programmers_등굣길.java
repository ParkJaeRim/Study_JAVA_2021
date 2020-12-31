public class programmers_등굣길 {
	public static void main(String[] args) {
		int m = 4;
		int n = 3;
		int[][] puddles = { { 3, 1 }, { 1, 4 }, { 2, 2 } };
//		int[][] puddles = { { 2, 2 } };
		solution(m, n, puddles);
	}

	public static int solution(int m, int n, int[][] puddles) {
		int[][] map = new int[n][m];

		for (int i = 0; i < puddles.length; i++) {
			map[puddles[i][1] - 1][puddles[i][0] - 1] = -1;
		}

		for (int i = 0; i < n; i++) {
			if (map[i][0] == -1) {
				break;
			}
			map[i][0] = 1;
		}
		for (int i = 0; i < m; i++) {
			if (map[0][i] == -1) {
				break;
			}
			map[0][i] = 1;
		}

		for (int i = 1; i < map.length; i++) {
			for (int j = 1; j < map[i].length; j++) {
				if (map[i][j] != -1) {
					if (map[i][j - 1] == -1) {
						map[i][j] = map[i - 1][j] % 1000000007;
					} else if (map[i - 1][j] == -1) {
						map[i][j] = map[i][j - 1] % 1000000007;
					} else {
						map[i][j] = (map[i - 1][j] + map[i][j - 1]) % 1000000007;
					}
				}
			}
		}
		return map[n - 1][m - 1] % 1000000007;
	}
}

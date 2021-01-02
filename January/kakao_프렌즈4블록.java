
public class kakao_프렌즈4블록 {
	static int cnt;
	static boolean flag;

	public static void main(String[] args) {
//		String[] board = { "CCBDE", "AAADE", "AAABF", "CCBBF" };
		String[] board = { "TTTANT", "RRFACC", "RRRFCC", "TRRRAA", "TTMMMF", "TMMTTJ" };
		int m = 6;
		int n = 6;
		cnt = 0;
		System.out.println(solution(m, n, board));

	}

	public static int solution(int m, int n, String[] board) {
		char[][] map = new char[m][n];
		for (int i = 0; i < board.length; i++) {
			map[i] = board[i].toCharArray();
		}
		while (true) {
			flag = false;
			find(map, m, n);
			if (!flag) {
				break;
			}
		}
		return cnt;
	}

	public static void find(char[][] map, int m, int n) {
		boolean[][] visited = new boolean[m][n];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map[i].length; j++) {
				if (!visited[i][j]) {
					if (map[i][j] != '0' && (i + 1 < m && j + 1 < n) && (map[i][j] == map[i + 1][j]
							&& map[i][j + 1] == map[i + 1][j + 1] && map[i][j] == map[i + 1][j + 1])) {
						visited[i][j] = true;
						flag = true;
					}
				}
			}
		}
		if (!flag) {
			return;
		}

		for (int i = 0; i < visited.length; i++) {
			for (int j = 0; j < visited[i].length; j++) {
				if (visited[i][j]) {
					if (map[i][j] != '0') {
						map[i][j] = '0';
						cnt++;
					}
					if (map[i][j + 1] != '0') {
						map[i][j + 1] = '0';
						cnt++;
					}
					if (map[i + 1][j] != '0') {
						map[i + 1][j] = '0';
						cnt++;
					}
					if (map[i + 1][j + 1] != '0') {
						map[i + 1][j + 1] = '0';
						cnt++;
					}
				}
			}
		}
		down(map, m, n);
	}

	public static void down(char[][] map, int m, int n) {
		for (int x = 0; x < n; x++) {
			for (int y = m - 1; y >= 0; y--) {
				if (map[y][x] == '0') {
					for (int z = y - 1; z >= 0; z--) {
						if (map[z][x] != '0') {
							map[y][x] = map[z][x];
							map[z][x] = '0';
							break;
						}
					}
				}
			}
		}
	}
}

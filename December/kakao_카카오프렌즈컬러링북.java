import java.util.LinkedList;
import java.util.Queue;

public class kakao_카카오프렌즈컬러링북 {
	static boolean[][] visited;
	static int EachCnt;
	static int[] dy = { -1, 0, 0, 1 };
	static int[] dx = { 0, 1, -1, 0 };

	public static void main(String[] args) {
		int m = 6;
		int n = 4;
		int[][] pic = { { 1, 1, 1, 0 }, { 1, 2, 2, 0 }, { 1, 0, 0, 1 }, { 0, 0, 0, 1 }, { 0, 0, 0, 3 },
				{ 0, 0, 0, 3 } };
		solution(m, n, pic);
	}

	public static int[] solution(int m, int n, int[][] picture) {
		int numberOfArea = 0;
		int maxSizeOfOneArea = 0;
		visited = new boolean[m][n];

		for (int i = 0; i < picture.length; i++) {
			for (int j = 0; j < picture[i].length; j++) {
				if (picture[i][j] != 0 && !visited[i][j]) {
					EachCnt = 1;
					bfs(i, j, picture);
					System.out.println(EachCnt);
					numberOfArea++;
					if (maxSizeOfOneArea < EachCnt) {
						maxSizeOfOneArea = EachCnt;
					}
				}
			}
		}

		int[] answer = new int[2];
		answer[0] = numberOfArea;
		answer[1] = maxSizeOfOneArea;
		return answer;
	}

	public static void bfs(int i, int j, int[][] pic) {
		Queue<Edge> que = new LinkedList<>();
		que.offer(new Edge(i, j));
		visited[i][j] = true;

		while (!que.isEmpty()) {
			Edge e = que.poll();
			for (int d = 0; d < 4; d++) {
				int ty = e.y + dy[d];
				int tx = e.x + dx[d];

				if (!(ty < 0 || ty >= pic.length || tx < 0 || tx >= pic[i].length)) {
					if (pic[e.y][e.x] == pic[ty][tx] && !visited[ty][tx]) {
						que.offer(new Edge(ty, tx));
						visited[ty][tx] = true;
						EachCnt++;
					}
				}
			}
		}

	}

	static class Edge {
		int y;
		int x;

		public Edge(int y, int x) {
			this.y = y;
			this.x = x;
		}
	}
}
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_10971_외판원순회2 {
	static boolean[] visited;
	static int N;
	static int[][] map;
	static int cost, answer;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		visited = new boolean[N];
		answer = Integer.MAX_VALUE; 
		
		for (int i = 0; i < N; i++) {
			cost = 0;
			backtracking(i, i, 0, cost);
		}
		System.out.println(answer);
	}

	public static void backtracking(int begin, int start, int depth, int cost) {
		if (start == begin && depth == N) {
			answer = Math.min(answer, cost);
			return;
		}
		for (int i = 0; i < N; i++) {
			if (map[start][i] != 0 && !visited[i]) {
				visited[i] = true;
				if (cost < answer)
					backtracking(begin, i, depth + 1, cost + map[start][i]);
				visited[i] = false;
			}
		}
	}
}

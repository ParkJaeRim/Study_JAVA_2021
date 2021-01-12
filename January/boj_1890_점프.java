import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1890_점프 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		long[][] map = new long[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Long.parseLong(st.nextToken());
			}
		}

		long[][] cal = new long[N][N];

		cal[0][0] = 1;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (cal[i][j] == 0 || (i == N - 1 && j == N - 1)) {
					continue;
				}

				int jump = (int) map[i][j];

				if (j + jump < N)
					cal[i][j + jump] += cal[i][j];
				if (i + jump < N)
					cal[i + jump][j] += cal[i][j];
			}
		}
		System.out.println(cal[N-1][N-1]);
	}

}

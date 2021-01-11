import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_12865_평범한배낭 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int max = Integer.parseInt(st.nextToken());

		int[][] dp = new int[N][max + 1];
		int[] weight = new int[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int w = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			weight[i] = w;
			for (int j = w; j < max + 1; j++) {
				dp[i][j] = v;

			}
		}

		for (int i = 1; i < dp.length; i++) {
			for (int j = 0; j < dp[i].length; j++) {
				if (j - weight[i] >= 0) {
					dp[i][j] = Math.max(dp[i][j] + dp[i - 1][j - weight[i]], dp[i - 1][j]);
				} else {
					dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
				}
			}
		}
		int answer = Integer.MIN_VALUE;
		for (int i = 0; i < weight.length; i++) {
			answer = Math.max(dp[i][max], answer);
		}
		System.out.println(answer);
	}
}

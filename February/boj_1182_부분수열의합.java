import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1182_부분수열의합 {
	static int N, goal, sum, cnt;
	static int[] num;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		goal = Integer.parseInt(st.nextToken());

		num = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		sum = 0;
		cnt = 0;
		backtracking(0, 0);
		if (goal != 0)
			System.out.println(cnt);
		else
			System.out.println(cnt - 1);
	}

	public static void backtracking(int start, int depth) {
		if (sum == goal) {
			cnt++;
		}

		if (depth == N) {
			return;
		}
		for (int i = start; i < num.length; i++) {
			sum += num[i];
			backtracking(i + 1, depth + 1);
			sum -= num[i];
		}
	}
}

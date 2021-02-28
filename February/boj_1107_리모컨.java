import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1107_리모컨 {
	static boolean[] broken;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int goal = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());

		broken = new boolean[10];

		if (M != 0) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				int t = Integer.parseInt(st.nextToken());
				broken[t] = true;
			}
		}
		int INF = 1000000;
		int hard = Math.abs(goal - 100);

		int ans = Integer.MAX_VALUE;
		for (int i = 0; i <= INF; i++) {
			int len = check(i);
			if (len > 0) {
				int cnt = Math.abs(i - goal);
				ans = Math.min(cnt + len, ans);
			}
		}
		int answer = Math.min(hard, ans);
		System.out.println(answer);
	}

	public static int check(int channel) {
		if (channel == 0) {
			if (broken[0]) {
				return 0;
			} else {
				return 1;
			}
		}
		int len = 0;
		while (channel != 0) {
			int chck = channel % 10;
			if (broken[chck])
				return 0;
			else {
				len++;
				channel /= 10;
			}
		}
		return len;
	}

}
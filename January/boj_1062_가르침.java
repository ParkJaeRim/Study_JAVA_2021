import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1062_가르침 {
	static int limit, N, K, answer;
	static boolean[] using;
	static boolean[][] word_lst;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		String[] word = new String[N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			word[i] = st.nextToken();
		}
		word_lst = new boolean[N][26];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < word[i].length(); j++) {
				word_lst[i][word[i].charAt(j) - 'a'] = true;
			}
		}

		answer = 0;
		using = new boolean[26];
		using['a' - 'a'] = using['n' - 'a'] = using['t' - 'a'] = using['i' - 'a'] = using['c' - 'a'] = true;
		limit = K - 5;
		go(0, 0);
		System.out.println(answer);
	}

	public static void go(int start, int depth) {
		if (limit == depth) {
			int cnt = 0;
			for (int i = 0; i < N; i++) {
				boolean cant_read = false;
				for (int j = 0; j < 26; j++) {
					if (word_lst[i][j] && !using[j]) {
						cant_read = true;
					}
				}
				if (!cant_read) {
					cnt++;
				}
			}
			answer = Math.max(answer, cnt);
			return;
		}

		for (int i = start; i < using.length; i++) {
			if (!using[i]) {
				using[i] = true;
				go(i + 1, depth + 1);
				using[i] = false;
			}
		}
	}
}

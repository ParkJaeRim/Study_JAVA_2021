import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2529_부등호 {
	static int[] pick;
	static char[] bu;
	static boolean[] visited;
	static int N;
	static Long maxV, minV;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		bu = new char[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			bu[i] = st.nextToken().charAt(0);
		}

		pick = new int[N + 1];
		visited = new boolean[10];
		maxV = Long.MIN_VALUE;
		minV = Long.MAX_VALUE;
		combi(0);
		StringBuilder sb = new StringBuilder();
		if (maxV.toString().length() < pick.length) {
			sb.append(0).append(maxV);
		} else {
			sb.append(maxV);
		}
		sb.append("\n");
		if (minV.toString().length() < pick.length) {
			sb.append(0).append(minV);
		} else {
			sb.append(minV);
		}
		System.out.println(sb.toString());
	}

	public static void combi(int depth) {
		if (depth == pick.length) {
			if (compare(pick)) {
				long x = 0;
				String tmp = "";
				for (int p : pick) {
					tmp += Integer.toString(p);
				}
				x = Long.parseLong(tmp);

				maxV = Math.max(maxV, x);
				minV = Math.min(minV, x);
			}

			return;
		}
		for (int i = 0; i < 10; i++) {
			if (!visited[i]) {
				pick[depth] = i;
				visited[i] = true;
				combi(depth + 1);
				visited[i] = false;
			}
		}
	}

	public static boolean compare(int[] pick) {
		for (int i = 0; i < bu.length; i++) {
			if (bu[i] == '>' && pick[i] < pick[i + 1]) {
				return false;
			}
			if (bu[i] == '<' && pick[i] > pick[i + 1]) {
				return false;
			}
		}
		return true;
	}
}

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class boj_1620_나는야포켓몬마스터이다솜 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		HashMap<String, Integer> hm = new HashMap<>();
		String[] name = new String[N + 1];
		for (int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			hm.put(s, i);
			name[i] = s;
		}
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (isStringNumber(s)) {
				sb.append(name[Integer.parseInt(s)]).append("\n");
			} else {
				sb.append(hm.get(s)).append("\n");
			}

		}
		System.out.print(sb.toString());
		br.close();
	}

	public static boolean isStringNumber(String s) {
		try {
			Double.parseDouble(s);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}

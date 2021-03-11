import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class boj_14425_문자열집합 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		HashSet<String> hs = new HashSet<>();
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			hs.add(st.nextToken());
		}
		int cnt = 0;
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();
			if (hs.contains(s)) {
				cnt++;
			}
		}
		System.out.println(cnt);
	}

}

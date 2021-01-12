import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1759_암호만들기 {
//4 6
//a t c i s w
	static int R, C;
	static char[] moeum = { 'a', 'e', 'i', 'o', 'u' };
	static char[] pick, alpha;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());

		pick = new char[R];
		alpha = new char[C];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < C; i++) {
			alpha[i] = st.nextToken().charAt(0);
		}

		Arrays.sort(alpha);
		combi(0, 0);
	}

	public static void combi(int start, int depth) {
		if (depth == R) {
			int mcnt = 0;
			int jcnt = 0;

			for (char p : pick) {
				boolean flag = false;
				for (char m : moeum) {
					if (m == p) {
						flag = true;
						break;
					}

				}
				if (!flag) {
					jcnt++;
				} else {
					mcnt++;
				}
			}
			if (mcnt >= 1 && jcnt >= 2) {
				for (char p : pick) {
					System.out.print(p);
				}
				System.out.println();
			}
			return;
		}

		for (int i = start; i < alpha.length; i++) {
			pick[depth] = alpha[i];
			combi(i + 1, depth + 1);
		}
	}
}

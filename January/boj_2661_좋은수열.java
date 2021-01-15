import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2661_좋은수열 {
	static int N;
	static boolean flag;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		if (N == 1) {
			System.out.println("1");
		} else {
			String s = "12";
			go(s);
		}

	}

	public static void go(String mystr) {
		if (mystr.length() == N) {
			System.out.println(mystr);
			System.exit(0);
			return;
		}

		for (int i = 1; i <= 3; i++) {
			flag = false;
			dfs(mystr + i, 1);
			if (!flag) {
				go(mystr + i);
			} else {
				continue;
			}
		}

	}

	public static void dfs(String mystr, int until) {
		int limit = 0;
		if (N % 2 == 0) {
			limit = mystr.length() / 2 + 1;
		} else {
			limit = (int) Math.ceil((double) mystr.length() / 2);
		}
		if (until == limit) {
			return;
		}

		for (int i = 0; i < mystr.length() - until; i++) {

			if ((i + (until * 2)) == (mystr.length() + 1)) {
				if (mystr.substring(i, i + until).equals(mystr.substring(i + until, mystr.length() - 1))) {
					flag = true;
				}
			} else if ((i + (until * 2)) < (mystr.length() + 1)
					&& mystr.substring(i, i + until).equals(mystr.substring(i + until, i + (until * 2)))) {
				flag = true;
			} else if ((i + until * 2) > (mystr.length() + 1)) {
				break;
			}
		}

		dfs(mystr, until + 1);
	}

}
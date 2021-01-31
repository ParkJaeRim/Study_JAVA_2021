import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class swea_5658_보물상자비밀번호 {

	// 3시 10분 ~ 40분 / 45분 ~ 23분 // 약 1시간 소요

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int t = 1; t <= tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			String s = st.nextToken();

			int byeon = N / 4;
			int howmany = 0;

			TreeSet<Integer> hs = new TreeSet<>();
			char[] lst = s.toCharArray();

			while (howmany != byeon) {
				for (int i = 0; i < 4; i++) {
					String x = "";
					for (int j = 0; j < byeon; j++) {
						x += lst[(i * byeon) + j];
					}
					int change = changeDigit(x);

					hs.add(change);
				}
				rotate(lst);
				howmany++;
			}
			int limit = 0;
			while (limit != K - 1) {
				hs.pollLast();
				limit++;
			}

			System.out.println("#" + t + " " + hs.pollLast());

		}

	}

	public static void rotate(char[] arr) {
		char tmp = arr[arr.length - 1];

		for (int i = arr.length - 1; i > 0; i--) {
			arr[i] = arr[i - 1];
		}
		arr[0] = tmp;
	}

	public static int changeDigit(String s) {
		int digit = 0;
		for (int i = 0; i < s.length(); i++) {
			if ('A' <= s.charAt(i) && s.charAt(i) <= 'F') {
				digit += ((s.charAt(i) - 'A') + 10) * Math.pow(16, s.length() - i - 1);
			} else if ('0' <= s.charAt(i) && s.charAt(i) <= '9') {
				digit += (s.charAt(i) - '0') * Math.pow(16, s.length() - i - 1);
			}
		}

		return digit;
	}

	static class Point implements Comparable<Point> {
		int x;

		public Point(int x) {
			super();
			this.x = x;
		}

		@Override
		public int compareTo(Point o) {
			return o.x - this.x;
		}
	}
}

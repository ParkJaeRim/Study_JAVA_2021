import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_7795_먹을것인가먹힐것인가 {
	static int[] a, b;
	static int cnt;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		for (int i = 1; i <= tc; i++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int M = Integer.parseInt(st.nextToken());

			a = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < N; y++) {
				a[y] = Integer.parseInt(st.nextToken());
			}
			b = new int[M];
			st = new StringTokenizer(br.readLine());
			for (int y = 0; y < M; y++) {
				b[y] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(a);
			Arrays.sort(b);
			cnt = 0;
			find(0);
			System.out.println(cnt);
		}

	}

	public static void find(int a_idx) {
		if (a_idx == a.length) {
			return;
		}
		int start = 0;
		int end = b.length - 1;
		boolean flag = false;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (b[mid] >= a[a_idx]) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		int mid = (start + end) / 2;
		if (b[mid] < a[a_idx]) {
			cnt += mid + 1;
		} else {
			cnt += mid;
		}
		find(a_idx + 1);
	}

}

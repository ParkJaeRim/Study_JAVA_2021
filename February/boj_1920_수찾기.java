import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_1920_수찾기 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		st = new StringTokenizer(br.readLine());
		int M = Integer.parseInt(st.nextToken());
		int[] find = new int[M];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < M; i++) {
			find[i] = Integer.parseInt(st.nextToken());
		}

		Arrays.sort(lst);
		for (int tc = 0; tc < M; tc++) {
			int num = find[tc];

			int start = 0;
			int end = N - 1;
			boolean flag = false;	
			while (start <= end) {
				int mid = (start + end) / 2;
				if (lst[mid] == num) {
					flag = true;
					break;
				}

				if (num < lst[mid]) {
					end = mid - 1;
				} else if (num > lst[mid]) {
					start = mid + 1;
				}
			}
			if (flag) {
				System.out.println(1);
			} else {
				System.out.println(0);
			}
		}
	}

}

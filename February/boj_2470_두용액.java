import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj_2470_두용액 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] lst = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < lst.length; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
		}
		Arrays.sort(lst);
		for (int i = 0; i < lst.length; i++) {
			System.out.print(lst[i] + " ");
		}

		int start = 0;
		int end = N - 1;
		int storeStart = -1;
		int storeEnd = -1;

		int sum = Integer.MAX_VALUE;
		while (start < end) {
			int tot = lst[start] + lst[end];

			if (tot == 0) {
				storeStart = start;
				storeEnd = end;
				break;
			}

			if (Math.abs(tot) < sum) {
				sum = Math.abs(tot);
				storeStart = start;
				storeEnd = end;
			}

			if (tot > 0) {
				end--;
			} else {
				start++;
			}
		}
		System.out.println(lst[storeStart] + " " + lst[storeEnd]);
	}
}

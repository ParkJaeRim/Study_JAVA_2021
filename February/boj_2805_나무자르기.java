import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2805_나무자르기 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int[] lst = new int[N];
		int start = Integer.MAX_VALUE;
		int end = Integer.MIN_VALUE;
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			lst[i] = Integer.parseInt(st.nextToken());
			end = Math.max(end, lst[i]);
		}
		start = 0;
		int ans = Integer.MAX_VALUE;
		long treeMax = Integer.MIN_VALUE;
		while (start <= end) {
			long tot = 0;
			int mid = (start + end) / 2;
			for (int i = 0; i < lst.length; i++) {
				if (lst[i] - mid > 0) {
					tot += lst[i] - mid;
				}
			}
			if (tot < M) {
				end = mid - 1;
			} else if (tot >= M) {
				start = mid + 1;
				treeMax = Math.max(treeMax, mid);
			}
		}
		System.out.println(treeMax);
	}
}

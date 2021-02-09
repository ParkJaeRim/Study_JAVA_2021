import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class boj_2776_암기왕 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int tc = Integer.parseInt(st.nextToken());
		StringBuffer sb = new StringBuffer();

		for (int t = 0; t < tc; t++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			long[] first = new long[N];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < first.length; i++) {
				first[i] = Integer.parseInt(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			PriorityQueue<Point> pq = new PriorityQueue<>();
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < M; i++) {
				long x = Integer.parseInt(st.nextToken());
				pq.offer(new Point(x, i));
			}
			long[] ans = new long[M];

			Arrays.sort(first);

			int start = 0;
			while (!pq.isEmpty()) {
				Point p = pq.poll();
				long find = p.num;
				int end = first.length - 1;
				boolean flag = false;
				while (start <= end) {
					int mid = (start + end) / 2;
					if (first[mid] == find) {
						flag = true;
						break;
					} else if (first[mid] < find) {
						start = mid + 1;
					} else if (first[mid] > find) {
						end = mid - 1;
					}
				}
				if (flag) {
					ans[p.idx] = 1;

				} else {
					ans[p.idx] = 0;
				}
				end = first.length - 1;
			}
			for (int i = 0; i < ans.length; i++) {
				sb.append(ans[i]).append("\n");
			}
		}
		System.out.println(sb.toString().substring(0, sb.length() - 1));
	}

	static class Point implements Comparable<Point> {

		Long num;
		int idx;

		public Point(Long num, int idx) {
			super();
			this.num = num;
			this.idx = idx;
		}

		@Override
		public int compareTo(Point o) {
			if (this.num > o.num) {
				return 1;

			} else if (this.num == o.num) {
				return 0;
			}
			return -1;
		}

	}
}

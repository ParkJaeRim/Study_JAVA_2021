import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class boj_5577_1학년 {
	static int[] num;
	static long[] cnt;
	static int N;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] num = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}
		long[][] cnt = new long[21][N - 1];	

		cnt[num[0]][0] = 1;

		for (int x = 1; x < N - 1; x++) {
			for (int y = 0; y < 21; y++) {
				if (cnt[y][x - 1] != 0) {
					if (y + num[x] <= 20) {
						cnt[y + num[x]][x] += cnt[y][x - 1];
					}
					if (y - num[x] >= 0) {
						cnt[y - num[x]][x] += cnt[y][x - 1];
					}
				}

			}

		}

		for (int i = 0; i < cnt.length; i++) {
			for (int j = 0; j < cnt[i].length; j++) {
				System.out.print(cnt[i][j] + " ");
			}
			System.out.println();
		}

		System.out.println(cnt[num[N - 1]][N - 2]);

	}
}
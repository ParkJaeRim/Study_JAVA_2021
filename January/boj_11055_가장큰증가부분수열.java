import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11055_가장큰증가부분수열 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());

		int[] number = new int[N];
		int[] increase = new int[N];
		int[] sum = new int[N];

		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
			sum[i] = number[i];

		}

		for (int i = 1; i < N; i++) {
			int tmp = Integer.MIN_VALUE;
			int tmpidx = -1;
			for (int j = 0; j < i; j++) {
				if (number[j] < number[i]) {
					tmp = Math.max(sum[j], tmp);
				}
			}
			if (tmp != Integer.MIN_VALUE)
				sum[i] += tmp;
		}

		int answer = -1;

		for (int i = 0; i < sum.length; i++) {
			answer = Math.max(answer, sum[i]);
		}
		System.out.println(answer);
	}
}

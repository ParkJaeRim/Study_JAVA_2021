import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_11053_가장긴증가하는부분수열 {
	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int[] number = new int[N];
		int[] answer = new int[N];
		st = new StringTokenizer(br.readLine());

		for (int i = 0; i < N; i++) {
			number[i] = Integer.parseInt(st.nextToken());
			answer[i] = 1;
		}

		for (int i = 1; i < N; i++) {
			int tmp = Integer.MIN_VALUE;
			for (int j = 0; j < i; j++) {
				if (number[i] > number[j]) {
					tmp = Math.max(tmp, answer[j]);
				}
			}
			if (tmp != Integer.MIN_VALUE)
				answer[i] = tmp + 1;
		}
		int result = 0;
		for (int i = 0; i < answer.length; i++) {
			result = Math.max(result, answer[i]);
		}
		System.out.println(result);
	}

}

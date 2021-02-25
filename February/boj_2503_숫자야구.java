import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_2503_숫자야구 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		Ball[] guess = new Ball[N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int strike = Integer.parseInt(st.nextToken());
			int ball = Integer.parseInt(st.nextToken());

			guess[i] = new Ball(num, strike, ball);
		}
		int cnt = 0;

		for (int realnum = 123; realnum <= 987; realnum++) {
			char[] real = Integer.toString(realnum).toCharArray();
			int tot = 0;
			if (real[0] != real[1] && real[1] != real[2] && real[2] != real[0] && real[0] != '0' && real[1] != '0'
					&& real[2] != '0') {

				for (int i = 0; i < guess.length; i++) {
					Ball tmpGuess = guess[i];
					int tmpNum = tmpGuess.num;
					char[] find = Integer.toString(tmpNum).toCharArray();

					int s = 0;
					int b = 0;

					for (int r = 0; r < 3; r++) {
						for (int f = 0; f < 3; f++) {
							if (real[r] == find[f]) {
								if (r == f) {
									s++;
								} else {
									b++;
								}
							}
						}
					}
					if (s == tmpGuess.strike && b == tmpGuess.ball) {
						tot++;
					} else {
						break;
					}
				}

				if (tot == N) {
					cnt++;
				}
			}
		}
		System.out.println(cnt);
	}

	static class Ball {
		int num;
		int strike;
		int ball;

		public Ball(int num, int strike, int ball) {
			this.num = num;
			this.strike = strike;
			this.ball = ball;
		}

	}
}

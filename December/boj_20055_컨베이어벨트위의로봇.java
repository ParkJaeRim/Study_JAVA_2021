import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_20055_컨베이어벨트위의로봇 {
	static int[] belt, robot;
	static int N, K;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		belt = new int[2 * N];
		robot = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			belt[i] = Integer.parseInt(st.nextToken());
		}

		int process = 0;

		while (true) {
			int t = belt[2 * N - 1];
			for (int i = (2 * N) - 1; i > 0; i--) {
				belt[i] = belt[i - 1];
			}
			belt[0] = t;

			for (int i = N - 1; i > 0; i--) {
				robot[i] = robot[i - 1];
				if (i - 1 == 0 && robot[i - 1] == 1) {
					robot[i - 1] = 0;
				}
			}

			if (robot[N - 1] == 1) {
				robot[N - 1] = 0;
			}
			for (int i = N - 1; i > 0; i--) {

				if (belt[i] > 0 && robot[i] == 0 && robot[i - 1] == 1) {
					robot[i] = 1;
					robot[i - 1] = 0;
					belt[i]--;
				}
			}

			if (robot[N - 1] == 1) {
				robot[N - 1] = 0;
			}
			if (belt[0] > 0 && robot[0] != 1) {
				robot[0] = 1;
				belt[0]--;
			}

			process++;
			if (!howmanyK()) {
				break;
			}
		}
		System.out.println(process);

	}

	public static boolean howmanyK() {
		int cnt = 0;
		for (int i = 0; i < belt.length; i++) {

			if (belt[i] == 0) {
				cnt++;
			}

			if (cnt >= K) {
				return false;
			}

		}
		return true;
	}
}

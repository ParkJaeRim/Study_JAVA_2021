import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14888_연산자끼워넣기 {
	static char[] cal = { '+', '-', '*', '/' };
	static char[] pickList;
	static int[] howmany, num;
	static int maxV, minV;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		num = new int[N];

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			num[i] = Integer.parseInt(st.nextToken());
		}

		howmany = new int[4];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 4; i++) {
			howmany[i] = Integer.parseInt(st.nextToken());
		}

		pickList = new char[N - 1];
		maxV = Integer.MIN_VALUE;
		minV = Integer.MAX_VALUE;

		pick(0);
		StringBuilder sb = new StringBuilder();
		sb.append(maxV).append("\n").append(minV);
		System.out.println(sb.toString());
	}

	public static void pick(int idx) {
		if (idx == pickList.length) {
			int result = num[0];
			for (int i = 0; i < pickList.length; i++) {
				if (pickList[i] == '+') {
					result += num[i + 1];
				} else if (pickList[i] == '-') {
					result -= num[i + 1];
				} else if (pickList[i] == '*') {
					result *= num[i + 1];
				} else if (pickList[i] == '/') {
					result /= num[i + 1];
				}
			}
			if (result > maxV) {
				maxV = result;
			}
			if (result < minV) {
				minV = result;
			}
		}

		for (int i = 0; i < 4; i++) {
			if (howmany[i] > 0) {
				howmany[i]--;
				pickList[idx] = cal[i];
				pick(idx + 1);
				howmany[i]++;
			}

		}
	}
}

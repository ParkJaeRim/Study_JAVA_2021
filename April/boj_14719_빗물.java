import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_14719_빗물 {
	static int[] wall;
	static int water;

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		wall = new int[W];
		st = new StringTokenizer(br.readLine());
		water = 0;
		int i = 0;
		while (st.hasMoreTokens()) {
			wall[i++] = Integer.parseInt(st.nextToken());
		}

		for (int idx = 0; idx < wall.length; idx++) {
			int leftMax = wall[idx];
			int rightMax = wall[idx];
			for (int left = 0; left < idx; left++) {
				leftMax = Math.max(leftMax, wall[left]);
			}

			for (int right = idx + 1; right < wall.length; right++) {
				rightMax = Math.max(rightMax, wall[right]);
			}

			if (leftMax <= wall[idx] || rightMax <= wall[idx]) {
				continue;
			} else {
				if (leftMax > rightMax) {
					water += rightMax - wall[idx];
				} else {
					water += leftMax - wall[idx];
				}
			}
		}
		System.out.println(water);
	}
}

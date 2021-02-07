import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj_1072_게임 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		long game = Integer.parseInt(st.nextToken());
		long win = Integer.parseInt(st.nextToken());
		long prob = (long) Math.floor(100 * win / game);

		long start = 1;
		long end = 1000000000;
		long todo = Long.MAX_VALUE;
		while (start <= end) {
			long mid = (start + end) / 2;
			System.out.println(start + "  " + mid + " " + end);
			long newgame = game + mid;
			long newwin = win + mid;
			long newprob = (long) Math.floor(100 * newwin / newgame);
			if (prob == newprob) {
				start = mid + 1;
			} else {
				end = mid - 1;
				todo = Math.min(todo, mid);
			}
		}
		if (todo != Long.MAX_VALUE) {
			System.out.println(todo);
		} else {
			System.out.println(-1);
		}
	}
}

import java.util.ArrayList;
import java.util.HashSet;

public class kakao_불량사용자 {
	static int answer;
	static int idx;
	static boolean flag;
	static ArrayList<Integer> al;
	static HashSet<String> hs;

	public static void main(String[] args) {

		String[] user = { "frodo", "fradi", "crodo", "abc123", "frodoc" };
//		String[] banned = { "fr*d*", "abc1**" };
//		String[] banned = { "*rodo", "*rodo", "******"};
		String[] banned = { "fr*d*", "*rodo", "******", "******" };
		solution(user, banned);
	}

	public static int solution(String[] user_id, String[] banned_id) {
		int[][] result = new int[banned_id.length][user_id.length];
		for (int i = 0; i < banned_id.length; i++) {
			for (int j = 0; j < user_id.length; j++) {
				if (banned_id[i].length() != user_id[j].length()) {
					continue;
				} else {
					idx = 0;
					flag = true;
					go(banned_id[i], user_id[j]);
					if (!flag) {
						result[i][j]++;
					}
				}
			}
		}

		boolean[] real = new boolean[100001];
		int depth = 0;
		al = new ArrayList<>();
		hs = new HashSet<>();
		dfs(result, real, depth);
		return answer;
	}

	public static void dfs(int[][] result, boolean[] real, int depth) {
		if (depth == result.length) {
			String t = "";
			for (int i = 0; i < real.length; i++) {
				if (real[i]) {
					t += i;
				}
			}
			hs.add(t);
			return;
		}
		for (int j = 0; j < result[depth].length; j++) {
			if (result[depth][j] == 1 && !real[j]) {
				real[j] = true;
				dfs(result, real, depth + 1);
				real[j] = false;
			}
		}
	}

	public static void go(String ban, String user) {
		if (idx == ban.length()) {
			flag = false;
			return;
		} else if (ban.charAt(idx) != user.charAt(idx) && ban.charAt(idx) != '*') {
			return;
		} else if (ban.charAt(idx) == '*') {
			idx++;
			go(ban, user);
		} else {
			idx++;
			go(ban, user);
		}
		return;
	}
}

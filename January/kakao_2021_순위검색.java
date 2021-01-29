import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class kakao_2021_순위검색 {

	// 12시 시작 ~1시
	static HashSet<String> hs;
	static String[] lst;
	static int cnt;

	public static void main(String[] args) {
		String[] info = { "java backend junior pizza 150", "python frontend senior chicken 210",
				"python frontend senior chicken 150", "cpp backend senior pizza 260", "java backend junior chicken 80",
				"python backend senior chicken 50" };

		String[] query = { "java and backend and junior and pizza 100",
				"python and frontend and senior and chicken 200", "cpp and - and senior and pizza 250",
				"- and backend and senior and - 150", "- and - and - and chicken 100", "- and - and - and - 150" };
		solution(info, query);
	}

	public static int[] solution(String[] info, String[] query) {
		int[] answer = new int[query.length];

		for (int i = 0; i < info.length; i++) {
			cnt = 0;
			lst = info[i].split(" ");
			String[] newStr = new String[info.length];
			hs = new HashSet<>();
			powerset(0, 0, info.length, lst, newStr);

			for (int q = 0; q < query.length; q++) {
				String[] origin = query[q].split(" ");
				ArrayList<String> maked = new ArrayList<>();

				for (int t = 0; t < origin.length - 1; t++) {
					if (!origin[t].equals("-") && !origin[t].equals("and")) {
						maked.add(origin[t]);
					}
				}

				aa: for (int a = 0; a < hs.size(); a++) {
					boolean[] visited = new boolean[maked.size()];
					boolean flag = false;
					for (int b = 0; b < maked.size(); b++) {
						if (hs.contains(maked.get(b))) {
							visited[b] = true;
						}
					}

					for (int c = 0; c < visited.length; c++) {
						if (!visited[c]) {
							flag = true;
						}
					}
					if (!flag) {
						if (Integer.parseInt(lst[lst.length - 1]) >= Integer.parseInt(origin[origin.length - 1])) {
							answer[q]++;
							break aa;
						}
					}

				}
			}
		}

		return answer;
	}

	public static void powerset(int start, int depth, int len, String[] lst, String[] newStr) {
		if (depth == len + 1) {
			return;
		}
		String tmp = "";
		String[] wow = new String[depth];
		for (int i = 0; i < depth; i++) {
			wow[i] = newStr[i];
		}
		Arrays.sort(wow);
		for (int i = 0; i < wow.length; i++) {
			tmp += wow[i];
		}

		cnt++;
		hs.add(tmp);

		for (int i = start; i < lst.length - 1; i++) {
			newStr[depth] = lst[i];
			powerset(i + 1, depth + 1, len, lst, newStr);

		}
	}
}

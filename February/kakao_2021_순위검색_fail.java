import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class kakao_2021_순위검색_fail {

	static HashMap<String, ArrayList<Integer>> hm;
	static String[] lst;
	static int cnt;
	static int[] answer;

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
		answer = new int[query.length];
		hm = new HashMap<>();

		for (int i = 0; i < info.length; i++) {
			String[] information = info[i].split(" ");
			String[] newStr = new String[info.length];
			powerset(0, 0, information, newStr);
		}

		Iterator<String> it = hm.keySet().iterator();
		while (it.hasNext()) {
			String key = it.next();
			ArrayList<Integer> li = hm.get(key);
			Collections.sort(li);
		}

		cleanQuery(query);
		return answer;
	}

	public static void cleanQuery(String[] query) {
		for (int i = 0; i < query.length; i++) {
			String[] eachQuery = query[i].split(" ");
			String tmp = "";

			for (int j = 0; j < eachQuery.length - 1; j++) {
				if (eachQuery[j].equals("-") || eachQuery[j].equals("and")) {
					continue;
				} else {
					tmp += eachQuery[j];
				}
			}

			if (hm.containsKey(tmp)) {
				ArrayList<Integer> al = hm.get(tmp);
				int findnum = Integer.parseInt(eachQuery[eachQuery.length - 1]);
				answer[i] = binarySearch(al, findnum);
			} else {
				answer[i] = 0;
			}
		}

	}

	public static int binarySearch(ArrayList<Integer> findlst, int findnum) {
		int start = 0;
		int end = findlst.size() - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			if (findlst.get(mid) < findnum) {
				start = mid + 1;
			} else {
				end = mid - 1;
			}
		}
		return findlst.size() - start;

	}

	public static void powerset(int start, int depth, String[] information, String[] newStr) {
		if (depth == 5) {
			return;
		}

		String tmp = "";
		for (int i = 0; i < depth; i++) {
			tmp += newStr[i];
		}

		if (!hm.containsKey(tmp)) {
			ArrayList<Integer> lst = new ArrayList<>();
			lst.add(Integer.parseInt(information[information.length - 1]));
			hm.put(tmp, lst);
		} else {
			hm.get(tmp).add(Integer.parseInt(information[information.length - 1]));
		}

		for (int i = start; i < information.length - 1; i++) {
			newStr[depth] = information[i];
			powerset(i + 1, depth + 1, information, newStr);
		}
	}
}

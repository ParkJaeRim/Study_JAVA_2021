import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;

public class kakao_2021_메뉴리뉴얼 {
	static HashMap<String, Integer> hm;

	public static void main(String[] args) {
		String[] orders = { "XYZ", "XWY", "WXA" };
		int[] course = { 2, 3, 4 };

		solution(orders, course);

	}

	public static String[] solution(String[] orders, int[] course) {
		int[] counting = new int[26];
		hm = new HashMap<>();
		for (int i = 0; i < orders.length; i++) {
			char[] x = new char[orders[i].length()];
			char[] newStr = new char[orders[i].length()];
			x = orders[i].toCharArray();
			powerset(0, 0, x.length, x, newStr);
		}
		ArrayList<String>[] al = new ArrayList[course.length];
		for (int i = 0; i < al.length; i++) {
			al[i] = new ArrayList<>();
		}
		for (int i = 0; i < course.length; i++) {
			String ans = "";
			int maxV = Integer.MIN_VALUE;
			Iterator<String> keys = hm.keySet().iterator();
			while (keys.hasNext()) {
				String key = keys.next();
				if (hm.get(key) > 1 && course[i] == key.length()) {
					if (maxV < hm.get(key)) {
						al[i].clear();
						ans = key;
						al[i].add(ans);
						maxV = hm.get(key);
					} else if (maxV == hm.get(key)) {
						al[i].add(key);
					}
				}
			}
		}
		ArrayList<String> tmp = new ArrayList<>();

		for (int i = 0; i < al.length; i++) {
			for (int j = 0; j < al[i].size(); j++) {
				tmp.add(al[i].get(j));
			}
		}
		String[] answer = new String[tmp.size()];
		for (int i = 0; i < tmp.size(); i++) {
			answer[i] = tmp.get(i);
		}
		Arrays.sort(answer);
		return answer;
	}

	public static void powerset(int start, int depth, int len, char[] x, char[] newStr) {
		if (depth == len + 1) {
			return;
		}
		String tmp = "";
		char[] wow = new char[depth];

		for (int i = 0; i < depth; i++) {
			wow[i] = newStr[i];
		}
		Arrays.sort(wow);
		for (int i = 0; i < wow.length; i++) {
			tmp += wow[i];
		}

		if (tmp.length() > 1) {
			if (hm.containsKey(tmp)) {
				hm.put(tmp, hm.get(tmp) + 1);
			} else {
				hm.put(tmp, 1);
			}
		}

		for (int i = start; i < x.length; i++) {
			newStr[depth] = x[i];
			powerset(i + 1, depth + 1, len, x, newStr);

		}
	}
}

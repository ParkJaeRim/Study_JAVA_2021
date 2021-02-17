import java.util.HashMap;
import java.util.HashSet;

public class kakao_2020_보석쇼핑 {

	public static void main(String[] args) {

//		String[] gems = { "DIA", "RUBY", "RUBY", "DIA", "DIA", "EMERALD", "SAPPHIRE", "DIA" };
		String[] gems = { "ZZZ", "YYY", "NNNN", "YYY", "BBB" };
//		String[] gems = { "AA", "AB", "AC", "AA", "AC" };
		solution(gems);
	}

	public static int[] solution(String[] gems) {
		int[] answer = new int[2];
		HashMap<String, Integer> hm = new HashMap<>();
		int idx = 0;
		for (int i = 0; i < gems.length; i++) {
			if (!hm.containsKey(gems[i])) {
				hm.put(gems[i], idx);
				idx++;
			}
		}

		int minV = gems.length;
		int memory = -1;

		for (int i = 0; i <= gems.length - hm.size(); i++) {
			HashSet<String> hs = new HashSet<>();
			if (i + minV <= gems.length) {
				for (int j = i; j < i + minV; j++) {
					hs.add(gems[j]);
					if (hs.size() == hm.size()) {
						if (minV > (j - i)) {
							minV = j - i;
							memory = i;
						}
						break;
					}
				}
			}
		}
		System.out.println(memory + " " + minV);
		answer[0] = memory + 1;
		answer[1] = memory + minV + 1;
		System.out.println(answer[0] + " " + answer[1]);
		return answer;
	}
}

import java.util.LinkedList;

public class kakao_2021_신규아이디추천 {

	public static void main(String[] args) {
//		String new_id = "...!@BaT#*..y.abcdefghijklm";
//		String new_id = "z-+.^.";
		String new_id = "123_.def";
		solution(new_id);
	}

	public static String solution(String new_id) {
		String answer = "";
//		1단계 new_id의 모든 대문자를 대응되는 소문자로 치환합니다.
		new_id = new_id.toLowerCase();

//		2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
		String tmp_id = "";
		for (int i = 0; i < new_id.length(); i++) {
			if ((('a' <= new_id.charAt(i) && new_id.charAt(i) <= 'z')
					|| ('0' <= new_id.charAt(i) && new_id.charAt(i) <= '9') || new_id.charAt(i) == '-'
					|| new_id.charAt(i) == '_' || new_id.charAt(i) == '.')) {
				tmp_id += new_id.charAt(i);
			}
		}
		
//		3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
		LinkedList<String> lst = new LinkedList<>();
		for (int i = 0; i < tmp_id.length(); i++) {
			lst.add(tmp_id.substring(i, i + 1));
		}

		int idx = 0;
		while (true) {
			int limit = lst.size();
			if (idx == limit) {
				break;
			}
			if (idx + 1 < lst.size() && lst.get(idx).equals(".") && lst.get(idx + 1).equals(".")) {
				lst.remove(idx);
				idx = 0;
			} else {
				idx++;
			}
		}

//		4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
		while (true) {
			if (lst.size() == 0) {
				break;
			}
			if (lst.get(0).equals(".")) {
				lst.remove(0);
			} else {
				break;
			}
		}
		while (true) {
			if (lst.size() == 0) {
				break;
			}
			if (lst.get(lst.size() - 1).equals(".")) {
				lst.remove(lst.size() - 1);
			} else {
				break;
			}
		}

//		5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
		if (lst.size() == 0) {
			lst.add("a");
		}

//		6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
		if (lst.size() >= 16) {
			int lstidx = 15;
			while (lst.size() >= 16) {
				lst.remove(15);
			}
		}
//	     만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
		while (true) {
			if (lst.get(lst.size() - 1).equals(".")) {
				lst.remove(lst.size() - 1);
			} else {
				break;
			}
		}

//		7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
		if (lst.size() <= 2) {
			while (lst.size() < 3) {
				lst.add(lst.get(lst.size() - 1));
			}
		}

		for (int i = 0; i < lst.size(); i++) {
			answer += lst.get(i);
		}
		System.out.println(answer);
		return answer;
	}

}

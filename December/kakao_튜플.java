import java.util.PriorityQueue;

class kakao_튜플 {
	public static void main(String[] args) {
		String s = "{{123}}";
		solution(s);
	}

	public static int[] solution(String s) {

		s = s.replace("{{", "");
		s = s.replace("}}", "");
		s = s.replace("},{", "/");
		String[] a = s.split("/");

		int[] count = new int[100001];
		for (int i = 0; i < a.length; i++) {
			String[] tmp = a[i].split(",");
			int[] t = new int[tmp.length];
			for (int j = 0; j < tmp.length; j++) {
				count[Integer.parseInt(tmp[j])]++;
			}
		}

		PriorityQueue<Edge> pq = new PriorityQueue<>();
		for (int i = 0; i < count.length; i++) {
			if (count[i] != 0)
				pq.add(new Edge(count[i], i));
		}

		int[] answer = new int[pq.size()];
		int i = 0;
		while (!pq.isEmpty()) {
			answer[i] = pq.poll().idx;
			i++;
		}
		return answer;
	}

	static class Edge implements Comparable<Edge> {
		int cnt;
		int idx;

		public Edge(int cnt, int idx) {
			this.cnt = cnt;
			this.idx = idx;
		}

		@Override
		public int compareTo(Edge o) {
			return o.cnt - this.cnt;
		}
	}
}
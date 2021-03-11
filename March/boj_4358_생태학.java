import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;

public class boj_4358_생태학 {

	public static void main(String[] args) throws IOException {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		HashMap<String, Integer> hm = new HashMap<>();
		int totCnt = hm.size();
		String tree = "";
		while ((tree = br.readLine()) != null) {
			if (hm.containsKey(tree)) {
				hm.replace(tree, hm.get(tree), hm.get(tree) + 1);
			} else {
				hm.put(tree, 1);
			}
			totCnt++;
		}
		Iterator<String> keys = hm.keySet().iterator();
		ArrayList<dict> al = new ArrayList<>();
		while (keys.hasNext()) {
			String key = keys.next();
			al.add(new dict(key, String.format("%.4f", ((double) hm.get(key) / totCnt) * 100)));
		}
		Collections.sort(al);
		for (int i = 0; i < al.size(); i++) {
			System.out.println(al.get(i).string + " " + al.get(i).prob);
		}
		br.close();
	}

	static class dict implements Comparable<dict> {
		String string;
		String prob;

		public dict(String string, String prob) {
			super();
			this.string = string;
			this.prob = prob;
		}

		@Override
		public int compareTo(dict d) {
			return this.string.compareTo(d.string);
		}

	}
}

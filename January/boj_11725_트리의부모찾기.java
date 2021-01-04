import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

public class boj_11725_트리의부모찾기 {
	static ArrayList<Integer>[] al;
	static int[] parent;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int n = Integer.parseInt(st.nextToken());
		al = new ArrayList[n + 1];

		for (int i = 0; i < al.length; i++) {
			al[i] = new ArrayList<Integer>();
		}

		for (int i = 0; i < n - 1; i++) {
			st = new StringTokenizer(br.readLine());
			if (st.hasMoreTokens()) {
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				al[u].add(v);
				al[v].add(u);
			}
		}

		parent = new int[n + 1];

		find(1);
		StringBuilder sb= new StringBuilder();
		for (int i = 2; i < parent.length; i++) {
			sb.append(parent[i]+"\n");
		}
		System.out.println(sb.toString().substring(0,sb.length()-1));
	}

	public static void find(int current) {
		for (int idx = 0; idx < al[current].size(); idx++) {
			if (parent[al[current].get(idx)] == 0) {
				parent[al[current].get(idx)] = current;
				find(al[current].get(idx));
			}
		}
	}

}

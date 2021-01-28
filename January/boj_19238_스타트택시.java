import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

public class boj_19238_스타트택시 {
	static int N, M, fuel;
	static ArrayList<Integer>[][] map;
	static int ans;
	static int[] dy = { -1, 1, 0, 0 };
	static int[] dx = { 0, 0, 1, -1 };
	static Queue<Point> que;
	static ArrayList<Point> al;
	static PriorityQueue<Point> pq;
	static int ty, tx, tot;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		pq = new PriorityQueue<>();
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		fuel = Integer.parseInt(st.nextToken());
		map = new ArrayList[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = new ArrayList<>();

			}
		}
		tot = M;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				int x = Integer.parseInt(st.nextToken());
				if (x == 1) {
					map[i][j].add(-1);
				} else {
					map[i][j].add(0);
				}
			}
		}

		st = new StringTokenizer(br.readLine());

		int startY = Integer.parseInt(st.nextToken()) - 1;
		int startX = Integer.parseInt(st.nextToken()) - 1;
		al = new ArrayList<>();
		al.add(new Point(-1, -1));

		for (int i = 1; i <= M; i++) {
			st = new StringTokenizer(br.readLine());
			int pply = Integer.parseInt(st.nextToken()) - 1;
			int pplx = Integer.parseInt(st.nextToken()) - 1;
			if (map[pply][pplx].get(0) == 0) {
				map[pply][pplx].clear();
			}
			map[pply][pplx].add(i);

			int taxiy = Integer.parseInt(st.nextToken()) - 1;
			int taxix = Integer.parseInt(st.nextToken()) - 1;
			al.add(new Point(taxiy, taxix));
		}

		ty = startY;
		tx = startX;

		int x = 0;
		while (true) {
//			System.out.println("MAP");
//			System.out.println("========================");
//			print(map);
//			pq.clear();
			pplBFS(ty, tx);
			if (x != 0 && pq.size() == 0) {
				System.out.println(fuel);
				return;
			} else if (x == 0 && pq.size() == 0) {
				System.out.println(-1);
				return;
			}
			// 제일 위에 있는 ArrayList를 빼서 거기로 간다.
			// 만약 al의 크기가 2 이상이면 모든 경우의 수에 대해 체크
			Point people = pq.poll();
			int goal = -1;

			if (people.lst.size() >= 2) {
//				System.out.println("gg");
				goal = bfs(people);
			}

			else {
				goal = people.lst.get(0);

			}
			System.out.println("가야하는 정류장 번호 : " + goal);
			System.out.println("시작하는 곳 : " + people.y + " " + people.x);
//			System.out.println("연료량 :  " + fuel);

			if (fuel >= 0) {
				boolean flag = placeBFS(people.y, people.x, goal);
				if (!flag) {
					System.out.println(-1);
					return;
				}
				System.out.println(flag);
				for (int i = 0; i < map[people.y][people.x].size(); i++) {
					if (map[people.y][people.x].get(i) == goal) {
						map[people.y][people.x].remove(i);
						tot--;
					}
				}
			}
			if (tot == 0) {
				System.out.println(fuel);
				return;
			}
			x++;
//			System.out.println();
		}
	}

	public static int bfs(Point people) {
		int[][] tmp = new int[N][N];
		int[][] cnt = new int[N][N];
		for (int i = 0; i < people.lst.size(); i++) {
			tmp[al.get(people.lst.get(i)).y][al.get(people.lst.get(i)).x] = people.lst.get(i);
		}
//		System.out.println("###### taxi 위치 ######");
//		print(tmp);

		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(people.y, people.x));
		while (!que.isEmpty()) {
			Point p = que.poll();
//			System.out.println();
//			print(cnt);
//			System.out.println(p.y + " " + p.x);
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				if (!isSafe(ny, nx)) {
					continue;
				}
				if (tmp[ny][nx] != 0) {
					cnt[ny][nx] = cnt[p.y][p.x] + 1;
//					print(cnt);
//					System.out.println("=====================");
//					System.out.println(cnt[ny][nx]);
//					System.out.println(tmp[ny][nx]);
//
//					print(tmp);
					return tmp[ny][nx];
				}
				if (map[ny][nx].get(0) != -1 && tmp[ny][nx] == 0 && cnt[ny][nx] == 0) {
//					System.out.println("?");
					cnt[ny][nx] = cnt[p.y][p.x] + 1;
					que.offer(new Point(ny, nx));

				}
			}
		}
		return -1;
	}

	public static boolean placeBFS(int startY, int startX, int goal) {
		int[][] cnt = new int[N][N];
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(startY, startX));
		Point g = al.get(goal);
//		System.out.println(g.y + " " + g.x);

		while (!que.isEmpty()) {
			Point p = que.poll();
//			System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%");
//			print(cnt);
//			System.out.println();
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];
				if (!isSafe(ny, nx)) {
					continue;
				}
				if (ny == g.y && nx == g.x) {
					cnt[ny][nx] = cnt[p.y][p.x] + 1;
//					System.out.println("남은 연료 : " + fuel + "  " + cnt[ny][nx]);
					if (fuel - cnt[ny][nx] >= 0) {
						fuel = fuel - cnt[ny][nx] + (cnt[ny][nx] * 2);
//						map[startY][startX].remove(goal);
//						if (map[startY][startX].size() == 0) {
//							map[startY][startX].add(0);
//						}
						ty = ny;
						tx = nx;
//						System.out.println(fuel);
						return true;
					} else {
//						System.out.println("?");
						return false;
					}
				} else if (map[ny][nx].get(0) != -1 && cnt[ny][nx] == 0) {
					que.offer(new Point(ny, nx));
					cnt[ny][nx] = cnt[p.y][p.x] + 1;
				}
			}
		}
		return false;

	}

	public static void pplBFS(int startY, int startX) {
		int[][] cnt = new int[N][N];
		Queue<Point> que = new LinkedList<>();
		que.offer(new Point(startY, startX));
		while (!que.isEmpty()) {
			Point p = que.poll();
			if (map[p.y][p.x].get(0) != 0) {
				pq.offer(new Point(p.y, p.x, map[p.y][p.x]));
//				System.out.println("?");
				return;
			}
			for (int d = 0; d < 4; d++) {
				int ny = p.y + dy[d];
				int nx = p.x + dx[d];

				if (!isSafe(ny, nx)) {
					continue;
				}

				if (map[ny][nx].get(0) != -1 && cnt[ny][nx] == 0) {
					if (map[ny][nx].get(0) == 0) {
						que.offer(new Point(ny, nx));
						cnt[ny][nx] = cnt[p.y][p.x] + 1;
					} else {
						cnt[ny][nx] = cnt[p.y][p.x] + 1;
						if (!pq.isEmpty() && cnt[pq.peek().y][pq.peek().x] == cnt[ny][nx]) {
							pq.add(new Point(ny, nx, map[ny][nx]));
//							System.out.println("#1 : " + ny + " " + nx + " " + map[ny][nx]);
//							System.out.println("거리 : " + cnt[ny][nx]);
						} else if (pq.isEmpty()) {
							pq.add(new Point(ny, nx, map[ny][nx]));
							fuel -= cnt[ny][nx];
//							System.out.println("#2 : " + ny + " " + nx + " " + map[ny][nx]);
//							System.out.println("거리 : " + cnt[ny][nx]);
							if (fuel < 0) {
								return;
							}
						} else {
							return;
						}
					}
				}
			}
//			System.out.println("=====cnt=====");
//			print(cnt);
		}
	}

	public static boolean isSafe(int y, int x) {
		if (y < N && x < N && y >= 0 && x >= 0) {
			return true;
		}
		return false;
	}

	static class Point implements Comparable<Point> {
		int y;
		int x;
		ArrayList<Integer> lst;

		public Point(int y, int x) {
			super();
			this.y = y;
			this.x = x;
		}

		public Point(int y, int x, ArrayList<Integer> lst) {
			super();
			this.y = y;
			this.x = x;
			this.lst = lst;
		}

		@Override
		public int compareTo(Point o) {
			if (this.y > o.y) {
				return 1;
			} else if (this.y == o.y) {
				return this.y - o.y;

			} else {
				return -1;
			}
		}

	}

	public static void print(ArrayList<Integer>[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				for (int k = 0; k < m[i][j].size(); k++) {
					System.out.print(m[i][j].get(k) + ",");
				}
				System.out.print("/");
			}
			System.out.println();
		}
	}

	public static void print(int[][] m) {
		for (int i = 0; i < m.length; i++) {
			for (int j = 0; j < m[i].length; j++) {
				System.out.print(m[i][j] + " ");
			}
			System.out.println();
		}
	}

}

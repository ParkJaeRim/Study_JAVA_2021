
public class programmers_정수삼각형 {
	public static void main(String[] args) {
		int[][] tri = { { 7 }, { 3, 8 }, { 8, 1, 0 }, { 2, 7, 4, 4 }, { 4, 5, 2, 6, 5 } };
		solution(tri);
	}

	public static int solution(int[][] triangle) {
		int answer = 0;
		int haeng = triangle.length;
		int yeol = triangle[haeng - 1].length;
		int[][] cal = new int[haeng][yeol];
		cal[0][0] = triangle[0][0];

		for (int i = 1; i < haeng; i++) {
			for (int j = 0; j < triangle[i].length; j++) {
				if (j == 0) {
					cal[i][j] = cal[i - 1][j] + triangle[i][j];
				} else if (j == triangle[i].length - 1) {
					cal[i][j] = cal[i - 1][j - 1] + triangle[i][j];
				} else {
					cal[i][j] = Math.max(cal[i - 1][j] + triangle[i][j], cal[i - 1][j - 1] + triangle[i][j]);
				}
			}
		}

		for (int i = 0; i < cal[haeng - 1].length; i++) {
			if (answer < cal[haeng - 1][i]) {
				answer = cal[haeng - 1][i];
			}
		}
		return answer;
	}
}

import java.util.Random;

public class BubbleSort {
	static int cnt;

	public static void main(String[] args) {
		Random rand = new Random();
		int[] num = new int[5];
		for (int i = 0; i < num.length; i++) {
			num[i] = rand.nextInt(10);
		}
		System.out.println("============정렬 전 배열============");
		print(num);
		cnt = 0;
		bubblesort(num);
		System.out.println("바꾼 횟수 : " + cnt);
	}

	public static void bubblesort(int[] num) {
		long now = System.currentTimeMillis();
		for (int i = 0; i < num.length; i++) {
			for (int j = 0; j < num.length - i - 1; j++) {
				if (num[j] > num[j + 1]) {
					int tmp = num[j];
					num[j] = num[j + 1];
					num[j + 1] = tmp;
					cnt++;
					print(num);
				} else {
					continue;
				}
			}
		}

		System.out.println("============SORTING 완료============");
		print(num);
		long after = System.currentTimeMillis();
		System.out.println("소요시간 : " + (after - now));
	}

	public static void print(int[] num) {
		for (int x = 0; x < num.length; x++) {
			System.out.print(num[x] + " ");
		}
		System.out.println();
	}

}

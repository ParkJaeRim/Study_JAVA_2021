import java.util.Random;

public class InsertionSort {

	public static void main(String[] args) {
		Random rand = new Random();
		int[] num = new int[10];
		for (int i = 0; i < num.length; i++) {
			num[i] = rand.nextInt(50);
		}
		print(num);
		insertionSort(num);
	}

	public static void insertionSort(int[] num) {
		for (int i = 1; i < num.length; i++) {
			int pivot = num[i];
			for (int j = i - 1; j >= 0; j--) {
				if (pivot < num[j]) {
					num[j + 1] = num[j];
					num[j] = pivot;
				}
			}
		}
		print(num);

	}

	public static void print(int[] num) {
		for (int x = 0; x < num.length; x++) {
			System.out.print(num[x] + " ");
		}
		System.out.println();
	}
}

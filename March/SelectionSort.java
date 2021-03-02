import java.util.Random;

public class SelectionSort {
	static int cnt;
	static long start;

	public static void main(String[] args) {
		Random rand = new Random();
		int[] num = new int[1000];
		for (int i = 0; i < num.length; i++) {
			num[i] = rand.nextInt(2000);
		}
		cnt = 0;
		System.out.println("============정렬 전 배열============");
		print(num);
		selectionSort(num, 0);
		selection_sort(num, num.length);
//		System.out.println("바꾼 횟수 : " + cnt);

	}

	private static void selection_sort(int[] a, int size) {

		for (int i = 0; i < size - 1; i++) {
			int min_index = i;
			for (int j = i + 1; j < size; j++) {
				if (a[j] < a[min_index]) {
					min_index = j;
				}
			}
			swap(a, min_index, i);
		}
		System.out.println("============SORTING 완료============");
		print(a);
	}

	private static void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}

	public static void selectionSort(int[] num, int pivot) {

		if (pivot == num.length) {
			System.out.println("============SORTING 완료============");
			print(num);
			return;
		}

		int minV = Integer.MAX_VALUE;
		int minIdx = -1;
		for (int i = pivot + 1; i < num.length; i++) {
			if (minV > num[i]) {
				minV = num[i];
				minIdx = i;
				cnt++;
			}
		}
		if (minIdx != -1 && num[pivot] > num[minIdx]) {
			int tmp = num[minIdx];
			num[minIdx] = num[pivot];
			num[pivot] = tmp;
		}

		selectionSort(num, pivot + 1);
	}

	public static void print(int[] num) {
		for (int x = 0; x < num.length; x++) {
			System.out.print(num[x] + " ");
		}
		System.out.println();
	}
}

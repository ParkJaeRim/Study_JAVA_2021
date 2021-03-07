
public class QuickSort {

	public static void main(String[] args) {

		int[] num = { 5, 3, 8, 4, 9, 1, 6, 2, 7 };
//		quickSort(num, 0, num.length - 1);
//		quicksort(num, 0, num.length);
		print(num);

	}

	public static void quicksort(int[] num, int start, int end) {
		print(num);

		if (end - start < 1) {
			return;
		}
		int leftPoint = start;
		int rightPoint = end - 1;
		int pivot = num[start];

		while (leftPoint <= rightPoint) {
			while (leftPoint <= end - 1 && pivot >= num[leftPoint]) {
				leftPoint++;
			}
			while (rightPoint >= start && pivot <= num[rightPoint]) {
				rightPoint--;
			}

			if (leftPoint <= rightPoint) {
				int swap = num[leftPoint];
				num[leftPoint] = num[rightPoint];
				num[rightPoint] = swap;
				leftPoint++;
				rightPoint--;
			}
		}

		// pivot을 맨 끝으로 잡으면 또 달라짐 if(leftPoint<= end)로 될듯
		if (rightPoint >= start) {
			int swap2 = num[start];
			num[start] = num[rightPoint];
			num[rightPoint] = swap2;
		}

		if (rightPoint >= start)
			quicksort(num, start, rightPoint);
		if (leftPoint <= end - 1)
			quicksort(num, leftPoint, end);
	}

	public static void quickSort(int[] num, int head, int tail) {
		print(num);

		if (tail - head < 1) {
			return;
		}
		int start = head;
		int end = tail;
		int pivot = num[(start + end) / 2];

		while (start <= end) {
			while (num[start] < pivot)
				start++;
			while (num[end] > pivot)
				end--;
			if (start <= end) {
				int tmp = num[start];
				num[start] = num[end];
				num[end] = tmp;
				start++;
				end--;
			}
		}

		quickSort(num, head, end);
		quickSort(num, start, tail);
	}

	public static void print(int[] num) {
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
	}
}

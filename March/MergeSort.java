
public class MergeSort {

	public static void main(String[] args) {

		int[] num = { 5, 3, 8, 4, 9, 1, 6, 2, 7 };

		divide(num, 0, num.length - 1);
	}

	public static void divide(int[] num, int start, int end) {
		if (start == end) {
			return;
		}
		int middle = (start + end) / 2;
		divide(num, start, middle);
		divide(num, middle + 1, end);
		// middle이 있어야 (start~middle / middle~end)끼리 합병가능
		merge(num, start, middle, end);
	}

	public static void merge(int[] num, int start, int mid, int end) {
		int[] newArr = new int[end - start + 1];
		int leftPoint = start;
		int rightPoint = mid + 1;
		int newIdx = 0;

		while (leftPoint <= mid && rightPoint <= end) {
			if (num[leftPoint] < num[rightPoint]) {
				newArr[newIdx] = num[leftPoint];
				leftPoint++;
				newIdx++;
			} else {
				newArr[newIdx] = num[rightPoint];
				rightPoint++;
				newIdx++;
			}
		}

		// 남은거는 무조건 상대배열보다 큰 값
		if (leftPoint > mid) {
			for (int i = rightPoint; i <= end; i++) {
				newArr[newIdx] = num[i];
				newIdx++;
			}
		}
		if (rightPoint > end) {
			for (int i = leftPoint; i <= mid; i++) {
				newArr[newIdx] = num[i];
				newIdx++;
			}
		}

		// 새배열 원래 배열로 복사하여 해당 구간 정렬
		for (int i = 0; i < newArr.length; i++) {
			num[i + start] = newArr[i];
		}

	}

	public static void print(int[] n) {
		for (int i = 0; i < n.length; i++) {
			System.out.print(n[i] + " ");
		}
		System.out.println();
	}
}

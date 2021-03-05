public class ShellSort {

	public static void main(String[] args) {
		int[] num = { 8, 1, 4, 2, 7, 6, 3, 5 };
		shellSort(num);
	}

	public static void shellSort(int[] num) {
		int n = num.length;
		for (int h = n / 2; h > 0; h /= 2) {
			for (int i = h; i < n; i++) {
				int pivot = num[i];
				for (int j = i; j >= 0; j -= h) {
					if (pivot < num[j]) {
						num[j + h] = num[j];
						num[j] = pivot;
					}
				}
			}
		}
		for (int i = 0; i < num.length; i++) {
			System.out.print(num[i] + " ");
		}
		System.out.println();
	}

}

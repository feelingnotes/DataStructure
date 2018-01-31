package array;

public class Array1 {
	
	//递增二维数组查找
	public static boolean searchMisInt(int p[][], int n) {
		if (p == null) {
			return false;
		}
		int i = p.length - 1;
		int j = 0;
		while (i >= 0 && j < p[0].length) {
			if (p[i][j] == n) {
				return true;
			} else if (p[i][j] > n) {
				i--;
			} else {
				j++;
			}
		}
		return false;
	}

	//查找旋转数组最小值
	public static int rotateMinIndex(int p[]) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start < end - 1) {
			mid = (start + end) / 2;
			if (p[start] == p[mid] && p[mid] == p[end]) {
				for (int i = start; i < end; i++) {
					if (p[i] > p[i + 1]) {
						return p[i + 1];
					}
				}
			}
			if (p[mid] >= p[start]) {
				start = mid;
			} else if (p[mid] <= p[end]) {
				end = mid;
			}
		}

		if (p[start] > p[end]) {
			return p[end];
		}
		return -1;
	}

	//奇数在前偶数在后
	public static void reverseOddEven(int p[]) {
		if (p == null) {
			return;
		}
		int start = 0;
		int end = p.length - 1;
		int temp;
		while (start < end) {
			while (start < end && p[start] % 2 != 0) {
				start++;
			}
			while (start < end && p[end] % 2 == 0) {
				end--;
			}
			temp = p[start];
			p[start] = p[end];
			p[end] = temp;
		}
	}

	//查找多于一半的元素
	public static int moreThanHalf(int p[]) {
		if (p == null) {
			return -1;
		}
		int count = 0;
		int index = 0;
		for (int i = 1; i < p.length; i++) {
			if (p[i] == p[index]) {
				index = i;
				count++;
			} else {
				count--;
			}
			if (count <= 0) {
				index = i;
				count = 0;
			}
		}
		if (count == 0) {
			return -1;
		} else {
			return p[index];
		}
	}
}

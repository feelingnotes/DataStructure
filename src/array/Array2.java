package array;

public class Array2 {

	// 调整堆
	public static void adjustHeap(int p[], int x, int k) {
		if (p == null || k > p.length) {
			return;
		}
		int temp = p[x];
		for (int i = 2 * x + 1; i < k; i += 2 * x + 1) {
			if (i + 1 < k && p[i] < p[i + 1]) {
				i++;
			}
			if (p[k] > temp) {
				p[i] = p[k];
				i = k;
			} else {
				break;
			}
		}
		p[x] = temp;
	}

	// 最小的k个数
	public static void minKthNum(int p[], int k) {
		if (p == null || k > p.length || k <= 0) {
			return;
		}
		int n[] = new int[k];
		for (int i = k / 2; i >= 0; i--) {
			adjustHeap(p, i, k);
		}
		for (int i = k; i < p.length; i++) {
			if (p[i] < p[0]) {
				p[0] = p[i];
				adjustHeap(p, 0, k);
			}
		}
		for (int i = 0; i < k; i++) {
			System.out.println(p[i]);
		}
	}

	// 连续子数组最大和
	public static void maxSumSubArray(int p[]) {
		if (p == null) {
			return;
		}
		int start = 0;
		int end = 0;
		int sum = 0;
		int max = 0;
		int indexs = 0;
		int indexe = 0;
		for (; end < p.length; end++) {
			sum += p[end];
			if (sum < 0) {
				sum = 0;
				start = end + 1;
			}
			if (sum > max) {
				max = sum;
				indexs = start;
				indexe = end;
			}
		}
		if (max == 0) {
			max = p[0];
			for (int i = 0; i < p.length; i++) {
				if (p[i] > max) {
					max = p[i];
					indexs = i;
					indexe = i;
				}
			}
		}
		for (int i = indexs; i <= indexe; i++) {
			System.out.println(p[i]);
		}
	}

	// 二维连续子数组最大和
	public static void maxSumSubArray2(int p[][]) {
		if (p == null) {
			return;
		}
		int startRow;
		int endRow;
		int startCol;
		int endCol;
		int sum = 0;
		int max = 0;
		int rs = 0;
		int re = 0;
		int cs = 0;
		int ce = 0;
		int tCols = 1;
		int ps[][] = new int[p.length + 1][p[0].length + 1];
		for (int i = 0; i < p.length + 1; i++) {
			ps[i][0] = 0;
		}
		for (int i = 0; i < p[0].length + 1; i++) {
			ps[0][i] = 0;
		}
		for (int i = 1; i < p.length + 1; i++) {
			for (int j = 1; j < p[0].length + 1; j++) {
				ps[i][j] = ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1] + p[i - 1][j - 1];
			}
		}
		for (startRow = 1; startRow < p.length + 1; startRow++) {
			for (endRow = startRow; endRow < p.length + 1; endRow++) {
				for (endCol = 1; endCol < p[0].length + 1; endCol++) {
					sum += ps[endRow][endCol] - ps[endRow][endCol - 1] - ps[startRow - 1][endCol]
							+ ps[startRow - 1][endCol - 1];
					if (sum < 0) {
						sum = 0;
						tCols = endCol + 1;
					}
					if (sum > max) {
						max = sum;
						cs = tCols - 1;
						ce = endCol - 1;
						rs = startRow - 1;
						re = endRow - 1;
					}
				}
				sum = 0;
				tCols = 1;
			}
		}
		System.out.println("最大和=" + max + ",起始行=" + rs + ",终止行=" + re + ",起始列=" + cs + ",终止列=" + ce);
	}

	// 逆序数字对个数
	public static int inversePairs(int p[], int start, int end) {
		if (p == null) {
			return 0;
		}
		if (start == end) {
			return 0;
		}
		if (end - start == 1) {
			if (p[end] < p[start]) {
				int temp = p[start];
				p[start] = p[end];
				p[end] = temp;
				return 1;
			} else {
				return 0;
			}
		}
		int mid = (start + end) / 2;
		int count = 0;
		int lCount = inversePairs(p, start, mid);
		int rCount = inversePairs(p, mid + 1, end);
		int i = mid;
		int j = end;
		int temp[] = new int[p.length];
		int t = end;
		while (start <= i && mid + 1 <= j) {
			if (p[i] > p[j]) {
				count += j - mid;
				temp[t] = p[i];
				i--;
				t--;
			} else {
				temp[t] = p[j];
				j--;
				t--;
			}
		}
		while (start <= i) {
			temp[t] = p[i];
			i--;
			t--;
		}
		while (mid + 1 <= j) {
			temp[t] = p[j];
			j--;
			t--;
		}
		return lCount + rCount + count;
	}
}

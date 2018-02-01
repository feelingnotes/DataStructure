package array;

public class Array3 {
	
	//获取数字第一次出现位置
	public static int getFirst(int p[], int i, int start, int end) {
		// TODO Auto-generated method stub
		if (p == null) {
			return -1;
		}
		if (start == end) {
			if (p[start] == i) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start + end) / 2;
		if (p[mid] >= i) {
			return getFirst(p, i, start, mid);
		} else {
			return getFirst(p, i, mid + 1, end);
		}
	}

	//获取数字最后一次出现位置
	public static int getLast(int p[], int i, int start, int end) {
		// TODO Auto-generated method stub
		if (p == null) {
			return -1;
		}
		if (start == end - 1) {
			if (p[start] == i) {
				return start;
			} else {
				return -1;
			}
		}
		int mid = (start + end) / 2;
		if (p[mid] > i) {
			return getLast(p, i, start, mid - 1);
		} else {
			return getLast(p, i, mid, end);
		}
	}

	//某数字出现次数
	public static int getNumberAppearTimes(int p[], int i) {
		if (p == null) {
			return -1;
		}
		int first = getFirst(p, i, 0, p.length - 1);
		int last = getLast(p, i, 0, p.length - 1);
		if (first == -1 || last == -1) {
			return -1;
		} else {
			return last - first + 1;
		}
	}

	//两个只出现一次的数字
	public static void getTwoAppearOnce(int p[]) {
		if (p == null) {
			return;
		}
		int n = 0;
		for (int i = 0; i < p.length; i++) {
			n = p[i] ^ n;
		}
		int x = 0;
		int y = 0;
		int diffBit = n ^ (n & (n - 1));
		for (int i = 0; i < p.length; i++) {
			if ((p[i] & diffBit) == 0) {
				x = x ^ p[i];
			} else {
				y = y ^ p[i];
			}
		}
		System.out.print(x + ", " + y);
	}

	//递增数组，找到和为s的两个数
	public static void findTwoNumberIsSumS(int p[], int s) {
		if (p == null) {
			return;
		}
		int start = 0;
		int end = p.length - 1;
		while (start != end) {
			if (p[start] + p[end] == s) {
				System.out.print(p[start] + " ");
				System.out.print(p[end]);
				break;
			} else if (p[start] + p[end] > s) {
				end--;
			} else {
				start++;
			}
		}
	}

	//和为sum的递增正数列
	public static void getSerialNumberSumN(int sum) {
		if (sum <= 2) {
			return;
		}
		int start = 1;
		int end = 2;
		int mid = (start + sum) / 2;
		int tsum = 3;
		while (start < mid) {
			if (tsum == sum) {
				System.out.print(start + "~" + end + "\n");
			} else if (tsum > sum) {
				while (tsum > sum) {
					tsum -= start;
					start++;
				}
				continue;
			}
			end++;
			tsum += end;
		}
	}

	//最大最小值辅助类
	public static class ValueHolder {
		int maxNum;
		int minNum;
	}

	//获取最大最小值
	public static ValueHolder getMaxMinNumber(int p[], int start, int end) {
		if (p == null) {
			return null;
		}
		ValueHolder valueHolder = new ValueHolder();
		if (start == end) {
			valueHolder.maxNum = p[start];
			valueHolder.minNum = p[start];
			return valueHolder;
		}
		int mid = (start + end) / 2;
		int lmax = getMaxMinNumber(p, start, mid).maxNum;
		int lmin = getMaxMinNumber(p, start, mid).minNum;
		int rmax = getMaxMinNumber(p, mid + 1, end).maxNum;
		int rmin = getMaxMinNumber(p, mid + 1, end).minNum;
		if (lmax > rmax) {
			valueHolder.maxNum = lmax;
		} else {
			valueHolder.maxNum = rmax;
		}
		if (lmin < rmin) {
			valueHolder.minNum = lmin;
		} else {
			valueHolder.minNum = rmin;
		}
		return valueHolder;
	}
}

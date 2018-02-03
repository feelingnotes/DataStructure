package array;

public class Array4 {
	
	// 查找最长递增子序列。
	static void searchMaxIncSubArray(int p[], int length) {
		int[] maxValue = new int[length + 1];
		maxValue[1] = p[0];
		int k = 1;
		for (int i = 1; i < length; i++) {
			if (p[i] > maxValue[k]) {
				k++;
				maxValue[k] = p[i];
			} else if (p[i] == maxValue[k]) {
				continue;
			} else {
				if (p[i] < maxValue[1]) {
					maxValue[1] = p[i];
					continue;
				}
				int first = 1;
				int last = k;
				// 二分查找
				while (first < last) {
					int mid = (last + first) / 2;
					if (maxValue[mid] <= p[i]) {
						first = mid + 1;
					} else {
						last = mid;
					}
				}
				if (p[i] < maxValue[first]) {
					maxValue[first] = p[i];
				}
			}
		}
		System.out.println("k = " + k);
	}

	//区间辅助类
	static class Area {
		int start;
		int end;

		Area(int start, int end) {
			this.start = start;
			this.end = end;
		}
	}

	//区间排序
	public static void sortArea(Area areas[], int start, int end) {
		if (areas == null) {
			return;
		}
		if (start < end) {
			int i = start + 1;
			int j = end;
			Area temp = null;
			while (i <= j) {
				while (i <= j && areas[i].start < areas[start].start) {
					i++;
				}
				while (i <= j && areas[j].start > areas[start].start) {
					j--;
				}
				if (i <= j) {
					temp = areas[i];
					areas[i] = areas[j];
					areas[j] = temp;
				}
			}
			temp = areas[start];
			areas[start] = areas[j];
			areas[j] = temp;
			sortArea(areas, start, j - 1);
			sortArea(areas, j + 1, end);
		}
	}

	//合并区间
	public static int mergeArea(Area areas[]) {
		if (areas == null) {
			return 0;
		}
		int index = 0;
		Area lastArea = areas[0];
		for (int i = 1; i < areas.length; i++) {
			if (areas[i].start <= lastArea.end) {
				if (areas[i].end >= lastArea.end) {
					lastArea.end = areas[i].end;
					areas[index] = lastArea;
				}
			} else {
				lastArea = areas[i];
				index++;
				areas[index] = lastArea;
			}
		}
		return index + 1;
	}

	//搜索区间是否在范围内
	public static boolean searchArea(Area areas[], Area area) {
		if (areas == null || area == null) {
			return false;
		}
		int start = 0;
		int end = areas.length - 1;
		int mid = 0;
		while (start <= end) {
			mid = (start + end) / 2;
			if (areas[mid].start > area.start) {
				if (areas[mid].end < area.end) {
					return false;
				} else {
					end = mid - 1;
				}
			} else if (areas[mid].start <= area.start && areas[mid].start <= area.end) {
				if (areas[mid].end >= area.end) {
					return true;
				} else {
					return false;
				}
			} else {
				start = mid + 1;
			}
		}
		return false;
	}

	//寻找是否有分割数组的最大值m ，使各份的和相等
	public static boolean find(int p[], int flag[], int cursum, int groupSum, int id, int groupLen) {
		if (p == null || flag == null) {
			return false;
		}
		if (cursum < 0) {
			return false;
		} else if (cursum == 0) {
			id++;
			cursum = groupSum;
			if (groupLen < id) {
				return true;
			}
		}

		for (int i = 0; i < p.length; i++) {
			if (flag[i] != 0) {
				continue;
			}
			flag[i] = id;
			if (find(p, flag, cursum - p[i], groupSum, id, groupLen)) {
				return true;
			}
			flag[i] = 0;
		}

		return false;
	}

	//一个整数数组，长度为 n，将其分为 m 份，使各份的和相等，求 m 的最大值
	public static void maxGroup(int p[]) {
		if (p == null) {
			return;
		}
		int flag[] = new int[p.length];
		int sum = 0;
		int groupSum;
		for (int i = 0; i < p.length; i++) {
			sum += p[i];
		}
		for (int i = p.length; i >= 2; i--) {
			if (sum % i != 0) {
				continue;
			}
			groupSum = sum / i;
			if (find(p, flag, groupSum, groupSum, 1, i)) {
				for (int j = 0; j < p.length; j++) {
					System.out.println(flag[j]);
				}
			}
		}
	}
}

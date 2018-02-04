package binarysearch;

public class binarySearch {

	//二分查找
	public static int binNormalSearch(int p[], int key) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start <= end) {
			mid = (start + end) / 2;
			if (p[mid] == key) {
				return mid;
			} else if (p[mid] > key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		return -1;
	}

	//二分查找第一次出现的位置
	public static int binFirstKSearch(int p[], int key) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start < end) {
			mid = (start + end) / 2;
			if (p[mid] == key) {
				end = mid;
			} else if (p[mid] > key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		if (p[start] == key) {
			return start;
		}
		return -1;
	}

	//二分查找最后一次出现的位置
	public static int binLastKSearch(int p[], int key) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start < end - 1) {
			mid = (start + end) / 2;
			if (p[mid] == key) {
				start = mid;
			} else if (p[mid] > key) {
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		if (p[end] == key) {
			return end;
		}
		if (p[start] == key) {
			return start;
		}
		return -1;
	}

	//查找小于关键字的最大数字出现的位置
	public static int binMaxLessKSearch(int p[], int key) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start < end - 1) {
			mid = (start + end) / 2;
			if (p[mid] >= key) {
				end = mid - 1;
			} else {
				start = mid;
			}
		}
		if (p[end] < key) {
			return p[end];
		} else if (p[start] < key) {
			return p[start];
		} else {
			return -1;
		}
	}

	//查找大于关键字的最小数字出现的位置
	public static int binMinMoreKSearch(int p[], int key) {
		if (p == null) {
			return -1;
		}
		int start = 0;
		int end = p.length - 1;
		int mid;
		while (start < end - 1) {
			mid = (start + end) / 2;
			if (p[mid] <= key) {
				start = mid + 1;
			} else {
				end = mid;
			}
		}
		if (p[start] > key) {
			return p[start];
		} else if (p[end] > key) {
			return p[end];
		} else {
			return -1;
		}
	}
}

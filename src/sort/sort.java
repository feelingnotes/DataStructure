package sort;

public class sort {
	
	//²åÈëÅÅĞò
	public static void insertSort(int p[]) {
		if (p == null || p.length <= 0) {
			return;
		}
		int tmp;
		for (int i = 1; i < p.length; i++) {
			for (int j = i; j > 0; j--) {
				if (p[j] < p[j - 1]) {
					tmp = p[j];
					p[j] = p[j - 1];
					p[j - 1] = tmp;
				}
			}
		}
	}

	//Ï£¶ûÅÅĞò
	public static void shellSort(int p[]) {
		if (p == null || p.length <= 0) {
			return;
		}
		int length = p.length / 2;
		int temp;
		while (length != 0) {
			for (int i = 0; i < p.length; i++) {
				for (int j = i; j >= length; j -= length) {
					if (p[j] < p[j - length]) {
						temp = p[j];
						p[j] = p[j - length];
						p[j - length] = temp;
					}
				}
			}
			length = length / 2;
		}
	}

	//Ñ¡ÔñÅÅĞò
	public static void selectSort(int p[]) {
		if (p == null || p.length <= 0) {
			return;
		}
		int temp;
		for (int i = 0; i < p.length - 1; i++) {
			int index = i;
			for (int j = i + 1; j < p.length; j++) {
				if (p[j] < p[index]) {
					index = j;
				}
			}
			if (index != i) {
				temp = p[i];
				p[i] = p[index];
				p[index] = temp;
			}
		}
	}

	//Ã°ÅİÅÅĞò
	public static void bubbleSort(int p[]) {
		if (p == null || p.length <= 0) {
			return;
		}
		int temp;
		for (int i = 0; i < p.length - 1; i++) {
			for (int j = 0; j < p.length - i - 1; j++) {
				if (p[j] > p[j + 1]) {
					temp = p[j];
					p[j] = p[j + 1];
					p[j + 1] = temp;
				}
			}
		}
	}

	//¼ÆÊıÅÅĞò
	public static int[] countSort(int p[]) {
		if (p == null || p.length <= 0) {
			return null;
		}
		int count = 0;
		int q[] = new int[p.length];
		for (int i = 0; i < p.length; i++) {
			for (int j = 0; j < p.length; j++) {
				if (p[i] > p[j]) {
					count++;
				}
			}
			q[count] = p[i];
			count = 0;
		}
		return q;
	}

	//»ùÊıÅÅĞò£¨»ñÈ¡Î»Êı×Ö£©
	public static int getDigit(int x, int d) {
		int n[] = { 1, 10, 100 };
		return x / n[d] % 10;
	}

	//»ùÊıÅÅĞò
	public static void radixSort(int p[], int d) {
		if (p == null || p.length <= 0) {
			return;
		}
		int index;
		int temp[] = new int[p.length];
		int count[] = new int[10];
		for (int i = 0; i < d; i++) {

			for (int j = 0; j < 10; j++) {
				count[j] = 0;
			}

			for (int j = 0; j < p.length; j++) {
				index = getDigit(p[j], i);
				count[index]++;
			}

			for (int j = 1; j < 10; j++) {
				count[j] = count[j] + count[j - 1];
			}

			for (int m = p.length - 1; m >= 0; m--) {
				index = getDigit(p[m], i);
				temp[count[index] - 1] = p[m];
				count[index]--;
			}

			for (int m = 0; m < p.length; m++) {
				p[m] = temp[m];
			}
		}
	}

	//¹é²¢ÅÅĞò
	public static void mergeSort(int p[], int start, int end) {
		if (p == null || p.length <= 0) {
			return;
		}
		if (end == start) {
			return;
		}
		int middle = (start + end) / 2;
		int temp[] = new int[p.length];
		int i, j, t;
		mergeSort(p, start, middle);
		mergeSort(p, middle + 1, end);
		i = start;
		j = middle + 1;
		t = start;
		while (i <= middle && j <= end) {
			if (p[i] < p[j]) {
				temp[t] = p[i];
				i++;
				t++;
			} else {
				temp[t] = p[j];
				j++;
				t++;
			}
		}
		
		while (i <= middle) {
			temp[t] = p[i];
			i++;
			t++;
		}

		while (j <= end) {
			temp[t] = p[j];
			j++;
			t++;
		}
		
		for (int m = start; m <= end; m++) {
			p[m] = temp[m];
		}
	}

	//¿ìËÙÅÅĞò
	public static void quickSort(int p[], int start, int end) {
		if (p == null || p.length <= 0) {
			return;
		}
		if (start < end) {
			int key = p[start];
			int l = start + 1;
			int r = end;
			int temp;
			while (l <= r) {
				while (l <= r && p[l] < key) {
					l++;
				}
				while (l <= r && p[r] > key) {
					r--;
				}
				if (l <= r) {
					temp = p[l];
					p[l] = p[r];
					p[r] = temp;
					l++;
					r--;
				}
			}
			temp = p[r];
			p[r] = p[start];
			p[start] = temp;
			quickSort(p, start, r - 1);
			quickSort(p, r + 1, end);
		}
	}

	//¶ÑÅÅĞò£¨µ÷Õû¶Ñ£©
	public static void adjustHeap(int p[], int i, int length) {
		int temp = p[i];
		for (int k = 2 * i + 1; k < length; k = 2 * k + 1) {
			if (k + 1 < length && p[k] < p[k + 1]) {
				k++;
			}
			if (p[k] > p[i]) {
				p[i] = p[k];
				i = k;
			}
		}
		p[i] = temp;
	}

	//¶ÑÅÅĞò
	public static void heapSort(int p[]) {
		if (p == null || p.length <= 0) {
			return;
		}
		int temp;
		for (int i = p.length / 2 - 1; i >= 0; i--) {
			adjustHeap(p, i, p.length);
		}
		for (int i = p.length - 1; i > 0; i--) {
			temp = p[0];
			p[0] = p[i];
			p[i] = temp;
			adjustHeap(p, 0, i);
		}
	}
}

package string;

public class String1 {

	// 替换空格
	public static char[] replaceSpace(char p[], char r[]) {
		if (p == null || r == null || p.length == 0) {
			return null;
		}
		int count = 0;
		int oriLength = 0;
		while (p[oriLength] != 0) {
			if (p[oriLength] == ' ') {
				count++;
			}
			oriLength++;
		}
		count = oriLength + (r.length - 1) * count - 1;
		oriLength--;
		while (oriLength != count && oriLength >= 0) {
			if (p[oriLength] == ' ') {
				for (int j = r.length - 1; j >= 0; j--) {
					p[count] = r[j];
					count--;
				}
				oriLength--;
			} else {
				p[count] = p[oriLength];
				count--;
				oriLength--;
			}
		}
		return p;
	}

	// 打印全排列
	public static void permutationStr(char p[], int start) {
		if (p == null) {
			return;
		}
		if (start == p.length) {
			System.out.println(p);
			return;
		}
		char c;
		for (int i = start; i < p.length; i++) {
			c = p[start];
			p[start] = p[i];
			p[i] = c;
			permutationStr(p, start + 1);
			c = p[start];
			p[start] = p[i];
			p[i] = c;
		}
	}

	// 第一个不重复字符
	public static char firstNotRepeat(char p[]) {
		if (p == null) {
			return 0;
		}
		int ch[] = new int[256];
		for (int i = 0; i < p.length; i++) {
			ch[p[i]]++;
		}
		for (int i = 0; i < p.length; i++) {
			if (ch[p[i]] == 1) {
				return p[i];
			}
		}
		return 0;
	}

	// 翻转单词
	public static void reverseSub(char p[], int start, int end) {
		char c;
		int i = start;
		int j = end;
		while (i < j) {
			c = p[i];
			p[i] = p[j];
			p[j] = c;
			i++;
			j--;
		}
	}

	// 翻转句子
	public static void reverseSentence(char p[]) {
		if (p == null) {
			return;
		}
		int start = 0;
		int end = 0;
		reverseSub(p, 0, p.length - 1);
		while (start < p.length) {
			if (p[start] == ' ') {
				start++;
				end++;
			} else if (end == p.length || p[end] == ' ') {
				reverseSub(p, start, --end);
				start = ++end;
			} else {
				end++;
			}
		}
	}
}

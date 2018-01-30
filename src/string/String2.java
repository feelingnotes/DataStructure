package string;

import java.util.ArrayList;

public class String2 {

	// 字符串快速排序（compareTo）
	public static void quickSort(ArrayList<String> strings, int start, int end) {
		if (strings == null) {
			return;
		}
		if (start < end) {
			String s = strings.get(start);
			int l = start + 1;
			int r = end;
			String temp;
			while (l <= r) {
				while (l <= r && s.compareTo(strings.get(l)) > 0) {
					l++;
				}
				while (l <= r && s.compareTo(strings.get(r)) < 0) {
					r--;
				}
				if (l <= r) {
					temp = strings.get(l);
					strings.set(l, strings.get(r));
					strings.set(r, temp);
					l++;
					r--;
				}
			}
			temp = strings.get(start);
			strings.set(start, strings.get(r));
			strings.set(r, temp);
			quickSort(strings, start, r - 1);
			quickSort(strings, r + 1, end);
		}
	}

	// 字符串重复性比较
	public static String compare(ArrayList<String> strings) {
		int max = 0;
		String r = null;
		for (int i = 0; i < strings.size() - 1; i++) {
			int up = 0;
			int down = 0;
			int number = 0;
			while (up < strings.get(i).length() && down < strings.get(i + 1).length()) {
				if (strings.get(i).charAt(up) == strings.get(i + 1).charAt(down)) {
					up++;
					down++;
					number++;
				} else {
					break;
				}
			}
			if (number > max) {
				max = number;
				r = strings.get(i).substring(0, up);

			}
		}
		return r;
	}

	// 最长重复字符串
	public static String longComStr(String str) {
		if (str == null) {
			return null;
		}
		ArrayList<String> strings = new ArrayList<>();
		for (int i = 0; i < str.length(); i++) {
			if (str.charAt(i) != ' ') {
				strings.add(str.substring(i, str.length()));
			}
		}
		quickSort(strings, 0, strings.size() - 1);
		return compare(strings);
	}

	// 最长回文字符串
	public static int maxSynStr(String str) {
		if (str == null) {
			return 0;
		}
		StringBuffer s = new StringBuffer();
		for (int i = 0; i < str.length(); i++) {
			s.append("#");
			s.append(str.charAt(i));
		}
		s.append("#");
		int p[] = new int[s.length()];
		int mid = 0;
		int max = 0;
		int count;
		for (int i = 0; i < str.length(); i++) {
			count = 0;
			if (i >= max) {
				while (i - count >= 0 && i + count < str.length() * 2 + 1
						&& s.charAt(i - count) == s.charAt(i + count)) {
					count++;
				}
				p[i] = count;
				mid = i;
				max = mid + count - 1;
			} else {
				if (p[2 * mid - i] < max - i) {
					p[i] = p[2 * mid - i];
				} else {
					while (i - count >= 0 && i + count < str.length() * 2 + 1
							&& s.charAt(i - count) == s.charAt(i + count)) {
						count++;
					}
					p[i] = count;
					mid = i;
					max = mid + count - 1;
				}
			}
		}
		count = 0;
		for (int i = 0; i < p.length; i++) {
			if (p[i] > count) {
				count = p[i];
				mid = i;
			}
		}
		return count - 1;
	}

	// 移动*位置
	public static char[] moveNullCharPos(String str) {
		if (str == null) {
			return null;
		}
		char ch[] = str.toCharArray();
		int last = str.length();
		char c;
		for (int i = str.length() - 1; i >= 0; i--) {

			if (ch[i] != '*') {
				last--;
				c = ch[i];
				ch[i] = ch[last];
				ch[last] = c;
			}
		}
		return ch;
	}

	// 倒序字符串不使用temp
	public static char[] reverWithoutTemp(String str) {
		if (str == null) {
			return null;
		}
		char ch[] = str.toCharArray();
		int start = 0;
		int end = ch.length - 1;
		while (start < end) {
			ch[start] = (char) (ch[start] ^ ch[end]);
			ch[end] = (char) (ch[start] ^ ch[end]);
			ch[start] = (char) (ch[start] ^ ch[end]);
			start++;
			end--;
		}
		return ch;
	}

	// 寻找关键字
	public static int findKey(String str, String[] str2) {
		for (int i = 0; i < str2.length; i++) {
			if (str2[i].equals(str)) {
				return i;
			}
		}
		return -1;
	}

	// 最短摘要
	public static String[] calMinAbst(String[] str1, String[] str2) {
		if (str1 == null || str2 == null) {
			return null;
		}
		String str[] = new String[str1.length];
		int start = 0;
		int end = 0;
		int flag[] = new int[str2.length];
		int flagSize = 0;
		int find = 0;
		int min = str1.length;
		int m1 = 0;
		int m2 = 0;
		while (start <= end && end < str1.length) {
			while (flagSize != str2.length && end < str1.length) {
				find = findKey(str1[end], str2);
				if (find != -1) {
					if (flag[find] == 0) {
						flagSize++;
					}
					flag[find]++;
				}
				end++;
			}
			while (flagSize == str2.length) {
				if (end - start < min) {
					min = end - start;
					m1 = start;
					m2 = end - 1;
				}
				find = findKey(str1[start++], str2);
				if (find != -1) {
					flag[find]--;
					if (flag[find] == 0) {
						flagSize--;
					}
				}
			}
		}
		for (int i = m1; i <= m2; i++) {
			str[i - m1] = str1[i];
		}
		return str;
	}

	// 最长公共字符串（动态规划）
	public static void LCS(String str1, String str2) {
		if (str1 == null || str2 == null) {
			return;
		}
		int lstr1 = str1.length();
		int lstr2 = str2.length();
		char c1[] = str1.toCharArray();
		char c2[] = str2.toCharArray();
		int ch[][] = new int[lstr1 + 1][lstr2 + 1];

		for (int i = 1; i <= lstr1; i++) {
			for (int j = 1; j <= lstr2; j++) {
				if (c1[i - 1] == c2[j - 1]) {
					ch[i][j] = ch[i - 1][j - 1] + 1;
				} else if (ch[i - 1][j] > ch[i][j - 1]) {
					ch[i][j] = ch[i - 1][j];
				} else {
					ch[i][j] = ch[i][j - 1];
				}
			}
		}
		int l = ch[lstr1][lstr2];
		char temp[] = new char[l + 1];
		int i = c1.length;
		int j = c2.length;
		while (i > 0 && j > 0) {
			if (ch[i][j] == ch[i][j - 1]) {
				j--;
			} else if (ch[i][j] == ch[i - 1][j]) {
				i--;
			} else if (ch[i][j] == ch[i - 1][j - 1] + 1) {
				temp[ch[i][j]] = c1[i - 1];
				i--;
				j--;
			}
		}
		for (int n = 1; n <= l; n++) {
			System.out.print(temp[n]);
		}
	}
}

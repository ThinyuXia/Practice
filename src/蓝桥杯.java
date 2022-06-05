//import java.util.*;
//public class Main {
//	
//	public static void main(String[] args) {


//		Scanner in = new Scanner(System.in);

//		int n = in.nextInt();
//		int m = in.nextInt();
//		int k = in.nextInt();
//		double v = in.nextDouble() / 2.0;
//		long ans = 0;
//		
//		int sumSeconds = 0;
//		int cnt = 1;
//		while(m -- != 0) {
//			int len = in.nextInt();
//			int gs = in.nextInt();
//			int rs = in.nextInt();
//			cnt ++;
//			if(cnt % k == 0) {
//				n -= len;
//			}else {
//				if(cnt % 2 == 0)
//					ans += gs;
//				else 
//					ans += rs;
//				ans += len / v;
//			}
//			
//			
//		}
//		System.out.println(ans);
//	}
//	
//}

//90 2 2 2
//30 20 20
//60 20 20

//import java.util.*;
//public class Main {
//	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		int[] a = new int[n + 1];
//		for(int i = 1;i <= n;i ++) a[i] = in.nextInt();
//		long ans = 0;
//		for(int i = 1;i <= n;i ++) {
//			for(int j = i;j <= n;j ++) {
//				boolean flag = true;
//				for(int i1 = i;i1 < j;i1 ++) {
//					if(a[i1] - a[i1  + 1] != 1) {
//						flag = false;
//						break;
//					}
//				}
//				if(flag) ans ++;
//			}
//		}
//		System.out.println(ans);
//		
//	}
//	
//}



//
//import java.util.*;
//public class Main {
//	
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int m = in.nextInt();
//		int n = in.nextInt();
//		int[][] a = new int[m + 1][n + 1];
//		int[][] sum = new int[m + 1][n + 1];
//		for(int i = 1;i <= m;i ++)
//			for(int j = 1;j <= n;j ++)
//				a[i][j] = in.nextInt();
//		int limit = in.nextInt();
////		for(int i = 1;i <= m;i ++) {
////			for(int j = 1;j <= n;j ++)
////				System.out.print(a[i][j] + " ");
////			System.out.println();
////		}
//		
////		for(int i = 1;i <= m;i ++) {
////			for(int j = 1;j <= n;j ++) {
////				a[i][j] += a[i - 1][j] + a[i][j - 1] - a[i - 1][j - 1]; 
////				System.out.print(a[i][j] + " ");
////			}
////			System.out.println();
////		}
//		int ans = 0;
//		for(int i = 1;i <= m;i ++) {
//			for(int j = 1;j <= n;j ++) {
//				boolean flag = true;
//				for(int x = i;x <= m && flag;x ++)
//					for(int y = j;y <= n;y ++)
//						if(cal(i,j,x,y,a) <= limit) {
//							ans = Math.max(ans,(x - i) * (y - j));
////							System.out.println("" + i + j + x  + y);
//							
//						}
//			}
//		}
//		System.out.println(ans);
//		
//	}
//	public static int cal(int x1,int y1,int x2,int y2,int[][] a) {
//		int max = Integer.MIN_VALUE;
//		int min = Integer.MAX_VALUE;
//		for(int i = x1;i <= x2;i ++) {
//			for(int j = y1;j <= y1;j ++) {
//				max = Math.max(max, a[i][j]);
//				min = Math.min(min, a[i][j]);
//			}
//		}
//		return max - min;
//	}
//	
//}



//import java.util.*;
//public class Main {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		long k = in.nextLong();
//		int cnt = 0;
//		int cnt2 = 0;
//		int cnt5 = 0;
//		if(k >= 100000) System.out.println(-1);
//		else 
//		for(int i = 1;i <= Integer.MAX_VALUE;i ++) {
//			if(i % 10 == 0) { 
//				int t = i;
//				while(t != 0 && t %10 == 0) {
//					cnt ++;
//					t /= 10;
//				}
//			}else if(i % 5 == 0) {
//				cnt2 ++;
//			}else if(i % 2 == 0) {
//				cnt5 ++;
//			}
//			while(cnt2 != 0 && cnt5 != 0) {
//				cnt2 --;
//				cnt5 --;
//				cnt ++;
//			}
////			System.out.println(i);
//			if(cnt == k) {
//				System.out.println(i);
//				break;
//			}
//		}
//		
////		System.out.println();
//	}
//}

//import java.util.*;
//public class Main {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = in.nextInt();
//		int[] a = new int[n];
//		int[] b = new int[n];
//		int[] ans = new int[n];
//		for(int i = 0;i < n;i ++) {
//			a[i] = in.nextInt();
//			b[i] = a[i];
//		}
//		Arrays.sort(b);
//		int cnt = 0;
//		for(int i = 0;i < n;i ++) {
//			if(b[i] == b[n / 2]) cnt ++;
//		}
//		
////		if(n / 2 - 1 >= 0) {
////			int idx = n / 2 - 1;
////			while(b[idx] == b[n / 2]) {
////				b[n / 2 - 1] = 
////			}
////		}
//		
//		if(a.length % 2 == 1) {
//			for(int i = 0;i < n;i ++) 
//				if(a[i] > b[n/ 2])
//					ans[i] = 0;
//				else if(a[i] == b[n / 2]){
//					 	if(cnt != 0 && cnt % 2 == 1) ans[i] = 0;
//					 	else ans[i] = 1;
//						continue;
//				}else 
//					ans[i] = b[n / 2] - a[i] + 1;
//			
//		}else {
//			for(int i = 0;i < n;i ++) 
//				if(a[i] > b[n/ 2])
//					ans[i] = 0;
//				else if(a[i] == b[n / 2]) {
//					if(cnt != 0 && cnt % 2 == 0) ans[i] = 0;
//				 	else ans[i] = 1;
//					continue;
//				}else
//					ans[i] = b[n / 2] - a[i] + 1;
//		}
//		
//		for(int i = 0;i < n;i ++)
//			System.out.print(ans[i] + " ");
//	}
//}



import java.util.*;
public class 蓝桥杯 {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String s = in.nextLine();
		int[] cnt = new int[26];
		for(int i = 0;i < s.length();i ++) {
			cnt[s.charAt(i) - 'A'] ++;
		}
		int max = 0;
		for(int i = 0;i < 26;i ++)
			max = Math.max(max, cnt[i]);
		for(int i = 0;i < 26;i ++)
			if(cnt[i] == max)
				System.out.print((char)('A' + i));
	}
}

//import java.util.*;
//public class Main {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int n = 2022222022;
//		long ans = 0;
//		for(int i = 2022;i <= n;i ++) 
//			if(check(i)) ans ++;
//		System.out.println(ans);
//		System.out.println(check(2022));
//	}
//
//public static boolean check(int n) {
//		String s = String.valueOf(n);
//		for(int i = 0,j = s.length() - 1;i < s.length() / 2;i ++,j --) {
//			if(s.charAt(i) != s.charAt(j)) return false;
//		} //�жϻ���
//		for(int i = 1;i < s.length() / 2;i ++) {
//			if(s.charAt(i) < s.charAt(i - 1)) return false;
//		}
//		for(int i = s.length() / 2; i < s.length() - 1;i ++) {
//			if(s.charAt(i) < s.charAt(i + 1)) return false;
//		}
//		return true;
//	}
//}

//import java.util.*;
//import java.math.BigInteger;
//public class Main {
//	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		BigInteger n = new BigInteger("20");
//		for(int i = 1;i <= 19;i ++) {
//			n = n.multiply(new BigInteger("20"));
//		}
//		System.out.println(n.remainder(new BigInteger("7")));
//	}
//}

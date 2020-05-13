import java.io.*;
import java.util.*;

class node {
	double x, y, angle;
	public node(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static node[] points;
	static long[] s;
	static ArrayList<Deque<Integer>> q;
	static ArrayList<Vector<Integer>> in, out;
	static double[][] L;
	
	static double cross(int a, int b, int c) {
		return (points[b].x - points[a].x) * (points[c].y - points[a].y) - (points[c].x - points[a].x) * (points[b].y - points[a].y);
	}
	
	static void proceed(int i, int j) {
		while(!q.get(i).isEmpty() && cross(q.get(i).getFirst(), i, j) > 0) {
			proceed(q.get(i).getFirst(), j);
			q.get(i).pollFirst();
		}
		out.get(i).add(j);
		q.get(j).addLast(i);
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		s = new long[(n + 10) * 2];
		s[0] = Integer.parseInt(br.readLine());
		int m = Integer.parseInt(br.readLine());
		points = new node[n + 10];
		q = new ArrayList<Deque<Integer>>(n);
		in = new ArrayList<Vector<Integer>>(n);
		out = new ArrayList<Vector<Integer>>(n);
		L = new double[n + 10][n + 10];
		
		for (int i = 1; i <= n * 2; i++) {
			s[i] = (long)s[i - 1] * s[i - 1] % m;
		}
		
		for (int i = 1; i <= n; i++) {
			double x = s[2 * i - 1] % 2000 - 1000.0;
			double y = s[2 * i] % 2000 - 1000.0;
			
			Random rand = new Random();
			x += rand.nextInt() % 10 * 1e-5;
			y += rand.nextInt() % 10 * 1e-5;
			points[i] = new node(x, y);
		}
		
		for (int i = 1; i <= n; i++)
			for (int j = 1; j <= n; j++)
				for (int k = 1; k <= n; k++)
					if (i != j && j != k && i != k && cross(i, j, k) == 0)
						System.out.println("colinear.");
		
		double maxarea = 0.0;
		Comparator<node> comp = new Comparator<node>() {
			@Override
			public int compare(node a, node b) {
				if (a.angle < b.angle)
					return -1;
				return 1;
			}
		};
		Comparator<node> compx = new Comparator<node>() {
			@Override
			public int compare(node a, node b) {
				if (a.x < b.x)
					return 1;
				return -1;
			}
		};
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int p = n; p > 0; p--) {
			Arrays.sort(points, 1, p + 1, compx);
			for (int i = 1; i < p; i++) {
				points[i].angle = Math.atan2(points[i].y - points[p].y, points[i].x - points[p].x);
			}
			Arrays.sort(points, 1, p, comp);
			
			for (int i = 0; i < p; i++) {
				q.add(new ArrayDeque<Integer>());
				q.get(i).clear();
				in.add(new Vector<Integer>());
				in.get(i).clear();
				out.add(new Vector<Integer>());
				out.get(i).clear();
			}
			for (int i = 1; i < p - 1; i++) {
				proceed(i, i + 1);
			}
			for (int i = 1; i < p; i++) {
				for (int j : out.get(i)) {
					in.get(j).add(i);
					L[i][j] = 0;
				}
			}
			for (int i = p - 1; i > 0; i--) { 
				double d = 0;
				for (int j = in.get(i).size() - 1, l = out.get(i).size() - 1; j >= 0; j--) {
					while(l >= 0 && cross(i, in.get(i).get(j), out.get(i).get(l)) < 0) {
						d = (d > L[i][out.get(i).get(l)]) ? d : L[i][out.get(i).get(l)];
						l--;
					}
					double weight = cross(p, in.get(i).get(j), i);
					L[in.get(i).get(j)][i] = d + weight;
				}
			}
			
			for (int i = 1; i < p; i++) {
				for (int j : out.get(i)) {
					if (L[i][j] != 0)
						bw.append(L[i][j] + " ");
				}
				bw.append("\n");
				bw.flush();
			}
			
			for (int i = 1; i < p; i++) {
				for (int j : out.get(i)) {
					if (L[i][j] > maxarea) {
						maxarea = L[i][j];
					}
				}
			}
		}
		System.out.printf("%.1f\n%d", maxarea/2);
		
	} // main method close
} // Main class close

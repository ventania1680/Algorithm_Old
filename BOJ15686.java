import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

class pair {
	int x, y;

	public pair(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	static int n, m, min, cs, hs;
	static ArrayList<pair> chicken, house;

	public static void DFS(ArrayList<pair> newchicken, int qty, int cur) {
		if (qty > m || qty + cs - cur < m)
			return;
		if (qty == m) { // when all of chicken place chosen, compare minimum and current chicken distance
			int mindist, totaldist = 0;
			int newcs = newchicken.size();
			for (int j = 0; j < hs; j++) {
				mindist = Integer.MAX_VALUE;
				for (int k = 0; k < newcs; k++) {
					int curdist = Math.abs(house.get(j).x - newchicken.get(k).x)
							+ Math.abs(house.get(j).y - newchicken.get(k).y);
					if (curdist == 1) {
						mindist = 1;
						break;
					}
					if (curdist < mindist)
						mindist = curdist;
				}
				totaldist += mindist;
			}
			if (min > totaldist)
				min = totaldist;
			return;
		}
		ArrayList<pair> nextchicken = new ArrayList<pair>(newchicken);
		DFS(nextchicken, qty, cur + 1);
		nextchicken.add(chicken.get(cur));
		DFS(nextchicken, qty + 1, cur + 1);
	}

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken()); // size of city
		m = Integer.parseInt(st.nextToken()); // maximum of chicken place qty
		house = new ArrayList<pair>();
		chicken = new ArrayList<pair>();
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				int block = Integer.parseInt(st.nextToken());
				if (block == 1)
					house.add(new pair(j, i));
				else if (block == 2)
					chicken.add(new pair(j, i));
			}
		}
		// compare the cost of each chicken place and delete most expensive
		cs = chicken.size();
		hs = house.size();
		min = Integer.MAX_VALUE;
		DFS(new ArrayList<pair>(), 0, 0); // brute-force search by DFS
		System.out.print(min);
	}
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
	public static int findgcd(int a, int b) { // find the greatest common divisor
		while (b != 0) {
			int temp = a%b;
			a = b;
			b = temp;
		}
		return a;
	}
	public static void main(String[] args) throws Exception {
    // input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		ArrayList<Integer> tree = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			tree.add(Integer.parseInt(br.readLine()));
		}
    // calculate nearest tree's gap for each tree
		ArrayList<Integer> gap = new ArrayList<Integer>();
		for (int i = 0; i < tree.size() - 1; i++) {
			gap.add(tree.get(i+1) - tree.get(i));
		}
    // find greatest common divisor
		int gcd = gap.get(0);
		for (int i = 1; i < gap.size(); i++) {
			gcd = findgcd(gcd, gap.get(i));
		}
		int min = 0;
		for (int i = 0; i < gap.size(); i++) {
			min += gap.get(i) / gcd - 1;
		}
    // output
		System.out.print(min);
	}
}

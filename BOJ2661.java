import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	static int n;
	static ArrayList<Integer> result = null; // result for print

	public static boolean promising(ArrayList<Integer> seq, int len) { // len mean seq's length
		for (int i = 1; i <= len / 2; i++) { // until i == len/2
			for (int j = 0; j <= len - 2 * i; j++) { // until j == len - 2i
				for (int k = 0; k < i; k++) { // until k == i - 1
					if (seq.get(j + k) != seq.get(j + i + k))
						break;
					if (k == i - 1)
						return false;
				} // for k
			} // for j
		} // for i
		return true;
	}

	public static boolean sequence(ArrayList<Integer> seq, int len) {
		if (!promising(seq, len))
			return false;
		if (len == n) {
			if (result == null)
				result = seq;
			else {
				for (int i = 0; i < len; i++) {
					if (result.get(i) > seq.get(i)) {
						result = seq;
						break;
					} else if (result.get(i) < seq.get(i))
						break;
				}
			}
			return true;
		}
		for (int i = 1; i <= 3; i++) {
			ArrayList<Integer> newseq = new ArrayList<Integer>(seq);
			newseq.add(i);
			if(sequence(newseq, len + 1)) // if found promising sequence, return true
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		// start the backtracking to find minimum sequence
		sequence(new ArrayList<Integer>(), 0);
		// output
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < result.size(); i++)
			bw.append((char) (result.get(i) + '0'));
		bw.flush();
	}
}

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String[] args) throws Exception {
		// input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		final int n = Integer.parseInt(br.readLine());
		// find prime numbers by using the sieve of eratosthenes
		boolean[] prime = new boolean[n + 1];
		Arrays.fill(prime, true);
		for (int i = 2; i * i <= n; i++) {
			if (prime[i]) {
				for (int j = i * i; j <= n; j += i) {
					prime[j] = false;
				}
			}
		}
		// move all prime numbers boolean -> int array
		int[] primeNum = new int[300000];
		int k = 0;
		for (int i = 2; i <= n; i++)
			if (prime[i])
				primeNum[k++] = i;
		// count every continuos sum
		int count = 0;
		int sum;
		for (int i = 0; i < k; i++) {
			sum = 0;
			for (int j = i; j < k; j++) {
				if (sum >= n)
					break;
				sum += primeNum[j];
			}
			if (sum == n)
				count++;
		}
		// output
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.append(Integer.toString(count));
		bw.flush();
	}
}

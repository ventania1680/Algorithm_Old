import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

class loc {
	int x, y;
	public loc(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

public class Main {
	
	static ArrayList<loc> blank;

	public static boolean promising(int[][] board, int x, int y) {
		for (int i = 0; i < 9; i++) {
			if (i != x && board[y][i] == board[y][x]) // horizontal check
				return false;
			if (i != y && board[i][x] == board[y][x]) // vertical check
				return false;
		}
		int posx = (x / 3) * 3, posy = (y / 3) * 3;
		for (int i = posy; i < posy+3; i++) {
			for (int j = posx; j < posx+3; j++) {
				if (posx == x && posy == y)
					break;
				if (board[posy][posx] == board[y][x])
					return false;
			}
		}
		return true;
	}

	public static boolean backtracking(int[][] board, int x, int y, int next) {
		if (!promising(board, x, y))
			return false;
		if (next == blank.size())
			return true;
		for (int i = 1; i <= 9; i++) {
			board[blank.get(next).y][blank.get(next).x] = i;
			if (backtracking(board, blank.get(next).x, blank.get(next).y, next+1))
				return true;
		}
		return false;
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int[][] board = new int[9][9];
		blank = new ArrayList<loc>();
		for (int i = 0; i < 9; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				board[i][j] = Integer.parseInt(st.nextToken());
				if (board[i][j] == 0)
					blank.add(new loc(j, i));
			}
		}
		for (int i = 1; i <= 9; i++) {
			board[blank.get(0).y][blank.get(0).x] = i;
			if (backtracking(board, blank.get(0).x, blank.get(0).y, 1))
				break;
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 0; i < 9; i++) {
			for (int j : board[i]) {
				bw.append(j + " ");
			}
			bw.append("\n");
		}
		bw.flush();
	}
}

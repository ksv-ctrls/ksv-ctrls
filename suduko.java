import java.util.*;

class Sudoku {
    private static final int SIZE = 9;
    private static final int SUBGRID_SIZE = 3;
    private int[][] board;
    private boolean[][] fixed;

    public Sudoku(int[][] board) {
        this.board = board;
        this.fixed = new boolean[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] != 0) {
                    fixed[i][j] = true;
                }
            }
        }
    }
    public boolean isValid(int row, int col, int num) {
        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        int startRow = row / SUBGRID_SIZE * SUBGRID_SIZE;
        int startCol = col / SUBGRID_SIZE * SUBGRID_SIZE;
        for (int i = startRow; i < startRow + SUBGRID_SIZE; i++) {
            for (int j = startCol; j < startCol + SUBGRID_SIZE; j++) {
                if (board[i][j] == num) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean solve() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == 0) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(row, col, num)) {
                            board[row][col] = num;
                            if (solve()) {
                                return true;
                            }
                            board[row][col] = 0;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public void printBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public void play() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Enter row (1-9), column (1-9), and number (1-9) to insert (or '0 0 0' to quit): ");
            int row = scanner.nextInt() - 1;
            int col = scanner.nextInt() - 1;
            int num = scanner.nextInt();
            if (row == -1 && col == -1 && num == 0) {
                break;
            }
            if (row >= 0 && row < SIZE && col >= 0 && col < SIZE && num >= 1 && num <= 9 && !fixed[row][col] && isValid(row, col, num)) {
                board[row][col] = num;
                printBoard();
            } else {
                System.out.println("Invalid move.");
            }
        }
        scanner.close();
    }
}
public class Suduko {
	public static void main(String[] args) {
		int[][] puzzle = {
	            {5, 3, 0, 0, 7, 0, 0, 0, 0},
	            {6, 0, 0, 1, 9, 5, 0, 0, 0},
	            {0, 9, 8, 0, 0, 0, 0, 6, 0},
	            {8, 0, 0, 0, 6, 0, 0, 0, 3},
	            {4, 0, 0, 8, 0, 3, 0, 0, 1},
	            {7, 0, 0, 0, 2, 0, 0, 0, 6},
	            {0, 6, 0, 0, 0, 0, 2, 8, 0},
	            {0, 0, 0, 4, 1, 9, 0, 0, 5},
	            {0, 0, 0, 0, 8, 0, 0, 7, 9}
	        };
	        Sudoku sudoku = new Sudoku(puzzle);
	        System.out.println("Sudoku puzzle:");
	        sudoku.printBoard();

	        sudoku.play();

	        System.out.println("Solved Sudoku puzzle:");
	        if (sudoku.solve()) {
	            sudoku.printBoard();
	        } else 
	        {
	            System.out.println("No solution exists.");
	        }
	}

}

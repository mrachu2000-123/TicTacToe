import java.util.Scanner;

class TicTacToe {

	static char[][] board;

	public TicTacToe() {

		board = new char[3][3];
		initBoard();
	}

	void initBoard() {
		for(int i =0;i<board.length;i++) {
			for (int j = 0; j < board[i].length; j++) {
				board[i][j]=' ';
			}
		}
	}

	static void dispBoard() {
		System.out.println("-------------");
		for(int i =0;i<board.length;i++) {
			System.out.print("| ");
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j]+" | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}

	static void placeMark(int row, int col, char mark) {
		if(row>=0 && row<=2 && col>=0 && col<=2) {
			board[row][col] = mark;
		}else {
			System.out.println("Invalid Position");
		}
	}

	static boolean checkColWin() {

		for (int j = 0; j <= 2; j++) {
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkRowWin() {

		for (int i = 0; i <= 2; i++) {
			if(board[i][0]!=' ' && board[i][0]==board[i][1] && board[i][1]==board[i][2]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkDiagWin() {

			if(board[0][0]!=' ' && board[0][0]==board[1][1] && board[1][1]==board[2][2] ||
					board[0][2]==board[1][1] && board[1][1]==board[2][0]) {
				return true;
		}
		return false;
	}
	
	static boolean checkDraw() {
		for (int i = 0; i <= 2; i++) {
			for (int j = 0; j <= 2; j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}


class HumanPlayer{

	String name;
	char mark;

	HumanPlayer(String name, char mark){
		this.name = name;
		this.mark = mark;
	}

	void makemove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			System.out.println("Enter the row and col");
			row = sc.nextInt();
			col = sc.nextInt();
		}while(!isvalidMove(row,col));
		TicTacToe.placeMark(row, col, mark);
		TicTacToe.dispBoard();

	}

	boolean isvalidMove(int row, int col) {

		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTacToe.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}
}


public class LaunchGame {

	public static void main(String[] args) {
		TicTacToe t = new TicTacToe();
		HumanPlayer P1 = new HumanPlayer("Bob",'X');
		HumanPlayer P2 = new HumanPlayer("Alex",'O');
		HumanPlayer Cp;
		Cp = P1;
		while(true){
			System.out.println(Cp.name+" turn");
			Cp.makemove();
			if(TicTacToe.checkColWin() || TicTacToe.checkRowWin()|| TicTacToe.checkDiagWin()) {
				System.out.println(Cp.name+" has Won");
				break;
			}else if(TicTacToe.checkDraw()){
				System.out.println("Game is draw");
				break;
			}
			else {
				if(Cp==P1) {
					Cp=P2;
				}else {
					Cp=P1;
				}
			}
		}
	}
}

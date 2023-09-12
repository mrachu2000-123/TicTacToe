import java.util.Random;
import java.util.Scanner;

class TicTac {

	static char[][] board;

	public TicTac() {

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

		for (int j = 0; j < board.length; j++) {
			if(board[0][j]!=' ' && board[0][j]==board[1][j] && board[1][j]==board[2][j]) {
				return true;
			}
		}
		return false;
	}

	static boolean checkRowWin() {

		for (int i = 0; i < board.length; i++) {
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
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board.length; j++) {
				if(board[i][j]==' ') {
					return false;
				}
			}
		}
		return true;
	}
}

abstract class player{
	String name;
	char mark;
	
	abstract void makemove();
	boolean isvalidMove(int row, int col) {

		if(row>=0 && row<=2 && col>=0 && col<=2) {
			if(TicTac.board[row][col]==' ') {
				return true;
			}
		}
		return false;
	}
	
	
}

class HumanPlayer1 extends player{

	HumanPlayer1(String name, char mark){
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
		TicTac.placeMark(row, col, mark);
	}

	
	
}

class AIPlayer extends player{

	AIPlayer(String name, char mark){
		this.name = name;
		this.mark = mark;
	}

	void makemove() {
		Scanner sc = new Scanner(System.in);
		int row;
		int col;
		do {
			Random r = new Random();
			row = r.nextInt(3);
			col = r.nextInt(3);
		}while(!isvalidMove(row,col));
		TicTac.placeMark(row, col, mark);
	}

}

public class LaunchGameAI {

	public static void main(String[] args) {
		TicTac t = new TicTac();
		HumanPlayer1 P1 = new HumanPlayer1("Bob",'X');
		AIPlayer P2 = new AIPlayer("GAI",'O');
		player Cp;
		Cp = P1;
		while(true){
			System.out.println(Cp.name+" turn");
			Cp.makemove();
			TicTac.dispBoard();
			if(TicTac.checkColWin() || TicTac.checkRowWin()|| TicTac.checkDiagWin()) {
				System.out.println(Cp.name+" has Won");
				break;
			}else if(TicTac.checkDraw()){
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



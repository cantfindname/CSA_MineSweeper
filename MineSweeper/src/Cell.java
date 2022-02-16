import java.awt.Color;

import doodlepad.Rectangle;
import doodlepad.Text;

public class Cell extends Rectangle {
	private int row,col;
	private int adjMine=0;
	private boolean isMine=false;
	private Minesweeper board;
	private static int safe;
	
	public Cell(int y, int x, int width, Minesweeper b) {
		super(x*width,y*width,width,width);
		setFillColor(Color.CYAN);
		setStrokeColor(Color.BLACK);
		row=y;
		col=x;
		board=b;		
	}
	
	public void onMouseClicked(double x, double y, int b) {
		
		if (!isMine()) {
			if (getFillColor().equals(Color.CYAN)) {
				safe++;
			}
			setFillColor(Color.WHITE);
			
			if (safe==board.getNumSafeCells()) {
				board.gameOver(false);
			}
		
			else if (adjMine==0) {
				board.sweep(row, col);
			}
			else {
				Text t= new Text (""+adjMine, this.getX(), this.getY(), 20);
			}
		}
		else {
			board.gameOver(true);
		}
	}
	
	
		/*
		if (isMine) {
			((Minesweeper) board).gameOver(true);
		}
		else {
			((Minesweeper) board).setAdjacentCounts(row,col);
			if(getFillColor()==Color.BLUE) {
				safe++;
				setFillColor(Color.WHITE);
			}
			if (((Minesweeper) board).getNumSafeCells() == safe) {
				((Minesweeper) board).gameOver(false);
			}
			else if(adjMine==0) {
				((Minesweeper) board).sweep(row,col);

			}
			else {
				String s = "  "+adjMine;
				Text t = new Text(s,col*width, row*width+10, 20);

			}
		}
		*/
	
	
	public void setMine() {
		isMine=true;
	}
	
	public void incrementCount() {
		adjMine++;
	}
	
	public boolean isMine() {
		return isMine;
	}
	
}

import java.awt.Color;

import doodlepad.Pad;
import doodlepad.Sound;

public class Minesweeper extends Pad{

	private Cell[][] a;
	private final int MINES;
	private static final int PAD_WIDTH = 576;
	
	public Minesweeper(int x) {
		super (PAD_WIDTH, PAD_WIDTH);
		if (x==1) {
			a = new Cell[9][9];
			MINES=10;
		}
		else {
			a = new Cell[16][16];
			MINES=40;
		}
		setUp();
	}
	
	public void sweep(int row, int col) {
		for (int r=row-1; r<=row+1; r++) {
			for (int c= col-1; c<=col+1; c++) {
				if (inBounds(r,c) && a[r][c].getFillColor().equals(Color.CYAN)) {
					a[r][c].onMouseClicked(0,0,0);
				}
			}
		}
	}
	
	public int getNumSafeCells() {return a.length*a.length - MINES;}
	
	private void fillArray() {
		int cellWidth = PAD_WIDTH/a.length;
		for (int r=0; r<a.length; r++) {
			for (int c=0; c<a[r].length; c++) {
				a[r][c]= new Cell(r,c,cellWidth, this);
			}
		}
	}  
	
	private void placeMines() {
		for (int i=0; i<MINES; i++) {
			int r = (int)(Math.random()*a.length);
			int c = (int)(Math.random()*a.length);
			while (a[r][c].isMine()) {
				r = (int)(Math.random()*a.length);
				c = (int)(Math.random()*a.length);
			}
			a[r][c].setMine();
			a[r][c].setFillColor(Color.RED);
			setAdjacentCounts(r,c);
		}
	}
	
	private void setUp() {
		fillArray();
		placeMines();
	}
	
	private boolean inBounds(int r, int c) {
		return !(r<0 || c<0 || r>=a.length || c>=a.length);
	}
	
	public void setAdjacentCounts(int row, int col) {
		for (int r=row-1; r<=row+1; r++) {
			for (int c=col-1; c<=col+1; c++) {
				if (inBounds(row, col) && !a[row][col].isMine()) {
					a[r][c].incrementCount();
				}
			}
		}
	}
	
	public void gameOver(boolean lost) {
		setEnabled(false);
		if (lost) {
			Sound s = new Sound("gameOverSound.wav");
			s.play();
			
			
		}
	}
		/*
			if (lost) {
				Sound l = new Sound("gameOverSound.wav");
				l.play();
			}
			else if (!lost) {
				Sound w = new Sound("victorySound.mp3");
				w.play();
			}
	}
	*/
}

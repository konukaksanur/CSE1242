import java.util.ArrayList;

import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class CheckLevel {
	private int currentRow;
	private int currentCol;
	private boolean levelCompleted;
	private String direction;
	private ArrayList<Integer> pathRow = new ArrayList(); //deðiþti
	private ArrayList<Integer> pathCol = new ArrayList(); //deðiþti
	private Tile[][] tiles;
	private Stage primaryStage;
	private GridPane pane;
	
	public CheckLevel(Tile[][] tiles, Stage primaryStage, GridPane pane) {
		this.tiles = tiles;
		this.primaryStage = primaryStage;
		this.pane = pane;
	}
	
	public boolean control() throws Exception {
		pathRow.clear();
		pathCol.clear();
		direction= "NoDirection";
		while (true) {
			int preCol = currentCol;
			int preRow = currentRow;
			if(tiles[currentRow][currentCol].getId().equals("StarterH") && currentCol-1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
				pathRow.add(currentRow);
				pathCol.add(currentCol);
				currentCol--;
			}
			else if(tiles[currentRow][currentCol].getId().equals("StarterV") && currentRow+1 < 4 && tiles[currentRow+1][currentCol].isUp()) {
				pathRow.add(currentRow);
				pathCol.add(currentCol);
				currentRow ++; 
			}
			else if(tiles[currentRow][currentCol].getId().equals("PipeStaticH") || tiles[currentRow][currentCol].getId().equals("PipeH")) {
				if(direction.equals("left") && currentCol + 1 < 4 && tiles[currentRow][currentCol+1].isLeft()) {//soldan
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol ++;
				} 
				else if(direction.equals("right") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol--; 
				} 
				else break;
			}
			else if (tiles[currentRow][currentCol].getId().equals("PipeStaticV") || tiles[currentRow][currentCol].getId().equals("PipeV")) {
				if(direction.equals("up") && currentRow+1 < 4 && tiles[currentRow+1][currentCol].isUp()) { //YUKARDAN
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow ++; 
				} 
				else if(direction.equals("down") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) { //AÞAÐIDAN
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow--; 
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equals("Pipe00") || tiles[currentRow][currentCol].getId().equals("PipeStatic00")) {
				if(direction.equals("left") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) { //YUKARDAN  //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow--;	
				} 
				else if(direction.equals("up") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) { //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol--;
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equals("Pipe01") || tiles[currentRow][currentCol].getId().equals("PipeStatic01")) {
				if(direction.equals("right") && currentRow - 1 >= 0 && tiles[currentRow-1][currentCol].isDown()) { //üstten //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow--;
				} 
				else if(direction.equals("up") && currentCol + 1 < 4 &&  tiles[currentRow][currentCol+1].isLeft()) { //SAÐDAN  //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol++; 
				} 
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equals("Pipe10") || tiles[currentRow][currentCol].getId().equals("PipeStatic10")) {
				if(direction.equals("down") && currentCol - 1 >= 0 && tiles[currentRow][currentCol-1].isRight()) {//soldan //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol--;
					System.out.println("111");
				} 
				else if (direction.equals("left") && currentRow + 1 < 4 && tiles[currentRow+1][currentCol].isUp()) { //alttan  //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow++;
				}
				else break;
			} 
			else if(tiles[currentRow][currentCol].getId().equals("Pipe11") || tiles[currentRow][currentCol].getId().equals("PipeStatic11")) {
				if(direction.equals("down") && currentCol + 1 < 4 && tiles[currentRow][currentCol+1].isLeft()) {//SAÐ  //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentCol++;
				} 
				else if(direction.equals("right") && currentRow + 1 < 4 && tiles[currentRow+1][currentCol].isUp()) {//ALTTAN  //deðiþti
					pathRow.add(currentRow);
					pathCol.add(currentCol);
					currentRow++;
				} 
				else break;
				
			} 
			else if(tiles[currentRow][currentCol].getId().equals("EndH")) {
				pathRow.add(currentRow);
				pathCol.add(currentCol);
				PipePuzzle.move = 0;
				PipePuzzle.levelNumber++; //deðiþti
				AnimateBall aBall = new AnimateBall(pathRow, pathCol);
				System.out.println("125");
				aBall.animateBall(pane, primaryStage);
				break; //deðiþti
				
			} 
			else if(tiles[currentRow][currentCol].getId().equals("EndV")) {
				pathRow.add(currentRow);
				pathCol.add(currentCol);
				PipePuzzle.move = 0;
				PipePuzzle.levelNumber++; //deðiþti
				AnimateBall aBall = new AnimateBall(pathRow, pathCol);
				System.out.println("134");
				aBall.animateBall(pane, primaryStage);
				break; //deðiþti
			} 
			else break;
		
			if(currentCol <preCol) {
				direction= "right";
			}
			else if (preCol < currentCol){
				direction= "left";
			}
			else if(preRow < currentRow) {
				direction = "up";
			}
			else if(currentRow < preRow){
				direction = "down";
			}
			else {
				direction= "NoDirection";
			}
		}
		return false;
	}
	
	public ArrayList<Integer> getPathRow() {
		return pathRow;
	}

	public void setPathRow(ArrayList<Integer> pathRow) {
		this.pathRow = pathRow;
	}

	public ArrayList<Integer> getPathCol() {
		return pathCol;
	}

	public void setPathCol(ArrayList<Integer> pathCol) {
		this.pathCol = pathCol;
	}

	public boolean isLevelCompleted() {
		return levelCompleted;
	}

	public void setLevelCompleted(boolean levelCompleted) {
		this.levelCompleted = levelCompleted;
	}	
}
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class CheckSwap {
	public int src1Row;
	public int src1Col;
	public int src2Row;
	public int src2Col;

	private Text nmbOfMoves;
	private GridPane pane;
	private Tile[][] tiles;
	private Stage primaryStage;
	private boolean src1declared;
	private boolean src2declared;
	
	public CheckSwap() {
		
	}
 	
	public CheckSwap(GridPane pane, Tile[][] tiles, Stage primaryStage, Text nmbOfMoves) {
		this.pane = pane;
		this.tiles = tiles;
		this.primaryStage = primaryStage;
		this.nmbOfMoves = nmbOfMoves;
		
	}
	public void getRowCol () throws Exception {
		pane.setOnMousePressed(event -> {
			this.src2Row = ((int) event.getY()) / 104;
			this.src2Col = ((int) event.getX()) / 104;
			this.src2declared = true;
			try {
				checkDeclared();
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}); 
		
		pane.setOnMouseReleased(event -> {
			this.src1Row = ((int) event.getY()) / 104;
			this.src1Col = ((int) event.getX()) / 104;
			this.src1declared = true;
			try {
				checkDeclared();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}
	public void checkDeclared () throws Exception {
		if (src1declared && src2declared && src1Row < 4 && src1Col < 4 && src2Col < 4 && src2Col < 4) {
			play();
			this.src1declared = false;
			this.src2declared = false;
		} 
	}
	public void play() throws Exception {	
		CheckTiles cTiles = new CheckTiles(tiles,src1Row,src1Col,src2Row,src2Col);
		if (src1Row == src2Row && Math.abs(src1Col - src2Col) == 1 && cTiles.checkTiles()) {
			printMoveNumber(pane);
			SwapTiles sTiles = new SwapTiles(pane, tiles, primaryStage, src1Row, src1Col, src2Row, src2Col);
			sTiles.swap();
		}
		if (src1Col == src2Col && Math.abs(src1Row - src2Row) == 1 && cTiles.checkTiles()) {
			printMoveNumber(pane);///deðiþti
			SwapTiles sTiles = new SwapTiles(pane, tiles, primaryStage, src1Row, src1Col, src2Row, src2Col);
			sTiles.swap();
			}
	}
	
	public void printMoveNumber(GridPane pane) {
		PipePuzzle.move++;
		pane.getChildren().remove(nmbOfMoves);
		this.nmbOfMoves = new Text(0, 0, PipePuzzle.move + "");
		nmbOfMoves.setFont(Font.font("Corrier", FontWeight.BOLD, 13));
		nmbOfMoves.setFill(Color.WHITE);
		pane.add(nmbOfMoves, 3, 4);
	}
	public int getSrc1Row() {
		return src1Row;
	}
	public int getSrc1Col() {
		return src1Col;
	}
	public int getSrc2Row() {
		return src2Row;
	}
	public int getSrc2Col() {
		return src2Col;
	}
	public void setSrc1declared(boolean src1declared) {
		this.src1declared = src1declared;
	}
	public void setSrc2declared(boolean src2declared) {
		this.src2declared = src2declared;
	}
	
	
}
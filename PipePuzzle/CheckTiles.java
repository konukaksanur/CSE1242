import javafx.scene.image.Image;

public class CheckTiles {
	private int src1Row;
	private int src1Col;
	private int src2Row;
	private int src2Col;
	private Tile[][] tiles = new Tile[4][4];
	
	public CheckTiles(Tile[][] tiles, int src1Row, int src1Col, int src2Row, int src2Col) {
		this.tiles = tiles;
		this.src1Row = src1Row;
		this.src1Col = src1Col;
		this.src2Row = src2Row;
		this.src2Col = src2Col;
	}
	
	public boolean checkTiles() {
		return tiles[src1Row][src1Col].getId().equals("EmptyFree") && tiles[src2Row][src2Col].isMoveable();
	}
}
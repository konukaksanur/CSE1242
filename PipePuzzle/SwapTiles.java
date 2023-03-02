import javafx.animation.PathTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.VLineTo;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SwapTiles {
	Tile[][] tiles;
	GridPane pane;
	Stage primaryStage;
	int src1Row;
	int src1Col;
	int src2Row;
	int src2Col;
	
	public SwapTiles(GridPane pane, Tile[][] tiles, Stage primaryStage, int src1Row, int src1Col, int src2Row, int src2Col) {
		this.tiles = tiles;
		this.pane = pane;
		this.primaryStage = primaryStage;
		this.src1Row = src1Row;
		this.src1Col = src1Col;
		this.src2Row = src2Row;
		this.src2Col = src2Col;
	}
	
	public GridPane swap() throws Exception {
		PathTransition pt = new PathTransition();
		Path pt1 = new Path();
		MoveTo start = new MoveTo( src2Col * 104 + 52, src2Row * 104 + 52);
		pt1.getElements().add(start);
		if (src1Row == src2Row) {
			HLineTo hLine = new HLineTo(src1Col * 104 + 52);
			pt1.getElements().add(hLine);
		}
		else if (src1Col == src2Col) {
			VLineTo vLine = new VLineTo(src1Row * 104 + 52);
			pt1.getElements().add(vLine);
		}
		
		pt.setPath(pt1);
		pt.setDuration(Duration.millis(150));
		ImageView imageV = new ImageView(tiles[src2Row][src2Col].getImage());
		pane.getChildren().remove(imageV);
		pane.add(imageV, 0, 0);
		Image tempFree = new Image("EmptyFree.PNG");
		pane.add(new ImageView(tempFree), src2Col, src2Row);
		pane.add(new ImageView(tempFree), src1Col, src1Row);
		imageV.toFront();
		pt.setNode(imageV);
		pt.play();
		pt.setOnFinished(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				try {
					pane.getChildren().remove(imageV);
					Image image2 = tiles[src1Row][src1Col].getImage();
					Image image1 = tiles[src2Row][src2Col].getImage();
					Tile temp = tiles[src2Row][src2Col];///deðiþti
					tiles[src2Row][src2Col] = tiles[src1Row][src1Col];///deðiþti
					tiles[src1Row][src1Col]= temp;///deðiþti
					pane.add(new ImageView(image1), src1Col, src1Row);
					pane.add(new ImageView(image2), src2Col, src2Row);
					CheckLevel cLevel = new CheckLevel(tiles, primaryStage, pane);
					cLevel.control();///deðiþti
					} catch (Exception e) {	
					e.printStackTrace();
					}	
				}});
		CheckSwap cSwap = new CheckSwap();
		cSwap.setSrc1declared(false);
		cSwap.setSrc2declared(false);
		return pane;
	}
}

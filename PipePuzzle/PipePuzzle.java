import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import javafx.animation.PathTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.HLineTo;
import javafx.scene.shape.Line;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.shape.PathElement;
import javafx.scene.shape.VLineTo;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PipePuzzle extends Application{
	public static int startRow = 0;
	public static int startCol = 0;
	public static int levelNumber =1; //deðiþti
	private boolean levelCompleted = true;
	private boolean src1declared; 
	private boolean src2declared;
	public static int move = 0;
	private Text nmbOfMoves;
	private Image Pipe00 = new Image("Pipe00.PNG"); //deðiþti
	private Image Pipe01 = new Image("Pipe01.PNG"); //deðiþti
	private Image Pipe10 = new Image("Pipe10.PNG"); //deðiþti
	private Image Pipe11 = new Image("Pipe11.PNG"); //deðiþti
	private Image EmptyNone = new Image("EmptyNone.PNG"); //deðiþti
	private Image EndH = new Image("EndH.PNG");//deðiþti
	private Image EndV = new Image("EndV.PNG");//deðiþti
	private Image EmptyFree = new Image("EmptyFree.PNG"); //deðiþti
	private Image PipeH = new Image("PipeH.PNG"); //deðiþti
	private Image PipeV = new Image("PipeV.PNG"); //deðiþti
	private Image StarterH = new Image("StarterH.PNG"); //DEÐÝÞTÝ
	private Image StarterV = new Image("StarterV.PNG"); //deðiþti
	private Image PipeStaticH = new Image("PipeStaticH.PNG"); //deðiþti
	private Image PipeStaticV = new Image("PipeStaticV.PNG"); //deðiþti
	private Image PipeStatic00 = new Image("PipeStatic00.PNG");//deðiþti
	private Image PipeStatic01 = new Image("PipeStatic01.PNG");//deðiþti
	private Image PipeStatic10 = new Image("PipeStatic10.PNG");//deðiþti
	private Image PipeStatic11 = new Image("PipeStatic11.PNG");//deðiþti
	Circle ball; //deðiþti
	ArrayList<String> pathRow = new ArrayList<String>();
	ArrayList<String> pathCol = new ArrayList<String>();
	
	
	Tile[][] tiles = new Tile[4][4];
	
	public PipePuzzle() {
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start (Stage primaryStage) throws Exception {	
		GridPane pane = new GridPane();
		
		pane.setAlignment(Pos.CENTER);
		ball = createBall();	
		nmbOfMoves = new Text(move + "");
		nmbOfMoves.setFont(Font.font("Corrier", FontWeight.BOLD, 13));
		nmbOfMoves.setFill(Color.WHITE);
		Text nmbOfMovesTxt = new Text("Number of Moves:");
		nmbOfMovesTxt.setFont(Font.font("Corrier", FontWeight.BOLD, 11));
		nmbOfMovesTxt.setFill(Color.WHITE);
		///
		if (levelNumber > 7) {
			primaryStage.close();
			System.exit(1);
		}
		while (levelNumber <= 7 && levelCompleted) {
			this.levelCompleted = false;
			File inputFile = new File("level" + levelNumber + ".txt"); 
			Scanner input = new Scanner(inputFile); 
			while (input.hasNext()) {
			String str = input.nextLine();
			if (!str.equals("")) {
				String[] parts = str.split(",");	
				pane = addPane(parts, pane, tiles); }	
			}
		}
		pane.setStyle("-fx-background-color: BLACK;");
		pane.add(nmbOfMoves, 3, 4);
		pane.add(nmbOfMovesTxt, 2, 4);
		CheckSwap checkSwap = new CheckSwap(pane, tiles, primaryStage, nmbOfMoves);
		checkSwap.getRowCol();
		Scene scene = new Scene(pane,416,435);
		primaryStage.setScene(scene);
		primaryStage.setTitle("150120049_150120075_Project");
		primaryStage.show(); 
	}
	public GridPane addPane(String str[], GridPane pane, Tile[][] tiles) {
		int number = Integer.parseInt(str[0]);
		int row = (number - 1) / 4;
		int column;
		if (number % 4 == 0)
			column = 3;
		else
			column = number % 4 - 1;
		if (str[1].equalsIgnoreCase("Starter")) {
			if (str[2].equalsIgnoreCase("Vertical")) {
				GridPane.setHalignment(ball, HPos.CENTER);
				GridPane.setValignment(ball, VPos.CENTER);
				PipePuzzle.startRow = row; 
				PipePuzzle.startCol = column;
				pane.add(new ImageView(StarterV), column, row); //deðiþti
				pane.add(ball, column, row);
				tiles[row][column] = new Tile("StarterV", false,true,false,false,false);
			} 
			else if(str[2].equalsIgnoreCase("Horizontal")) {
				GridPane.setHalignment(ball, HPos.CENTER);
				GridPane.setValignment(ball, VPos.CENTER);
				PipePuzzle.startRow = row;
				PipePuzzle.startCol = column;
				pane.add(new ImageView(StarterH), column, row); //deðiþti
				pane.add(ball, column, row);
				tiles[row][column] = new Tile("StarterH", false,false,false,true,false);
			}
		} 
		else if (str[1].equalsIgnoreCase("Empty")) {
			if (str[2].equalsIgnoreCase("none")) {
				pane.add(new ImageView(EmptyNone), column, row); //deðiþti
				tiles[row][column] = new Tile("EmptyNone", false,false,false,false,true);	
			} 
			else if (str[2].equalsIgnoreCase("Free")) {
				pane.add(new ImageView(EmptyFree), column, row); //deðiþti
				tiles[row][column] = new Tile("EmptyFree", false,false,false,false,false);
			}
		} 
		else if (str[1].equalsIgnoreCase("Pipe")) {
			if (str[2].equalsIgnoreCase("Vertical")) {
				pane.add(new ImageView(PipeV), column, row); //deðiþti
				tiles[row][column] = new Tile("PipeV", true,true,false,false,true);
			} 
			else if(str[2].equalsIgnoreCase("Horizontal")) {
				pane.add(new ImageView(PipeH), column, row); //deðiþti
				tiles[row][column] = new Tile("PipeH", false,false,true,true,true);	
			} 
			else if(str[2].equalsIgnoreCase("00")) {
				pane.add(new ImageView(Pipe00), column, row); //deðiþti
				tiles[row][column] = new Tile("Pipe00", true,false,false,true,true);	
			} 
			else if (str[2].equalsIgnoreCase("01")) {
				pane.add(new ImageView(Pipe01), column, row); //deðiþti
				tiles[row][column] = new Tile("Pipe01", true,false,true,false,true);
			} 
			else if (str[2].equalsIgnoreCase("10")) {
				pane.add(new ImageView(Pipe10), column, row); //deðiþti
				tiles[row][column] = new Tile("Pipe10", false,true,false,true,true);
			} 
			else if (str[2].equalsIgnoreCase("11")) {
				pane.add(new ImageView(Pipe11), column, row); //deðiþti
				tiles[row][column] = new Tile("Pipe11", false,true,true,false,true);
			}
		} 
		else if (str[1].equalsIgnoreCase("PipeStatic")) {
			if(str[2].equals("Vertical")) {
				pane.add(new ImageView(PipeStaticV), column, row);	 //deðiþti
				tiles[row][column] = new Tile("PipeStaticV", true,true,false,false,false);
			} 
			else if(str[2].equalsIgnoreCase("Horizontal")){
				pane.add(new ImageView(PipeStaticH), column, row);	//DEÐÝÞTÝ
				tiles[row][column] = new Tile("PipeStaticH", false,false,true,true,false);
			} 
			else if(str[2].equalsIgnoreCase("00")) {
				pane.add(new ImageView(PipeStatic00), column, row);	//deðiþti
				tiles[row][column] = new Tile("PipeStatic00", true,false,false,true,false);
			} 
			else if(str[2].equalsIgnoreCase("01")) {
				pane.add(new ImageView(PipeStatic01), column, row);	//deðiþti
				tiles[row][column] = new Tile("PipeStatic01", true,false,true,false,false);
			} 
			else if(str[2].equalsIgnoreCase("10")) {
				pane.add(new ImageView(PipeStatic10), column, row);	//deðiþti
				tiles[row][column] = new Tile("PipeStatic10", false,true,false,true,false);
			} 
			else if(str[2].equalsIgnoreCase("11")) {
				pane.add(new ImageView(PipeStatic11), column, row);	//deðiþti
				tiles[row][column] = new Tile("PipeStatic11", false,true,true,false,false);
			}
		} 
		else if (str[1].equalsIgnoreCase("End")) {
			if(str[2].equalsIgnoreCase("Vertical")) {
				pane.add(new ImageView(EndV), column, row); //deðiþti
				tiles[row][column] = new Tile("EndV", false,true,false,false,false);
			
			} 
			else {
				pane.add(new ImageView(EndH), column, row); //deðiþti
				tiles[row][column] = new Tile("EndH", false,false,false,true,false);
			
			}
		}
		return pane;
	}
	
	public Circle createBall() {
		Circle ball = new Circle(20);
		ball.setStroke(Color.DARKSLATEBLUE);
		ball.setFill(Color.DARKSLATEBLUE);
		GridPane.setHalignment(ball, HPos.CENTER); //deðiþti
		GridPane.setValignment(ball, VPos.CENTER); //deðiþti
		return ball;
	}
	public Circle getCircle() {
		return ball;
	}
}
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;

public class Tile {
	private String id;
	private boolean up;
	private boolean down;
	private boolean right;
	private boolean left;
	private boolean moveable;
	private Image image;
	
	public Tile(String id, boolean up, boolean down,boolean right, boolean left, boolean moveable  ) {
		this.id = id;
		this.up = up;
		this.down = down;
		this.right = right;
		this.left = left;
		this.moveable = moveable;	
		this.image = new Image(id +".PNG");
	}


	public Image getImage() {
		return image;
	}


	public void setImage(Image image) {
		this.image = image;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isMoveable() {
		return moveable;
	}

	public void setMoveable(boolean moveable) {
		this.moveable = moveable;
	}	
}
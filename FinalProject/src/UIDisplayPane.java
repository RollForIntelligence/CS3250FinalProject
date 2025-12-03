
import javafx.animation.AnimationTimer;
import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class UIDisplayPane extends StackPane {
	private double width = 0.0;
	private double height = 0.0;
		
	private Pane movementPane;
	private GridPane inventoryGrid;
	private Inventory inventory;
	
	public UIDisplayPane(Inventory inventory, double width, double height) {
		// : set up a GridPane of tiles for the items
		inventoryGrid = new GridPane();
		this.inventory = inventory;
		
		// Ensures the tiles are squares that fit within the bounds of the screen
		double tileSize = Math.min((width >= 400 ? 80 : width / 5), (height >= 320 ? 80 : height / 4));
		
		for (int i = 0; i < this.inventory.MAX_CAPACITY; i++) {
			ItemTile tile = new ItemTile(tileSize, this.inventory.getItemAt(i), i);
			inventoryGrid.add(tile, i % 5, i / 5);
		}
		
		this.width = tileSize * 5;
		this.height = tileSize * 4;
		
		this.getChildren().add(inventoryGrid);
		
		// Create a pane to display the dynamic positions of the moving inventory items
		movementPane = new Pane();
		this.getChildren().add(movementPane);
				
		
//		this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> { 
//			renderInventory(inventory, UIDisplayPane.this.getWidth(), UIDisplayPane.this.getHeight());
//		});
		
		// Allow the user to appear to move a copy of a tile around with their mouse
		this.setOnMousePressed(event -> {
			ItemTile tileClicked = null;
			for (Node tile : inventoryGrid.getChildren()) {
				if (tile.getBoundsInParent().intersects(event.getX(), event.getY(), 1, 1)) {
					tileClicked = ((ItemTile) tile).copy();
					break;
				}
			}
			
			// Create a copy of the tile in question to place in the movementPane at the event's location
			if (tileClicked != null) {
				movementPane.getChildren().add(tileClicked);
				tileClicked.setLayoutX(event.getX());
				tileClicked.setLayoutY(event.getY());
			}
		});
		
		// setOnMouseDragged: move any tile in the movementPane
		this.setOnMouseDragged(event -> {
			if (movementPane.getChildren().size() > 0) {
				Node movingTile = movementPane.getChildren().get(0);
				movingTile.setLayoutX(event.getX());
				movingTile.setLayoutY(event.getY());
			}
		});
		
		// setOnMouseReleased: place the tile in the movementPane into the corresponding position in the inventory
		this.setOnMouseReleased(event -> {
			if (movementPane.getChildren().size() > 0) {
				for (Node tile : inventoryGrid.getChildren()) {
					if (tile.getBoundsInParent().intersects(event.getX(), event.getY(), 1, 1)) {
						ItemTile movingTile = (ItemTile) movementPane.getChildren().get(0);
						ItemTile tileSelected = (ItemTile) tile;
						inventory.swapItems(movingTile.getIndex(), tileSelected.getIndex());
						renderInventory(inventory, width, height);
						break;
					}
				}
				movementPane.getChildren().remove(0);
			}
		});
	}
	
	public void renderInventory(Inventory inventory, double width, double height) {
		if (inventoryGrid == null) {
			return;
		}
		
		// relocate the logic for creating the inventory panel here
		inventoryGrid.getChildren().clear();
		
		// Ensures the tiles are squares that fit within the bounds of the screen
		double tileSize = Math.min((width >= 400 ? 80 : width / 5), (height >= 320 ? 80 : height / 4));
		
		for (int i = 0; i < inventory.MAX_CAPACITY; i++) {
			ItemTile tile = new ItemTile(tileSize, inventory.getItemAt(i), i);
			inventoryGrid.add(tile, i % 5, i / 5);
		}
		
		this.width = tileSize * 5;
		this.height = tileSize * 4;
		
	}
	
	public double getInventoryWidth() {
		return width;
	}
	
	public void closeInventory() {
		
	}
	
	private class ItemTile extends Canvas { // : make ItemTile a canvas to draw the item's images on them
		private Item item;
		private int index;
		
		public ItemTile(double size, Item item, int index) {
			super(size, size);
			this.item = item;
			this.index = index;
			drawTile();
		}
		
		private void drawTile() {
			GraphicsContext gc = this.getGraphicsContext2D();
			gc.setStroke(Color.DARKGRAY);
			gc.setFill(Color.LIGHTGRAY);
			
			gc.setLineWidth(5);
			
			gc.fillRect(0, 0, this.getWidth(), this.getHeight());
			gc.strokeRect(0, 0, this.getWidth(), this.getHeight());
			
			if (item != null) {
				gc.setImageSmoothing(false);
				gc.drawImage(item.getSprite(), 2.5, 2.5, this.getWidth() - 5, this.getHeight() - 5);
			}
		}
		
		public Item getItem() { return item; }
		public int getIndex() { return index; }
		
		public ItemTile copy() {
			ItemTile copy = new ItemTile(this.getWidth(), this.getItem(), index);
			return copy;
		}
	}
	
	private Canvas gameOverCanvas = null;
	
	public UIDisplayPane(String message, double width, double height) {
		gameOverCanvas = new Canvas();
		inventoryGrid = null;
		formatGameOverCanvas(gameOverCanvas, message, width, height);
		
		this.getChildren().clear();
		this.getChildren().add(gameOverCanvas);
		
		this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			formatGameOverCanvas(gameOverCanvas, message, UIDisplayPane.this.getWidth(), UIDisplayPane.this.getHeight());
		});
	}
	
	public void resizeScreen(double width, double height) {
		if (inventoryGrid != null) {
			renderInventory(inventory, width, height);
		}
		if (gameOverCanvas != null) {
			formatGameOverCanvas(gameOverCanvas, "Game Over", UIDisplayPane.this.getWidth(), UIDisplayPane.this.getHeight());
		}
	}
	
	private void formatGameOverCanvas(Canvas canvas, String message, double width, double height) {
		canvas.setWidth(width);
		canvas.setHeight(height);
		
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLACK);
		gc.fillRect(0, 0, width, height);
		
		gc.setFont(Font.font("Times New Roman", FontWeight.BOLD, 40.0));
		gc.setFill(Color.RED);
		gc.fillText(message, (width - 220) / 2, height / 2);
	}
}

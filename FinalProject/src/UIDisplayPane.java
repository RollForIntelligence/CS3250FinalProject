
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;

public class UIDisplayPane extends StackPane {
	double width = 0.0;
	double height = 0.0;
	
	public UIDisplayPane(Inventory inventory, double width, double height) {
		// : set up a GridPane of tiles for the items
		GridPane inventoryGrid = new GridPane();
		
		// Ensures the tiles are squares that fit within the bounds of the screen
		double tileSize = Math.min((width >= 400 ? 80 : width / 5), (height >= 320 ? 80 : height / 4));
		
		for (int i = 0; i < inventory.MAX_CAPACITY; i++) {
			ItemTile tile = new ItemTile(tileSize, inventory.getItemAt(i));
			inventoryGrid.add(tile, i / 4, i % 4);
		}
		
		this.width = tileSize * 5;
		this.height = tileSize * 4;
		
		this.getChildren().add(inventoryGrid);
		
		// TODO: set up an animationTimer that runs while the UIDisplayPane is open
		// TODO: allow the user to appear to move a copy of a tile around with their mouse
		// TODO: set up a way to stop the timer when the inventory is closed
	}
	
	public double getInventoryWidth() {
		return width;
	}
	
	private class ItemTile extends Canvas { // : make ItemTile a canvas to draw the item's images on them
		private Item item;
		
		public ItemTile(double size, Item item) {
			super(size, size);
			this.item = item;
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
				gc.drawImage(item.getSprite(), 5, 5, this.getWidth() - 5, this.getHeight() - 5);
			}
		}
		
		public void setItem(Item item) { this.item = item; }
		public Item getItem() { return item; }
	}
	
	public UIDisplayPane(String message, double width, double height) {
		Canvas gameOverCanvas = new Canvas();
		formatGameOverCanvas(gameOverCanvas, message, width, height);
		
		this.getChildren().add(gameOverCanvas);
		
		this.layoutBoundsProperty().addListener((observable, oldValue, newValue) -> {
			formatGameOverCanvas(gameOverCanvas, message, UIDisplayPane.this.getWidth(), UIDisplayPane.this.getHeight());
		});
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


public class Inventory {
	private Item[] items;
	private final int MAX_CAPACITY = 20;
	
	public Inventory() {
		this.items = new Item[MAX_CAPACITY];
		for (int i = 0; i < MAX_CAPACITY; i++) {
			items[i] = null;
		}
	}
}

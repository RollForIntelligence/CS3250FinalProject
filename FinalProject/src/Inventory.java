
public class Inventory {
	private Item[] items;
	private final int MAX_CAPACITY = 20;
	
	public Inventory() {
		this.items = new Item[MAX_CAPACITY];
		for (int i = 0; i < MAX_CAPACITY; i++) {
			items[i] = null;
		}
	}
	
	public Item getFirstItem() {
		return items[0];
	}
	
	public void swapItems(int first, int second) {
		if (first >= MAX_CAPACITY || second >= MAX_CAPACITY) {
			return;
		}
		else if (first < 0 || second < 0) {
			return;
		}
		else if (first == second) {
			return;
		}
		else {
			Item temp = items[first];
			items[first] = items[second];
			items[second] = temp;
		}
	}
	
	public Item removeItem(int index) {
		Item temp = items[index];
		items[index] = null;
		return temp;
	}
}

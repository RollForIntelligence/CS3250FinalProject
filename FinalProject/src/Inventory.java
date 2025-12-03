
public class Inventory {
	private Item[] items;
	public final int MAX_CAPACITY = 20;
	
	public Inventory() {
		this.items = new Item[MAX_CAPACITY];
		for (int i = 0; i < MAX_CAPACITY; i++) {
			items[i] = null;
		}
	}
	
	public Item getFirstItem() {
		return items[0];
	}
	
	public Item getItemAt(int index) {
		if (index >= MAX_CAPACITY || index < 0) {
			return null;
		}
		
		return items[index];
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
	
	public boolean addItem(Item newItem) {
		boolean successful = false;
		
		// Cast the new item to the type of Item it is
		Item addedItem;
		if (newItem instanceof Weapon) {
			addedItem = (Weapon) newItem;
		}
		// TODO: add consumable check
		else {
			addedItem = newItem;
		}
		
		// Check the current set of items to see if the new item can add to an existing slot
		for (Item item : items) {
			if (item == null) {
				continue;
			}
			if (addedItem instanceof Weapon && item instanceof Weapon) {
				item = (Weapon) item;
				
				// Check if the Weapons are the same type
				if (item.equals(addedItem)) {
					
					// Check if there is enough room in the stack of Weapons
					if (item.quantity + addedItem.quantity <= Weapon.getMaxStackSize()) {
						item.quantity += addedItem.quantity;
						successful = true;
						break;
					}
					
					// Fill any remaining space in the Weapon stack, then continue with the new item's smaller quantity
					else if (item.quantity < Weapon.getMaxStackSize()) {
						addedItem.quantity -= (Weapon.getMaxStackSize() - item.quantity);
						continue;
					}
				}
			}
			// TODO: add consumable check
		}
		
		// Add the new item to the first empty slot if it hasn't been placed yet
		if (!successful) {
			for (int i = 0; i < MAX_CAPACITY; i++) {
				if (this.getItemAt(i) == null) {
					items[i] = addedItem;
					successful = true;
					break;
				}
			}
		}
		
		return successful;
	}
}

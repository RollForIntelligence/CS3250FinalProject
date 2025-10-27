
public abstract class Weapon extends Item {
	protected int damageValue;
	protected int range;
	private final static int maxStackSize = 3;
	
	public int getDamage() {
		return damageValue;
	}
	
	public int getRange() {
		return range;
	}
	
	public boolean equals(Weapon weapon) {
		if (!weapon.name.equals(this.name)) {
			return false;
		}
		else if (weapon.damageValue != this.damageValue) {
			return false;
		}
		else if (weapon.range != this.range) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public static int getMaxStackSize() {
		return maxStackSize;
	}
}

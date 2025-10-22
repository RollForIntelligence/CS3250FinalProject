
public abstract class Weapon extends Item {
	protected int damageValue;
	protected int range;
	
	public int getDamage() {
		return damageValue;
	}
	
	public int getRange() {
		return range;
	}
}

package week8;

public class Axe {
	private int damage;
	private int durability;
	private int maxDurability;
	
	public Axe(int damage, int durability) {
		this.damage = damage;
		this.maxDurability =  durability;
		this.durability = durability;
	}
	
	public int hit(){
		if(durability > 0 && durability >= maxDurability/2) {
			durability--;
			return damage;
		} 
		if(damage > 1) {
			--damage;
		}  
		
		return damage;
	}
}

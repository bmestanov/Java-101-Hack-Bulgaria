package week8;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Hero {
	private String name;
	private String title;
	private int health;
	private int life;
	private List<Spell> spells;
	private List<Weapon> weapons;
	private int initHealth;
	private Scanner sc;

	public Hero(String name, String title, int health) {
		this.name = name;
		this.title = title;
		this.health = health;
		this.initHealth = health;
		this.life = 9;
		this.spells = new ArrayList();
		this.weapons = new ArrayList();
		this.sc = new Scanner(System.in);
	}

	public String knownAs() {
		return name + " the " + title;
	}

	public int getHealth() {
		return health;
	}

	public boolean isAlive() {
		return health > 0;
	}

	public boolean canCast() {
		return spells.isEmpty();
	}

	public void takeDamage(int points) {
		health = Math.max(0, health - points);
	}

	public void takeHealing(int points) {
		health = Math.max(initHealth, health + points);
	}

	public void equipWeapon(Weapon weapon) {
		weapons.add(weapon);
	}

	public void learnSpell(Spell spell) {
		spells.add(spell);
	}

	public void attack() {
		if (spells.isEmpty() && weapons.isEmpty()) {
			System.out.println("Oh no! " + "You don't have any weapons or spells. Time to claw some!");
			return;
		}
		
		int choice;
		if(!spells.isEmpty() && !weapons.isEmpty()){
			do {
				System.out.println("You have both weapons and spells, choose what to do! \n "
						+ "Hit 1 for a weapon attack or 2 for a spell attack.");
				choice = sc.nextInt();
			} while (choice != 1 || choice !=2);
			if(choice == 1) {
				attackWithWeapons();
			} else {
				attackWithSpells();
			}
		} else if(!spells.isEmpty()) {
			attackWithSpells();
		} else {
			attackWithWeapons();
		}

	}

	private void attackWithWeapons() {
		int choice;
		do {
			for (int i = 0; i < weapons.size(); i++) {
				System.out.println(Integer.toString(i + 1) + ": " + weapons.get(i));
			}

			choice = sc.nextInt();
		} while (choice < 0 || choice > weapons.size());
		//return the damage instead
	}
	
	private void attackWithSpells() {
		int choice;
		do {
			for (int i = 0; i < spells.size(); i++) {
				System.out.println(Integer.toString(i + 1) + ": " + spells.get(i));
			}

			choice = sc.nextInt();

		} while (choice < 0 || choice > spells.size());
		//return the spell instead
	}
}

package br.com.amil.fps.domain;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Player {
	
	private String name;
	
	private int kills;
	
	private int deaths;
	
	private int currentStreak;
	
	List<String> weapons = new ArrayList<>();
	
	List<Integer> streaks =  new ArrayList<>();
	
	public Player(String name) {
		this.name = name;
	}

	public String name() {
		return name;
	}

	public void kill(String weaponUsed) {
		weapons.add(weaponUsed);
		kills++;
		currentStreak++;
	}
	
	public void died() {
		deaths++;
		streaks.add(currentStreak);
		currentStreak = 0;
	}
	
	public int kills() {
		return kills;
	}
	
	public int deaths() {
		return deaths;
	}
	
	public AddictedPlayer addicted() {
		return new AddictedPlayer();
	}
	
	public class AddictedPlayer {
		
		public String prefferedWeapon() {
			if (kills() == 0) return "N/A";
			Map<String, Long> groupedWeapons = weapons.stream().collect(Collectors.groupingBy(weapon -> weapon, Collectors.counting()));
			return groupedWeapons.entrySet().stream().max((weaponA, weaponB) -> weaponA.getValue().compareTo(weaponB.getValue())).get().getKey();
		}
		
		public int longestStreak() {
			if(isImmortal()) return currentStreak;
			streaks.sort(Comparator.reverseOrder());
			return streaks.get(0);
		}
		
		public boolean isImmortal() {
			return deaths() == 0;
		}
		
	}
	
}

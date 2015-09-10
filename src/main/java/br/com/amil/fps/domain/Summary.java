package br.com.amil.fps.domain;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class Summary {

	private static String playerLine = "%s - %d - %d - %s - %d";
	
	public static void show(Match match) {
		System.out.println();
		printHeader(match);
		buildPlayerOutput(match).forEach(System.out::println);;
	}

	private static void printHeader(Match match) {
		System.out.println("Match number " + match.id());
		System.out.println("Name - Kills - Deaths - Preffered Weapon - Award");
	}

	private static List<String> buildPlayerOutput(Match match) {
		return match.players().stream().map(new Function<Player, String>() {

			@Override
			public String apply(Player player) {
				return String.format(playerLine, 
						player.name(), 
						player.kills(), 
						player.deaths(), 
						player.addicted().prefferedWeapon(),
						player.addicted().isImmortal() ? 1 : 0);
			}
		}).collect(Collectors.toList());
	}
	
	
}

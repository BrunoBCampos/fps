package br.com.amil.fps.domain;

import java.util.ArrayList;
import java.util.List;

public class Match {
	
	private int id;
	
	private List<Event> events = new ArrayList<>();
	
	private List<Player> players = new ArrayList<>();
	
	private boolean ended;
	
	public Match(String id) {
		this.id = Integer.parseInt(id);
	}
	
	public void add(Event event) {
		events.add(event);
		proccessEvent(event);
	}
	
	private void proccessEvent(Event event) {
		Player killer = resolvePlayer(event.killer());
		if(!killer.name().equals("<WORLD>")) killer.kill(event.weapon());
		resolvePlayer(event.victim()).died();
	}

	private Player resolvePlayer(String playerName) {
		if(playerName.equals("<WORLD>")) return new Player("<WORLD>");
		
		Player player = player(playerName);
		if(player != null) return player;
		
		players.add(new Player(playerName));
		return players.get(players.size() -1);
	}
	
	public boolean ended() {
		return ended;
	}
	
	public void end() {
		ended = true;
	}
	
	public int id() {
		return id;
	}

	public Player player(String playerName) {
		return players.stream().filter(player -> player.name().equals(playerName)).findFirst().orElse(null);
	}
	
	public List<Player> players() {
		return players;
	}

	public List<Event> events() {
		return events;
	}

}

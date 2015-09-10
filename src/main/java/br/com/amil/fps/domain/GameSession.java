package br.com.amil.fps.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GameSession {
	
	private List<Match> matches = new ArrayList<>();
	
	public Match currentMatch() {
		return getCurrent().orElse(null);
	}
	
	public Match lastMatch() {
		return matches.stream().filter(match -> match.ended()).reduce((previous, current) -> current).orElse(null);
	}

	public void add(Match match) {
		Optional<Match> current = getCurrent();
		if(current.isPresent() && !current.get().ended()) current.get().end();
		matches.add(match);
	}
	
	private Optional<Match> getCurrent() {
		return matches.stream().filter(match -> !match.ended()).findFirst();
	}
	
	public Match get(int id) {
		return matches.stream().filter(match -> match.id() == id).findFirst().orElse(null);
	}
	
	public List<Match> matches() {
		return matches;
	}
	
}

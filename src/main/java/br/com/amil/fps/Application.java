package br.com.amil.fps;

import java.io.IOException;
import java.net.URISyntaxException;

import br.com.amil.fps.domain.GameSession;
import br.com.amil.fps.domain.Summary;
import br.com.amil.fps.parser.MatchParser;

public class Application {
	
	public static void main(String[] args) throws URISyntaxException, IOException {
		
		MatchParser parser = new MatchParser();
		parser.proccessInput("input.txt");
		GameSession session = new GameSession();
		parser.getMatches().stream().forEach(match -> session.add(match));
		session.matches().forEach(Summary::show);
		
	}

}

package br.com.amil.fps.parser;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.amil.fps.domain.Event;
import br.com.amil.fps.domain.Event.Builder;
import br.com.amil.fps.domain.Match;

public class MatchParser {
	
	private static Pattern regexData = Pattern.compile("^(\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2})");
	private static Pattern START = Pattern.compile(regexData + " - New match (\\d+) has started$");
	private static Pattern END = Pattern.compile(regexData + " - Match (\\d+) has ended$");
	private static Pattern CHALLENGE = Pattern.compile(regexData + " - (\\S+) killed (\\S+) (using|by) (\\w+)$");
	
	List<Match> matches = new ArrayList<>();
	
	public void proccessInput(String filePath) throws URISyntaxException, IOException {
		Files.lines(Paths.get(MatchParser.class.getClassLoader().getResource(filePath).toURI())).forEach(line -> parseLine(line));
	}
	
	private void parseLine(String line) {
		
		if(line.matches(START.pattern())) {
			Matcher matcher = START.matcher(line);
			matcher.matches();
			matches.add(new Match(matcher.group(2)));
		}
		
		if(line.matches(END.pattern())) {
			matches.get(matches.size() -1).end();
		}
		
		if(line.matches(CHALLENGE.pattern())) {
			Matcher matcher = CHALLENGE.matcher(line);
			matcher.matches();
			Builder builder = new Event.Builder();
			builder.killer(matcher.group(2));
			builder.victim(matcher.group(3));
			builder.weapon(matcher.group(5));
			builder.date(LocalDateTime.parse(matcher.group(1), DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
			lastMatch(matches).add(builder.build());
		}
	}
	
	private Match lastMatch(List<Match> matches) {
		return matches.get(matches.size()-1); 
	}
	
	public List<Match> getMatches() {
		return matches;
	}
	
}

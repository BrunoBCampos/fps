package br.com.amil.fps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import org.junit.Test;

import br.com.amil.fps.domain.Match;
import br.com.amil.fps.parser.MatchParser;

public class MatchParserTest {
	
	@Test
	public void shouldCreateEvents() throws URISyntaxException, IOException {
		MatchParser parser = new MatchParser();
		parser.proccessInput("input.txt");
		List<Match> matches = parser.getMatches();
		assertThat(matches.get(0), is(notNullValue()));
		assertThat(matches.get(0).events(), is(notNullValue()));
		assertThat(matches.get(0).events().size(), is(equalTo(5)));
		
	}
	
}

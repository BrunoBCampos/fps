package br.com.amil.fps;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.*;

import org.junit.Test;
import static org.junit.Assert.*;

import br.com.amil.fps.domain.Event;
import br.com.amil.fps.domain.Match;

public class MatchTest {
	
	@Test
	public void shouldCreatePlayerIfNotExists() {
		Match match = new Match("1");
		match.add(new Event("Mario", "Vedita", "Lerdeza", LocalDateTime.now()));
		assertThat(match.player("Mario"), is(notNullValue()));
	}
	
	@Test
	public void shouldNotCreateSamePlayerMoreThanOnce() {
		Match match = new Match("1");
		match.add(new Event("Mario", "Vedita", "Lerdeza", LocalDateTime.now()));
		match.add(new Event("Mario", "Bjornn", "Lerdeza", LocalDateTime.now()));
		match.add(new Event("Mario", "Bjornn", "Burocracia", LocalDateTime.now()));
		assertThat(match.players().size(), is(equalTo(3)));
	}
	
	@Test
	public void shouldProccessRegularEventCorrectly() {
		Match match = new Match("1");
		match.add(new Event("Mario", "Vedita", "Lerdeza", LocalDateTime.now()));
		assertThat(match.player("Mario").kills(), is(equalTo(1)));
	}
	
	@Test
	public void shouldProccessWORLDEventCorrectly() {
		Match match = new Match("1");
		match.add(new Event("<WORLD>", "Vedita", "Causas Naturais", LocalDateTime.now()));
		assertThat(match.player("<WORLD>"), is(nullValue()));
		assertThat(match.player("Vedita").kills(), is(equalTo(0)));
		assertThat(match.player("Vedita").deaths(), is(equalTo(1)));
	}
	
	@Test
	public void shouldNotFindPlayer() {
		Match match = new Match("1");
		match.add(new Event("Mario", "Vedita", "Lerdeza", LocalDateTime.now()));
		assertThat(match.player("Bira"), is(nullValue()));
	}

}

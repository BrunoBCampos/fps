package br.com.amil.fps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;

import java.time.LocalDateTime;

import org.junit.Before;
import org.junit.Test;

import br.com.amil.fps.domain.Event;
import br.com.amil.fps.domain.GameSession;
import br.com.amil.fps.domain.Match;

public class GameSessionTest {
	
	private GameSession session;
	
	@Before
	public void before() {
		Match match = new Match("1");
		match.add(new Event("<WORLD>", "Vedita", "Causas Naturais", LocalDateTime.now()));
		session = new GameSession();
		session.add(match);
	}

	
	@Test
	public void shouldReturnCurrentMatchOnlyIfIsOpened() {
		assertThat(session.currentMatch(), is(notNullValue()));
	}
	
	@Test
	public void shouldNotReturnCurrentWhenThereIsNoMatchOpened() {
		session.currentMatch().end();
		assertThat(session.currentMatch(), is(nullValue()));
	}

	@Test
	public void shouldReturnLastMatchOnlyIfIsEnded() {
		session.currentMatch().end();
		assertThat(session.lastMatch(), is(notNullValue()));
	}

	@Test
	public void shouldNotReturnLastMatch() {
		assertThat(session.lastMatch(), is(nullValue()));
	}
	
	@Test
	public void shouldEndCurrentMatchIfANewMatchIsCreated() {
		session.add(new Match("2"));
		assertThat(session.lastMatch().id(), is(equalTo(1)));
		assertThat(session.currentMatch().id(), is(equalTo(2)));
	}
	
	@Test
	public void shouldReturnMatchById() {
		assertThat(session.get(1), is(notNullValue()));
	}
	
	@Test
	public void shouldNotReturnMatchById() {
		assertThat(session.get(8), is(nullValue()));
	}

}

package br.com.amil.fps;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import br.com.amil.fps.domain.Player;

public class PlayerTest {
	
	@Test
	public void shouldIncreaseNumberOfKillsWhenHeKills() {
		Player player =  new Player("Mocotó");
		player.kill("AK47");
		assertThat(player.kills(), is(equalTo(1)));
	}
	
	@Test
	public void shouldIncreaseNumberOfDeathsWhenHeDies() {
		Player player =  new Player("Mocotó");
		player.died();
		assertThat(player.deaths(), is(equalTo(1)));
	}
	
	@Test
	public void shouldBeImmortalIfPlayerNeverDies() {
		Player player =  new Player("Mario");
		player.kill("AK47");
		player.kill("SPAS-12");
		assertThat(player.addicted().isImmortal(), is(true));
	}
	
	@Test
	public void shouldNotBeImmortalIfPlayerDies() {
		Player player =  new Player("Mario");
		player.kill("AK47");
		player.died();
		assertThat(player.addicted().isImmortal(), is(false));
	}
	
	@Test
	public void addictedPlayerShoudlKnowHisPrefferedWeapon() {
		Player player =  new Player("Kamakura");
		player.kill("AK47");
		player.kill("SPAS-12");
		player.kill("KNIFE");
		player.kill("KNIFE");
		player.kill("KNIFE");
		assertThat(player.addicted().prefferedWeapon(), is(equalTo("KNIFE")));
	}
	
	@Test
	public void addictedPlayerShoudlNotHavePrefferedWeaponIfHeHadNoKills() {
		Player player =  new Player("Kamakura");
		assertThat(player.addicted().prefferedWeapon(), is(equalTo("N/A")));
	}
	
	@Test
	public void addictedPlayerShoudlKnowHisLongestStreakIfHeDies() {
		Player player =  new Player("Bira");
		player.kill("AK47");
		player.died();
		player.kill("KNIFE");
		player.kill("KNIFE");
		player.kill("AK47");
		player.kill("KNIFE");
		player.died();
		player.kill("AK47");
		assertThat(player.addicted().longestStreak(), is(equalTo(4)));
	}
	
	@Test
	public void addictedPlayerShoudlKnowHisLongestStreakIfHeIsImmortal() {
		Player player =  new Player("Bira");
		player.kill("AK47");
		player.kill("KNIFE");
		player.kill("KNIFE");
		player.kill("AK47");
		player.kill("KNIFE");
		player.kill("AK47");
		assertThat(player.addicted().longestStreak(), is(equalTo(6)));
	}
	

}

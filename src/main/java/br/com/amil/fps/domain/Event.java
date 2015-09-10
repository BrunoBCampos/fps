package br.com.amil.fps.domain;

import java.time.LocalDateTime;

public class Event {
	
	private String killer;
	private String victim;
	private String weapon;
	private LocalDateTime date;

	public Event(String killer, String victim, String weapon, LocalDateTime date) {
		this.killer = killer;
		this.victim = victim;
		this.weapon = weapon;
		this.date = date;
	}
	
	public String killer() {
		return killer;
	}

	public String victim() {
		return victim;
	}

	public String weapon() {
		return weapon;
	}

	public LocalDateTime date() {
		return date;
	}

	public static class Builder {
		
		private String killer;
		private String victim;
		private String weapon;
		private LocalDateTime date;
		
		public Builder killer(String killer) {
			this.killer = killer;
			return this;
		}
		
		public Builder victim(String victim) {
			this.victim = victim;
			return this;
		}
		
		public Builder weapon(String weapon) {
			this.weapon = weapon;
			return this;
		}
		
		public Builder date(LocalDateTime date) {
			this.date = date;
			return this;
		}
		
		public Event build() {
			return new Event(killer, victim, weapon, date);
		}
		
	}

}

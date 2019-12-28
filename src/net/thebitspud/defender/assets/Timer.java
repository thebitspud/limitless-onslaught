package net.thebitspud.defender.assets;

//a simple timer class that can be used in various ways

public class Timer {
	private long ticks, delay;
	private boolean activated, repeating;

	public Timer(long delay, boolean repeating) {
		this.delay = delay; //how long until the event is activated / 60 = 1 second
		this.repeating = repeating;
	}

	public void tick() {
		if(ticks >= delay) {
			activated = true;
			if(repeating) ticks = 0;
		}else{
			ticks++;
			activated = false;
		}
	}

	public void activateOnNext() {
		ticks = delay;
	}

	public void reset() {
		ticks = 0;
		activated = false;
	}

	//getters and setters

	public boolean isActivated() {
		tick();

		return activated;
	}

	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}

	public long getTicks() {
		return ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public boolean getRepeating() {
		return repeating;
	}
}
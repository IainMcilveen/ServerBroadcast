package com.iain.serverbroadcast;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	// add &#(number) to add colour, regex will fix
	private String[] messages = { "xd", "Hello, World!", "This is a test", "die xd", "this is dumb","i just needed a longer array", "this perhaps is a test", "wtf is this xd" };
	private String[] textColours = { "&b", "&4", "&c", "&6", "&e", "&a", "&9", "&d" };
	private Main main;
	private int currentTime = 5;
	private BukkitRunnable currentRunnable = null;

	public Broadcast(Main main) {
		this.main = main;
		sendBroadcast(main);

	}
	
	public void setCurrentTime(int currentTime) {
		this.currentTime = currentTime;
	}

	public void setPending(int newTime) {
		this.currentTime = newTime;
		currentRunnable.cancel();
		sendBroadcast(main);
	}

	public void setRunnable(BukkitRunnable runnable) {
		this.currentRunnable = runnable;
	}

	public BukkitRunnable getRunnable() {
		return this.currentRunnable;
	}

	public void sendBroadcast(Main main) {

		BukkitRunnable runnable = new BukkitRunnable() {

			String lastmsg = null;
			int randomIndex;
			int randomColour;

			@Override
			public void run() {

				randomIndex = (int) (Math.random() * (messages.length));
				randomColour = (int) (Math.random() * (textColours.length));

				if (lastmsg == null) {
					lastmsg = messages[randomIndex];
				} else {
					while (lastmsg == messages[randomIndex]) {
						randomIndex = (int) (Math.random() * (messages.length));
					}
				}
				if (getRunnable() != this) {
					setRunnable(this);
				}
				Bukkit.broadcastMessage((textColours[randomColour] + messages[randomIndex]).replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "§"));
			}

		};
		// main, start delay, delay between running 20: 20 ticks : 1 second real time.
		currentTime = main.getCurrentTime();
		runnable.runTaskTimer(main, 0, 20 * currentTime);

	}

}

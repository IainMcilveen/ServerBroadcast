package com.iain.serverbroadcast;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	// add &#(number) to add colour, regex will fix
	//private String[] messages = { "xd", "Hello, World!", "This is a test", "die xd", "this is dumb","i just needed a longer array", "this perhaps is a test", "wtf is this xd" };
	private ArrayList<String[]> messages;
	private String[] textColours = { "&b", "&4", "&c", "&6", "&e", "&a", "&9", "&d" };
	private Main main;
	private int currentTime = 5;
	private BukkitRunnable currentRunnable = null;

	public Broadcast(Main main, ArrayList<String[]> messages) {
		this.main = main;
		this.messages = messages;
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

			String[] lastmsg = null;
			int lastIndex;
			int randomIndex;
			int randomColour;

			@Override
			public void run() {

				randomIndex = (int) (Math.random() * (messages.size()));
				randomColour = (int) (Math.random() * (textColours.length));

				if (lastmsg == null) {
					lastmsg = messages.get(randomIndex);
				} else {
					lastmsg = messages.get(lastIndex);
					while (lastmsg[0].equals((messages.get(randomIndex))[0])) {
						randomIndex = (int) (Math.random() * (messages.size()));
					}
				}
				
				if (getRunnable() != this) {
					setRunnable(this);
				}
				
				lastIndex = randomIndex;
				for(String sentence: messages.get(randomIndex)) {
					Bukkit.broadcastMessage((textColours[randomColour] + sentence).replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "§"));
				}
				
			}

		};
		
		// main, start delay, delay between running 20: 20 ticks : 1 second real time.
		currentTime = main.getCurrentTime();
		runnable.runTaskTimer(main, 0, 20 * currentTime);

	}

}

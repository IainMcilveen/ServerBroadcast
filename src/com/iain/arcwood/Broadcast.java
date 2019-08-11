package com.iain.arcwood;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	// add &#(number) to add colour, regex will fix
	private String[] messages = { "xd", "hello, World!", "This is a test" };
	private String[] textColours = { "&b", "&4", "&c", "&6", "&e", "&a", "&9", "&d" };

	public Broadcast(Main main) {

		this.messages[0] = "lol this is an xd";
		sendBroadcast(main);

	}

	public void sendBroadcast(Main main) {

		BukkitRunnable runnable = new BukkitRunnable() {
			
			String lastmsg = null;
			int randomIndex;
			int randomColour;

			@Override
			public void run() {
				randomIndex = (int) (Math.random() * (messages.length - 1));
				randomColour = (int) (Math.random() * (textColours.length - 1));
				if(lastmsg == null) {
					lastmsg = messages[randomIndex];
				}else {
					while(lastmsg == messages[randomIndex]) {
						randomIndex = (int) (Math.random() * (messages.length - 1));
					}
				}
				
				Bukkit.broadcastMessage((textColours[randomColour] + messages[randomIndex]).replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "§"));
			}

		};
		// main, start delay, delay between running 20: 20 ticks : 1 second real time.
		runnable.runTaskTimer(main, 0, 20 * 60);

	}

}

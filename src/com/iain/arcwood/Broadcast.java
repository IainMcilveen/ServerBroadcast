package com.iain.arcwood;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	// add &#(number) to add colour, regex will fix
	private String[] messages = { "xd", "Hello, World!", "This is a test","die xd","this is dumb","i just needed a longer array","this perhaps is a test","wtf is this xd" };
	private String[] textColours = { "&b", "&4", "&c", "&6", "&e", "&a", "&9", "&d" };
	public int pendingChanges = 0;
	private Main main;
	private boolean cancelled = false;
	public int currentTime = 5;
	
	
	public Broadcast(Main main) {
		this.main=main;
		sendBroadcast(main);

	}
	
	public void setPending(int newTime) {
		this.pendingChanges = newTime;
		System.out.println("set");
		sendBroadcast(main);
	}

	public void sendBroadcast(Main main) {

		BukkitRunnable runnable = new BukkitRunnable() {
			
			String lastmsg = null;
			int randomIndex;
			int randomColour;

			@Override
			public void run() {
				if(pendingChanges > 0) {
					System.out.println("pended changes");
					cancelled = true;
					this.cancel();
				}
				
				randomIndex = (int) (Math.random() * (messages.length));
				randomColour = (int) (Math.random() * (textColours.length));
				if(lastmsg == null) {
					lastmsg = messages[randomIndex];
				}else {
					while(lastmsg == messages[randomIndex]) {
						randomIndex = (int) (Math.random() * (messages.length));
					}
				}
				Bukkit.broadcastMessage((textColours[randomColour] + messages[randomIndex]).replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "�"));
			}

		};
		// main, start delay, delay between running 20: 20 ticks : 1 second real time.
		if(cancelled == true) {
			currentTime = pendingChanges;
			pendingChanges = 0;
			cancelled = false;	
			System.out.println("in");
		}
		System.out.println("out");
		runnable.runTaskTimer(main, 0, 20 * currentTime);
		

	}
	

}

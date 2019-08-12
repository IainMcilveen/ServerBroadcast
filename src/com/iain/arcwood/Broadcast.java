package com.iain.arcwood;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	// add &#(number) to add colour, regex will fix
	private String[] messages = { "xd", "Hello, World!", "This is a test","die xd","this is dumb","i just needed a longer array","this perhaps is a test","wtf is this xd" };
	private String[] textColours = { "&b", "&4", "&c", "&6", "&e", "&a", "&9", "&d" };
	public int pendingChanges = 0;
	private Main main;
	public boolean commandCalled = false;
	public boolean killInstance = false;
	public boolean instanceKilled = false;
	public int currentTime = 5;
	public BukkitRunnable runnableStore = null;
	
	
	public Broadcast(Main main) {
		this.main=main;
		sendBroadcast(main);

	}
	
	public void setRunnable(BukkitRunnable runnable) {
		this.runnableStore = runnable;
	}
	
	/*
	public void setPending(int newTime) {
		this.pendingChanges = newTime;
		this.currentTime = newTime;
		commandCalled = true;
		killInstance = true;
		System.out.println("set");
	}
	*/
	public void setPending(int newTime) {
		this.pendingChanges = newTime;
		this.currentTime = newTime;
		sendBroadcast(main);
		System.out.println("renew");
	}
	
	public void restart() {
		sendBroadcast(main);
	}

	public void sendBroadcast(Main main) {

		BukkitRunnable runnable = new BukkitRunnable() {
			
			
			String lastmsg = null;
			int randomIndex;
			int randomColour;

			@Override
			public void run() {
				
				if(killInstance == true) {
					System.out.println("ins: "+currentTime);
					System.out.println("killed it");
					killInstance = false;
					instanceKilled = true;
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
				System.out.println("bb ct: "+currentTime);
				Bukkit.broadcastMessage((textColours[randomColour] + messages[randomIndex]).replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "§"));
			}

		};
		// main, start delay, delay between running 20: 20 ticks : 1 second real time.
		System.out.println("broke out");
		
		if(commandCalled == true) {
			currentTime = pendingChanges;
			pendingChanges = 0;
			commandCalled = false;
			System.out.println("incommandcalled");
		}
		
		if(instanceKilled == true) {
			System.out.println("creating new instance");
			sendBroadcast(main);
			instanceKilled = false;
		}
		System.out.println("out");
		runnable.runTaskTimer(main, 0, 20 * currentTime);
		
	}
	

}

package com.iain.arcwood;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

public class Broadcast {

	//add &#(number) to add colour, regex will fix
	private String[] msg = { "xd", "&1hello, World!", "This is a test" };
	
	public Broadcast(Main main) {

		this.msg[0] = "lol this is an xd";
		sendBroadcast(main);

	}

	public void sendBroadcast(Main main) {

		BukkitRunnable runnable = new BukkitRunnable() {

			@Override
			public void run() {
				Bukkit.broadcastMessage(msg[1].replaceAll("(?<!\\\\)&(?=[0-9A-fK-o])", "§"));
			}

		};
		//main, start delay, delay between running 20: 20 ticks : 1 second real time.
		runnable.runTaskTimer(main, 0, 20 * 60);

	}

}

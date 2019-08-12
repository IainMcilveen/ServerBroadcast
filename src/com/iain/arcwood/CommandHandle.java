package com.iain.arcwood;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandle implements CommandExecutor {
	
	private Broadcast broadcast;

	
	public CommandHandle(Broadcast broadcast) {
		this.broadcast = broadcast;
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int newTime;
		if(label.equals("rmb")) {
			try {
				newTime = Integer.parseInt(args[0]);
				System.out.println(newTime);
				broadcast.setPending(newTime);
			}catch(Exception e) {
				System.out.println("killed itself");
				newTime = 5;
			}
			
		}
		return true;
	}
}

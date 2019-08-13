package com.iain.arcwood;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class CommandHandle implements CommandExecutor {

	private Broadcast broadcast;
	private Main main;

	public CommandHandle(Broadcast broadcast, Main main) {
		this.broadcast = broadcast;
		this.main = main;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		int newTime;
		if (label.equals("rmb")) {
			try {
				newTime = Integer.parseInt(args[0]);
			} catch (Exception e) {
				return false;
			}
			
			if(args.length > 1 && args[1] instanceof String && args[1].toUpperCase().equals("M")) {
				newTime *= 60;
			}
			main.setCurrentTime(newTime);
			broadcast.setPending(newTime);
			
			
			

		}
		return true;
	}
}

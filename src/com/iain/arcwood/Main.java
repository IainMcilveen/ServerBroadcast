package com.iain.arcwood;


import java.io.File;
import java.io.IOException;

import org.bukkit.plugin.java.JavaPlugin;
import com.google.common.io.Files;


public class Main extends JavaPlugin {
	
	public String location = this.getDataFolder().getPath();
	
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		Broadcast broadcast = new Broadcast(this);
		CommandHandle commandhandle = new CommandHandle(broadcast);
		
		this.getCommand("rmb").setExecutor(commandhandle);
		
		File config = new File(this.getDataFolder(), "config.json");
		
		if(this.getDataFolder().exists() == false) {
			System.out.println("Config folder not found, creating new one...");
			this.getDataFolder().mkdirs();
		}
		
		try {
			config.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
}

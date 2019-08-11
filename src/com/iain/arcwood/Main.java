package com.iain.arcwood;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		Broadcast broadcast = new Broadcast(this);
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
}

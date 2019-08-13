package com.iain.arcwood;


import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

//ideas:
//{player} = player name, {variable} = result based on that
//removing messages


public class Main extends JavaPlugin {
	
	private int currentTime;
	private ArrayList<JSONArray> jsonMessages;
	private final ArrayList<String[]> messages = new ArrayList<String[]>();
	
	public String location = this.getDataFolder().getPath();
	
	public void readJson(File json) {
		
		
		JSONParser parser = new JSONParser();
		try {
			JSONObject obj = (JSONObject) parser.parse(new FileReader(json) );
		
			
			//getting the current time from the file
			this.currentTime =  Math.toIntExact((long) obj.get("currentTime"));
		
			//getting the text
			JSONArray array = (JSONArray) obj.get("text");
			for (Object object : array) {
				JSONArray innerArray = (JSONArray) object;
				int length = innerArray.size();
				String[] messages = new String[length];
				for (int i = 0; i < length; i++) {
					messages[i] = innerArray.get(i).toString();
				}
				this.messages.add(messages);
			}
			//System.out.println(messages);
			
		} catch (IOException | org.json.simple.parser.ParseException e) {
			e.printStackTrace();
		}
		
		
		
		/*
		try (Reader reader = new FileReader(json)) {

            JSONObject jsonObject = (JSONObject) parser.parse(reader);
            System.out.println(jsonObject);

            String name = (String) jsonObject.get("name");
            System.out.println(name);

            long age = (Long) jsonObject.get("age");
            System.out.println(age);

            // loop array
            JSONArray msg = (JSONArray) jsonObject.get("messages");
            
            
            Iterator<String> iterator = msg.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
            

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        */
		
	}
	
	public void writeJson() {
		
	}
	
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		File config = new File(this.getDataFolder(), "config.json");
		
		if(this.getDataFolder().exists() == false) {
			System.out.println("Config folder not found, creating new one...");
			this.getDataFolder().mkdirs();
		}
		
		if(config.exists() == false) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		readJson(config);
		
		Broadcast broadcast = new Broadcast(this);
		CommandHandle commandhandle = new CommandHandle(broadcast);
		this.getCommand("rmb").setExecutor(commandhandle);
		
	}
	
	@Override
	public void onDisable() {
		super.onDisable();
	}
	
	public int getCurrentTime() {
		return this.currentTime;
	}
	
}

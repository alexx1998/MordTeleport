package com.mordonia.mordteleport.events.data;

import com.mordonia.mordteleport.MordTeleport;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;

public class LocationDataManger {
    private JavaPlugin plugin = MordTeleport.getProvidingPlugin(MordTeleport.class);
    public HashMap<String, LocationData> datamap = new HashMap<>();


    public void loadMap(){
        for(String name : plugin.getConfig().getConfigurationSection("tp.").getKeys(false)){
            if(name == null){
                System.out.println("Could could not load locations! Empty!");
                break;
            }



        }
    }
}

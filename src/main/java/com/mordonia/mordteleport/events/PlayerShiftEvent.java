package com.mordonia.mordteleport.events;

import com.mordonia.mordteleport.MordTeleport;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class PlayerShiftEvent implements Listener {
    private JavaPlugin plugin = MordTeleport.getProvidingPlugin(MordTeleport.class);
    public MordTeleport mordTeleport;
    public PlayerShiftEvent(MordTeleport mordTeleport){
        this.mordTeleport = mordTeleport;
    }
    @EventHandler
    public void onShift(PlayerToggleSneakEvent event){
        Player p = event.getPlayer();
        Location loc = p.getLocation();
        Block b = loc.getBlock().getRelative(BlockFace.DOWN);
        LuckPermsApi luckPermsApi = mordTeleport.getLuckPermsApi();
        String removeGroup = plugin.getConfig().getString("worlds.remove");
        String addGroup = plugin.getConfig().getString("worlds.add");
        for(String name : plugin.getConfig().getConfigurationSection("tp.").getKeys(false)){
            if(b.getType() == Material.GOLD_BLOCK){
                World world = p.getWorld();
                String wName = plugin.getConfig().getString("worlds.world");
                if(world.getName().equals(wName)){
                    if(!luckPermsApi.getUser(p.getDisplayName()).getPrimaryGroup().equalsIgnoreCase(addGroup)){
                       Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getDisplayName() + " parent add " + addGroup);
                    }
                }
                int x = b.getX();
                int z = b.getZ();
                int y = b.getY();
                int locX = plugin.getConfig().getInt("tp." + name + ".x");
                int locZ = plugin.getConfig().getInt("tp." + name + ".z");
                int locY = plugin.getConfig().getInt("tp." + name + ".y");

                if(x == locX && z == locZ && y == locY){
                    int destX = plugin.getConfig().getInt("tp." + name + ".destination.x");
                    int destY = plugin.getConfig().getInt("tp." + name + ".destination.y");
                    int destZ = plugin.getConfig().getInt("tp." + name + ".destination.z");
                    String worldName = plugin.getConfig().getString("tp." + name + ".destination.world");
                    Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "mvtp " + p.getDisplayName() + " e:" + worldName +":" + destX + "," + destY + "," + destZ);
                    p.sendMessage(ChatColor.GOLD + "You teleported successfully!");
                }
            }
        }


    }
}

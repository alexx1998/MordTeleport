package com.mordonia.mordteleport.commands;

import com.mordonia.mordteleport.MordTeleport;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Commands implements CommandExecutor {
    private JavaPlugin plugin = MordTeleport.getProvidingPlugin(MordTeleport.class);
    private int destX, destY, destZ;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;



        if(command.getName().equals("mordtp")){
            if(args.length < 1){
                return false;
            }
            if(args[0].equals("create")){
                if(args.length < 6){
                    p.sendMessage("/mordtp create [uniqueName] [world] [x] [y] [z]");
                    return false;
                }
                    if(!sender.hasPermission("mordtp.set")){
                        p.sendMessage(ChatColor.RED + "You don't have enough permission to run this command!");
                        return false;
                    }
                    String name = args[1];
                    Location loc = p.getLocation();
                    Block b = loc.getBlock().getRelative(BlockFace.DOWN);
                    int locx = b.getX();
                    int locy = b.getY();
                    int locz = b.getZ();

                    try{
                        destX = Integer.parseInt(args[3]);
                        destY = Integer.parseInt(args[4]);
                        destZ = Integer.parseInt(args [5]);
                    }
                    catch (NumberFormatException e){
                        sender.sendMessage("/mordtp create [uniqueName] [world] [x] [y] [z]");
                        return false;
                    }

                    String destWorld = args[2];
                    World dest = Bukkit.getWorld(destWorld);
                    if(!Bukkit.getServer().getWorlds().contains(dest)){
                        sender.sendMessage(ChatColor.DARK_RED + "Invalid world");
                        return false;
                    }
                    b.setType(Material.GOLD_BLOCK);


                plugin.getConfig().createSection("tp." + name);
                    plugin.getConfig().set("tp." + name + ".x", locx);
                    plugin.getConfig().set("tp." + name + ".y", locy);
                    plugin.getConfig().set("tp." + name + ".z", locz);
                    plugin.getConfig().set("tp." + name + ".destination.world", destWorld);
                    plugin.getConfig().set("tp." + name + ".destination.x", destX);
                    plugin.getConfig().set("tp." + name + ".destination.y", destY);
                    plugin.getConfig().set("tp." + name + ".destination.z", destZ);
                    p.sendMessage("Succesfully created a warp point!");
                    plugin.saveConfig();

                    return true;

                }
                }
                    return false;
                }

            }


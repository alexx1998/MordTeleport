package com.mordonia.mordteleport;

import com.mordonia.mordteleport.commands.Commands;
import com.mordonia.mordteleport.events.PlayerShiftEvent;
import me.lucko.luckperms.api.LuckPermsApi;
import org.bukkit.Bukkit;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public final class MordTeleport extends JavaPlugin {

    private static LuckPermsApi luckPermsApi = null;

    public static LuckPermsApi getLuckPermsApi() {
        return luckPermsApi;
    }

    @Override
    public void onEnable() {
        setupLuckPermsApi();
        this.getCommand("mordtp").setExecutor(new Commands());
        this.getServer().getPluginManager().registerEvents(new PlayerShiftEvent(this), this);
        this.getConfig().options().copyDefaults(true);
        this.saveConfig();

    }

    @Override
    public void onDisable() {
    }

    public void setupLuckPermsApi() {
        RegisteredServiceProvider<LuckPermsApi> provider = Bukkit.getServicesManager().getRegistration(LuckPermsApi.class);
        if (provider != null) {
            luckPermsApi = provider.getProvider();

        }
    }
}

package org.engineergaming.hinklecraft;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        getLogger().info("HinkleCraft enabled!");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PhantomBoostListener(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HinkleCraft disabled!");
    }
}

package org.engineergaming.hinklecraft;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Server server = getServer();

        getLogger().info("HinkleCraft enabled!");
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(), this);
        getServer().getPluginManager().registerEvents(new PhantomBoostListener(), this);
        getServer().getPluginManager().registerEvents(new LightningBoostListener(), this);
        getServer().getPluginManager().registerEvents(new DiamondBreakListener(), this);
        getServer().getPluginManager().registerEvents(new BedtimeBuddyListener(), this);
        getServer().getPluginManager().registerEvents(new CoalListener(), this);

        String[] materials = {"WOODEN", "STONE", "GOLDEN", "IRON", "DIAMOND", "NETHERITE"};
    }

    @Override
    public void onDisable() {
        getLogger().info("HinkleCraft disabled!");
    }
}

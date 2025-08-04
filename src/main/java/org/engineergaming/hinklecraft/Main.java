package org.engineergaming.hinklecraft;

import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        Server server = getServer();

        getLogger().info("HinkleCraft enabled!");
        server.getPluginManager().registerEvents(new PlayerJoinListener(), this);
        server.getPluginManager().registerEvents(new MobChanger(), this);
        server.getPluginManager().registerEvents(new LightningBoostListener(), this);
        server.getPluginManager().registerEvents(new DiamondBreakListener(), this);
        server.getPluginManager().registerEvents(new BedtimeBuddyListener(), this);
        server.getPluginManager().registerEvents(new CoalListener(), this);
        server.getPluginManager().registerEvents(new ItemBreakListener(), this);
        server.getPluginManager().registerEvents(new FurnaceDurability(), this);
        server.getPluginManager().registerEvents(new WaterLavaBucket(), this);
        server.getPluginManager().registerEvents(new FoodPotionRandomizer(), this);
        server.getPluginManager().registerEvents(new LapisPick(), this);
        server.getPluginManager().registerEvents(new InventoryRandomizer(), this);
        server.getPluginManager().registerEvents(new GoldDropListener(), this);
        server.getPluginManager().registerEvents(new CoalItems(), this);
        server.getPluginManager().registerEvents(new GhastSplit(), this);
        server.getPluginManager().registerEvents(new RecipeHandler(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HinkleCraft disabled!");
    }
}

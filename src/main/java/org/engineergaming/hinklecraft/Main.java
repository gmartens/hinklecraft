package org.engineergaming.hinklecraft;

import org.bukkit.*;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

public class Main extends JavaPlugin {


    @Override
    public void onEnable() {
        Server server = getServer();
        PluginManager pm = server.getPluginManager();

        getLogger().info("HinkleCraft enabled!");
        pm.registerEvents(new SuperFlatWorld(), this);
        pm.registerEvents(new PlayerJoinListener(), this);
        pm.registerEvents(new MobChanger(), this);
        pm.registerEvents(new LightningBoostListener(), this);
        pm.registerEvents(new DiamondBreakListener(), this);
        pm.registerEvents(new BedtimeBuddyListener(), this);
        pm.registerEvents(new CoalListener(), this);
        pm.registerEvents(new ItemBreakListener(), this);
        pm.registerEvents(new FurnaceDurability(), this);
        pm.registerEvents(new WaterLavaBucket(), this);
        pm.registerEvents(new FoodPotionRandomizer(), this);
        pm.registerEvents(new LapisPick(), this);
        pm.registerEvents(new InventoryRandomizer(), this);
        pm.registerEvents(new GoldDropListener(), this);
        pm.registerEvents(new CoalItems(), this);
        pm.registerEvents(new GhastSplit(), this);
        pm.registerEvents(new RecipeHandler(), this);
        pm.registerEvents(new SlimeBlockBounce(), this);
    }

    @Override
    public void onDisable() {
        getLogger().info("HinkleCraft disabled!");
    }
}

package org.engineergaming.hinklecraft;

import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private String[] recipeRuiner(String[] input) {
        String concatted = ""; 
        for(String s : input) concatted += s;
        String full_ruined = "";
        for(int i = 0; i < concatted.length(); i++) {
            full_ruined += concatted.charAt((i + 1) % concatted.length());
        }
        String[] output = new String[3];
        for(int i = 0; i < output.length; i++) {
            output[i] = full_ruined.substring(i*3, i*3+3);
        }
        return output;
    }

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

        // FUNNY TOOL RECIPE RUINER!!!
        String[] materials = {"wooden", "stone", "golden", "iron", "diamond"};
        String[] tools = {"pickaxe", "hoe", "shovel", "axe", "sword"};

        String[] flippedRecipeShape = {"ihg", "fed", "cba"};
        for(String mat : materials) {
            flippedRecipeShape = recipeRuiner(flippedRecipeShape);
            for(String tool : tools) {
                NamespacedKey key = NamespacedKey.minecraft(mat + "_" + tool);
                getLogger().info("Flipping: " + key.toString());
                ShapedRecipe recipe = (ShapedRecipe)server.getRecipe(key);
                server.removeRecipe(key);
                server.addRecipe(recipe.shape(flippedRecipeShape));
            }
        }
    }

    @Override
    public void onDisable() {
        getLogger().info("HinkleCraft disabled!");
    }
}

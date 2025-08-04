package org.engineergaming.hinklecraft;

import org.bukkit.NamespacedKey;
import org.bukkit.Server;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import java.util.ArrayList;

public class RecipeHandler implements Listener {
    private final Plugin plugin = Main.getPlugin(Main.class);
    private final Server server = plugin.getServer();

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

    public static ArrayList<NamespacedKey> recipes = new ArrayList<NamespacedKey>();

    RecipeHandler() {
        // FUNNY TOOL RECIPE RUINER!!!
        String[] materials = {"wooden", "stone", "golden", "iron", "diamond"};
        String[] tools = {"pickaxe", "hoe", "shovel", "axe", "sword"};

        String[] flippedRecipeShape = {"ihg", "fed", "cba"};
        for(String mat : materials) {
            flippedRecipeShape = recipeRuiner(flippedRecipeShape);
            for(String tool : tools) {
                NamespacedKey key = NamespacedKey.minecraft(mat + "_" + tool);
                plugin.getLogger().info("Flipping: " + key.toString());
                ShapedRecipe recipe = (ShapedRecipe)server.getRecipe(key);
                server.removeRecipe(key);
                server.addRecipe(recipe.shape(flippedRecipeShape));
                recipes.add(key);
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.discoverRecipes(recipes);
    }
}

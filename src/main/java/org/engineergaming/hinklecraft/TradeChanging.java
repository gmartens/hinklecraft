package org.engineergaming.hinklecraft;

import java.net.http.WebSocket.Listener;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.MerchantRecipe;
import org.bukkit.inventory.ItemStack;
import java.util.List;

public class TradeChanging implements Listener{
    private final Random random = new Random();

    Main plugin = Main.getPlugin(Main.class);
    
    // This event is called whenever a player clicks a new trade on the trades sidebar
    @EventHandler
    public void OnPlayerClickTrade(TradeSelectEvent event) {
        if(random.nextInt(10) == 1) {
            Material newMat = Material.values()[random.nextInt(Material.values().length)];
            if (newMat.isItem()) {
                List<ItemStack> tradeItems = event.getMerchant().getRecipe(event.getIndex()).getIngredients();
                MerchantRecipe recipeChange = new MerchantRecipe(new ItemStack(newMat, random.nextInt(newMat.getMaxStackSize())+1), 10);
                recipeChange.setIngredients(tradeItems);
                event.getMerchant().setRecipe(event.getIndex(), recipeChange);
            }
        }
    }
}

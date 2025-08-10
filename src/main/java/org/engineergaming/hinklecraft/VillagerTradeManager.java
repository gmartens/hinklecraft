package org.engineergaming.hinklecraft;

import org.bukkit.Material;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.VillagerAcquireTradeEvent;
import org.bukkit.event.entity.VillagerCareerChangeEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MerchantRecipe;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.bukkit.entity.Villager.Profession.*;

public class VillagerTradeManager implements Listener {
    
    private final Random random = new Random();

    @EventHandler
    public void OnPlayerClickTrade(TradeSelectEvent event) {
        if(random.nextInt(10) == 1) {
                Material newMat = getRandomValidItem();
                List<ItemStack> tradeItems = event.getMerchant().getRecipe(event.getIndex()).getIngredients();
                MerchantRecipe recipeChange = new MerchantRecipe(new ItemStack(newMat, random.nextInt(newMat.getMaxStackSize())+1), 10);
                recipeChange.setVillagerExperience(random.nextInt(10));
                recipeChange.setIngredients(tradeItems);
                event.getMerchant().setRecipe(event.getIndex(), recipeChange);
        }
    }

    @EventHandler
    public void onVillagerAcquireTrade(VillagerAcquireTradeEvent event) {
        MerchantRecipe recipe = event.getRecipe();
        Material newMat = getRandomValidItem();
        List<ItemStack> tradeItems = recipe.getIngredients();
        recipe = new MerchantRecipe(new ItemStack(newMat, random.nextInt(newMat.getMaxStackSize())+1), 10);
        recipe.setVillagerExperience(random.nextInt(10));
        recipe.setIngredients(tradeItems);
        event.setRecipe(recipe);
    }
    
    @EventHandler
    public void onVillagerCareerChange(VillagerCareerChangeEvent event) {
        Villager villager = event.getEntity();
        
        villager.getServer().getScheduler().runTaskLater(
            villager.getServer().getPluginManager().getPlugin("hinklecraft"), 
            () -> setCustomTrades(villager), 
            1L
        );
    }

    private Material getRandomValidItem() {
        Material newMat = Material.values()[random.nextInt(Material.values().length)];
        while(!newMat.isItem() || newMat.isLegacy()) { // reroll until we get a valid item
            newMat = Material.values()[random.nextInt(Material.values().length)];
        }
        return newMat;
    }
    
    private void setCustomTrades(Villager villager) {
        List<MerchantRecipe> customTrades = new ArrayList<>();
        
        customTrades = createGeneralTrades();

        villager.setRecipes(customTrades);
    }
    
    private List<MerchantRecipe> createGeneralTrades() {
        List<MerchantRecipe> trades = new ArrayList<>();

        MerchantRecipe trade1 = new MerchantRecipe(new ItemStack(Material.EMERALD, 1), 999);
        trade1.addIngredient(new ItemStack(Material.COAL, 2));
        trade1.setVillagerExperience(4);
        trades.add(trade1);
        
        MerchantRecipe trade2 = new MerchantRecipe(new ItemStack(Material.COAL, 64), 999);
        trade2.addIngredient(new ItemStack(Material.EMERALD, 1));
        trade2.setVillagerExperience(4);
        trades.add(trade2);
        
        return trades;
    }
}
/*
package org.engineergaming.hinklecraft;

import java.net.http.WebSocket.Listener;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.enchantment.EnchantItemEvent;
import org.bukkit.event.inventory.TradeSelectEvent;
import org.bukkit.inventory.MerchantRecipe;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;

import org.bukkit.inventory.ItemStack;
import java.util.List;

public class EnchantChanging implements Listener {
    @EventHandler
    public void OnEnchant(EnchantItemEvent event) {
        ItemStack item = event.getItem();
        item.getData(DataComponentTypes.ENCHANTMENTS);
        item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                .add(Enchantment.FORTUNE, 10)
                                                                .add(Enchantment.VANISHING_CURSE, 1)
                                                                .build());
    }
}
*/
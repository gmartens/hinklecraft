package org.engineergaming.hinklecraft;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.Plugin;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;
import net.kyori.adventure.text.minimessage.MiniMessage;

public class LapisPick implements Listener {
    Plugin plugin = Main.getPlugin(Main.class);
    NamespacedKey key = new NamespacedKey(plugin, "lapis_pick");
    ItemStack item;
    ShapedRecipe recipe;

    LapisPick() {
        item = ItemStack.of(Material.DIAMOND_PICKAXE, 1);
        item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.FORTUNE, 10)
                                                                      .add(Enchantment.VANISHING_CURSE, 1)
                                                                      .build());
        item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<blue><bold>Lapis Pickaxe"));
        item.setData(DataComponentTypes.MAX_DAMAGE, 1);

        recipe = new ShapedRecipe(key, item);
        recipe.shape("LLL", " S ", " S ");
        recipe.setIngredient('L', Material.LAPIS_LAZULI);
        recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(recipe);
    }

    @EventHandler
    void onBreakBlock(BlockBreakEvent event) {
        if(event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) {
            event.getBlock().getWorld().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SLIME_BLOCK_BREAK, 1, 1);
        }
    }
    
}

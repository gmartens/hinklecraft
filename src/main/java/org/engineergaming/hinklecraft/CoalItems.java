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

public class CoalItems implements Listener {
    Plugin plugin = Main.getPlugin(Main.class);
    NamespacedKey key = new NamespacedKey(plugin, "coal_pick");
    private class Tool {
        ItemStack item;
        ShapedRecipe recipe;
    }

    int coalDurability = 64;
    int coalStackStize = 1;

    Tool pick;
    Tool axe;
    Tool shovel;
    Tool hoe;

    public void CoalPick() {
        pick.item = ItemStack.of(Material.DIAMOND_PICKAXE, 1);
        pick.item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        pick.item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Pickaxe"));
        pick.item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        pick.item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        pick.recipe = new ShapedRecipe(key, pick.item);
        pick.recipe.shape("CCC", " S ", " S ");
        pick.recipe.setIngredient('C', Material.COAL);
        pick.recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(pick.recipe);
    }

    public void CoalAxe() {
        axe.item = ItemStack.of(Material.DIAMOND_AXE, 1);
        axe.item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        axe.item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Axe"));
        axe.item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        axe.item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        axe.recipe = new ShapedRecipe(key, axe.item);
        axe.recipe.shape(" CC", " SC", " S ");
        axe.recipe.setIngredient('C', Material.COAL);
        axe.recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(axe.recipe);
    }

    public void CoalShovel() {
        shovel.item = ItemStack.of(Material.DIAMOND_SHOVEL, 1);
        shovel.item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        shovel.item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Shovel"));
        shovel.item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        shovel.item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        shovel.recipe = new ShapedRecipe(key, shovel.item);
        shovel.recipe.shape(" C ", " S ", " S ");
        shovel.recipe.setIngredient('C', Material.COAL);
        shovel.recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(shovel.recipe);
    }

    public void CoalHoe() {
        hoe.item = ItemStack.of(Material.DIAMOND_HOE, 1);
        hoe.item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        hoe.item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Hoe"));
        hoe.item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        hoe.item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        hoe.recipe = new ShapedRecipe(key, hoe.item);
        hoe.recipe.shape(" CC", " S ", " S ");
        hoe.recipe.setIngredient('C', Material.COAL);
        hoe.recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(hoe.recipe);
    }

    @EventHandler
    void onBreakBlock(BlockBreakEvent event) {
        if((event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(pick.item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(axe.item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(shovel.item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(hoe.item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ){
            event.getBlock().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE , 1, 1);
        }
    }
    
}

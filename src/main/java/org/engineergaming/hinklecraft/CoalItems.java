package org.engineergaming.hinklecraft;

import java.util.Set;
import java.util.Map;
import java.util.HashMap;

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
        public Tool(ItemStack x, ShapedRecipe y){
            item = x;
            recipe = y;
        }
    }
    Map<String, Tool> CoalTools = new HashMap<String, Tool>();

    int coalDurability = 64;
    int coalStackStize = 1;

    public void CoalPick() {
        ItemStack item = ItemStack.of(Material.DIAMOND_PICKAXE, 1);
        CoalTools.put("CoalPick", new Tool(item, new ShapedRecipe(key, item)));
        CoalTools.get("CoalPick").item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        CoalTools.get("CoalPick").item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Pickaxe"));
        CoalTools.get("CoalPick").item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        CoalTools.get("CoalPick").item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        CoalTools.get("CoalPick").recipe = new ShapedRecipe(key, item);
        CoalTools.get("CoalPick").recipe.shape("CCC", " S ", " S ");
        CoalTools.get("CoalPick").recipe.setIngredient('C', Material.COAL);
        CoalTools.get("CoalPick").recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(CoalTools.get("CoalPick").recipe);
    }

    public void CoalAxe() {
        ItemStack item = ItemStack.of(Material.DIAMOND_AXE, 1);
        CoalTools.put("CoalAxe", new Tool(item, new ShapedRecipe(key, item)));
        CoalTools.get("CoalAxe").item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        CoalTools.get("CoalAxe").item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Axe"));
        CoalTools.get("CoalAxe").item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        CoalTools.get("CoalAxe").item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        CoalTools.get("CoalAxe").recipe = new ShapedRecipe(key, item);
        CoalTools.get("CoalAxe").recipe.shape(" CC", " SC", " S ");
        CoalTools.get("CoalAxe").recipe.setIngredient('C', Material.COAL);
        CoalTools.get("CoalAxe").recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(CoalTools.get("CoalAxe").recipe);
    }

    public void CoalShovel() {
        ItemStack item = ItemStack.of(Material.DIAMOND_SHOVEL, 1);
        CoalTools.put("CoalShovel", new Tool(item, new ShapedRecipe(key, item)));
        CoalTools.get("CoalShovel").item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        CoalTools.get("CoalShovel").item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Shovel"));
        CoalTools.get("CoalShovel").item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        CoalTools.get("CoalShovel").item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        CoalTools.get("CoalShovel").recipe = new ShapedRecipe(key, item);
        CoalTools.get("CoalShovel").recipe.shape(" C ", " S ", " S ");
        CoalTools.get("CoalShovel").recipe.setIngredient('C', Material.COAL);
        CoalTools.get("CoalShovel").recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(CoalTools.get("CoalShovel").recipe);
    }

    public void CoalHoe() {
        ItemStack item = ItemStack.of(Material.DIAMOND_HOE, 1);
        CoalTools.put("CoalHoe", new Tool(item, new ShapedRecipe(key, item)));
        CoalTools.get("CoalHoe").item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                                                                      .add(Enchantment.EFFICIENCY, 100)
                                                                      .build());
        CoalTools.get("CoalHoe").item.setData(DataComponentTypes.ITEM_NAME, MiniMessage.miniMessage().deserialize("<grey><bold>Coal Hoe"));
        CoalTools.get("CoalHoe").item.setData(DataComponentTypes.MAX_DAMAGE, coalDurability);
        CoalTools.get("CoalHoe").item.setData(DataComponentTypes.MAX_STACK_SIZE, coalStackStize);

        CoalTools.get("CoalHoe").recipe = new ShapedRecipe(key, item);
        CoalTools.get("CoalHoe").recipe.shape(" CC", " S ", " S ");
        CoalTools.get("CoalHoe").recipe.setIngredient('C', Material.COAL);
        CoalTools.get("CoalHoe").recipe.setIngredient('S', Material.STICK);
        plugin.getServer().addRecipe(CoalTools.get("CoalHoe").recipe);
    }

    @EventHandler
    void onBreakBlock(BlockBreakEvent event) {
        if((event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(CoalTools.get("CoalPick").item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(CoalTools.get("CoalAxe").item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(CoalTools.get("CoalShovel").item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ||
        (event.getPlayer().getInventory().getItemInMainHand().matchesWithoutData(CoalTools.get("CoalHoe").item, Set.of(DataComponentTypes.CUSTOM_NAME, DataComponentTypes.DAMAGE, DataComponentTypes.ENCHANTMENTS, DataComponentTypes.REPAIR_COST), true)) ){
            event.getBlock().getWorld().playSound(event.getPlayer().getLocation(), Sound.ENTITY_GENERIC_EXPLODE , 1, 1);
        }
    }
    
}

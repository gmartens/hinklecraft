package org.engineergaming.hinklecraft;

import org.bukkit.World;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ExperienceOrb;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.enchantment.EnchantItemEvent;

import io.papermc.paper.datacomponent.DataComponentTypes;
import io.papermc.paper.datacomponent.item.ItemEnchantments;

import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class EnchantChanging implements Listener {
    @EventHandler
    public void OnEnchant(EnchantItemEvent event) {
        World world = event.getEnchantBlock().getWorld();
        ItemStack item = event.getItem();
        ItemEnchantments enchants = item.getData(DataComponentTypes.ENCHANTMENTS);
        item.setData(DataComponentTypes.ENCHANTMENTS, ItemEnchantments.itemEnchantments()
                .addAll(enchants.enchantments())
                .add(Enchantment.MENDING, 1)
                .add(Enchantment.VANISHING_CURSE, 1)
                .add(Enchantment.BINDING_CURSE, 1)
                .build());
        new BukkitRunnable() {
            @Override
            public void run() {
                event.getEnchantBlock().breakNaturally();
                world.spawn(event.getEnchantBlock().getLocation(), ExperienceOrb.class, (ExperienceOrb orb) -> {
                    orb.setExperience(1000);
                });
            }
        }.runTaskLater(Main.getPlugin(Main.class), 1L);

    }
}
